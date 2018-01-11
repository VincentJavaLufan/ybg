/*******************************************************************************
 * @author 严
 * @parm div 容器ID
 * @parm xxdm 单位代码
 * @parm bgid 表格ID
 ******************************************************************************/
var DEFAULTDIV='talediv';// 默认容器名称
var GETBGINFOURL='/bbtest/getbginfo.do';// 获取表格信息URL
var UREPORTDESIGNURL='/ureport/designer/loadReport';//报表模板请求URL;
var TJXTTABLEDATAURL='/bbtest/getData.do';//请求模版后 获取数据的RUL
function tjxtinit(div,bgid,xxdm){
	$.ajax({
		url:GETBGINFOURL,
		type:"post",
		dataType:"json",
		success:function(data){
			var startrow=data.startrow;
			var startcol=data.startcol;
			var tempurl=data.tempurl;
			tjxtgetData(div,tempurl,xxdm,bgid,startrow,startcol);
		}
		
	})
	
}
var cellsMap;
function tjxtgetData(div, tempurl, xxdm, bg_id,startrow,startcol) {
	var container = document.getElementById(div);
	
	var rows;
	var columns;
	var temhot;	
	// 1.获取想要的表格的详细信息。 需要的内容：bg_id，起始的行列号 ，路径
	 $.ajax({
	        url: UREPORTDESIGNURL,
	        type: "post",
	        data: {
	            file: tempurl//'file:jcj313.ureport.xml'
	        },
	        dataType: "json",
	        success: function(data) {
	            var setmergeCells = [];
	            cellsMap = data.cellsMap;
	            columns = data.columns;
	            rows = data.rows;
	            var rowHeights = [];
	            var height;
	            var dataArray = [];
	            for (var row of rows) {
	                height = row.height;
	                rowHeights.push(pointToPixel(height));
	            }
	            var colWidths = [];
	            var width;
	            for (var col of columns) {
	                width = col.width;
	                colWidths.push(pointToPixel(width));
	            }

	            for (var row of rows) {
	                var rowData = [];
	                for (var col of columns) {
	                    var key = row.rowNumber + "," + col.columnNumber;
	                    if (cellsMap[key]) {
	                        var celldata = cellsMap[key].value.value || "";
	                        rowData.push(celldata);
	                        var rowspan = cellsMap[key].rowSpan,
	                        colspan = cellsMap[key].colSpan;
	                        if (rowspan > 0 || colspan > 0) {
	                            if (rowspan === 0) rowspan = 1;
	                            if (colspan === 0) colspan = 1;
	                            setmergeCells.push({
	                                rowspan,
	                                colspan,
	                                row: row.rowNumber - 1,
	                                col: col.columnNumber - 1
	                            });
	                        }

	                    } else {
	                        rowData.push("");
	                    }
	                }
	                dataArray.push(rowData);
	            }
	            var temhot = new Handsontable(container, {
	                rowHeaders: true,
	                colHeaders: true,
	                data: dataArray,
	                minSpareRows: 1,
	                contextMenu: false,
	                manualColumnResize: true,
	                formulas: true,
	                mergeCells: setmergeCells,
	            });

	            // 加载模版
	            // hot.loadData(dataArray);
	            temhot.updateSettings({
	                colWidths,
	                rowHeights
	            });
	            // initData();//加载数据 你公式计算的库在哪
	            // 从0表示第一列
	       
	            var endrow = startrow;	            
	            var endcol = startcol;
	            $.ajax({
	                url: TJXTTABLEDATAURL,
	                type: "post",
	                dataType: "json",
	                data:{
	                	bgid:bg_id,
	                	xxdm:xxdm
	                },
	                success: function(result) {
	                    var data = result.values;
	                    //row 是数组 代表哪些行有数据，fields也是数组 代表有数据的列 
	                    //如果是二维数组 则不需要这么麻烦。
	                    var rowlength = result.row.length;
	                    var collength = result.fields.length;
	                    endrow = startrow + rowlength;
	                    endcol = startcol + collength;
	                    var hotdata = temhot.getData();
	                    //下面是根据行列赋值到相关的表格。
	                    for (var j = 0; j < result.row.length; j++) {
	                        var cellrow = parseInt(result.row[j]);
	                        for (var i = 0; i < result.fields.length; i++) {
	                            var cellcol = parseInt(result.fields[i].replace("c", ""));
	                            if (data[j][i]) {
	                                hotdata[cellrow + startrow][cellcol + startcol] = data[j][i] || "";
	                            }
	                        }

	                    }
	                    temhot.loadData(hotdata);
	                }
	            });
	        }
	    });
	
}

function pointToPixel(point) {
    var value = point * 1.33;
    return Math.round(value);
}
