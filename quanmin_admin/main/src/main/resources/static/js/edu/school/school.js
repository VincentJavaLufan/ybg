var pageii = null;
var grid = null;
$(function() {
    grid = lyGrid({
        id : 'paging',
        l_column : [
                {
                    colkey : "id",
                    name : "主键",
                    width : "50px",
                    hide : true
                }, {
                    colkey : "schoolname",
                    name : "学校名字"
                }, {
                    colkey : "schooltype",
                    name : "学校类型，1111四位，分别代表高中到幼儿，0001代表学校包含幼儿"
                }, {
                    colkey : "info",
                    name : ""
                }],
        jsonUrl : rootPath + '/edu/school_do/list.do',
        checkbox : true
    });
    $("#search").click("click", function() {// 绑定查询按扭
        var searchParams = $("#searchForm").serializeJson();// 初始化传参数
        grid.setOptions({
            data : searchParams
        });
    });
   
});

