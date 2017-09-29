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
    $("#addRole").click("click", function() {
        addRole();
    });
    $("#editRole").click("click", function() {
        editRole();
    });
    $("#delRole").click("click", function() {
        delRole();
    });
});
function editRole() {
    var cbox = grid.getSelectedCheckbox();
    if (cbox.length > 1 || cbox == "") {
        layer.msg("只能选中一个");
        return;
    }
    pageii = layer.open({
        title : "编辑",
        type : 2,
        maxmin : true, // 开启最大化最小化按钮
        area : [
                "600px", "60%"],
        content : rootPath + '/edu/school_do/toupdate.do?id=' + cbox
    });
}
function addRole() {
    pageii = layer.open({
        title : "新增",
        type : 2,
        maxmin : true, // 开启最大化最小化按钮
        area : [
                "600px", "60%"],
        content : rootPath + '/edu/school_do/toadd.do'
    });
}
function delRole() {
    var cbox = grid.getSelectedCheckbox();
    if (cbox == "") {
        layer.msg("请选择删除项！！");
        return;
    }
    layer.confirm('是否删除？', function(index) {
        var url = rootPath + '/edu/school_do/remove.do';
        var s = CommnUtil.ajax(url, {
            ids : cbox.join(",")
        }, "json");
        layer.msg(s.msg);
        grid.loadData();
    });
}
