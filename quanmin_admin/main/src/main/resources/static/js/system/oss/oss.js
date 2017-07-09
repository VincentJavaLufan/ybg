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
                        
                        return "<img src='"+data+"'>";
                    }
                }, {
                    colkey : "createdate",
                    name : "创建时间"
                }],
        jsonUrl : rootPath + '/sys/oss_do/list.do',
        checkbox : true
    });
//    $("#search").click("click", function() {// 绑定查询按扭
//        var searchParams = $("#searchForm").serializeJson();// 初始化传参数
//        grid.setOptions({
//            data : searchParams
//        });
//    });

   
});



