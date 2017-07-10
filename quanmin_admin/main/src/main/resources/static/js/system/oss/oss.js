var pageii = null;
var grid = null;
$(function() {
    grid = lyGrid({
        id : 'paging',
        l_column : [
                {
                    colkey : "id",
                    name : "id",
                    width : "50px",
                    hide : true
                }, {
                    colkey : "url",
                    name : "图片",
                    renderData : function(rowindex, data, rowdata, colkeyn) {
                        return "<img width='300px' hight='300px' src='" + data + "'>";
                    }
                }, {
                    colkey : "createdate",
                    name : "创建时间"
                }],
        jsonUrl : rootPath + '/sys/oss_do/list.do',
        checkbox : true
    });
    
    /****/
    
    
    
    // $("#search").click("click", function() {// 绑定查询按扭
    // var searchParams = $("#searchForm").serializeJson();// 初始化传参数
    // grid.setOptions({
    // data : searchParams
    // });
    // });
});

function del(){
    var cbox = grid.getSelectedCheckbox();
    if (cbox == "") {
        layer.msg("请选择删除项！！");
        return;
    }
    layer.confirm('是否删除？', function(index) {
        var url = rootPath + "/sys/oss_do/delete.do";
        var s = CommnUtil.ajax(url, {
            ids : cbox.join(",")
        }, "json");
        if (s.success ) {
            alert('删除成功');
            grid.loadData();
        } else {
            alert('删除失败');
        }
    });
}
