var pageii = null;
var grid = null;
$(function() {
    // $(function() {
    // grid = lyGrid({
    // id : 'paging',
    // l_column : [ {
    // colkey : "id",
    // name : "id",
    // hide : true
    // }, {
    // colkey : "name",
    // name : "菜单名称",
    // align : 'left'
    // }, {
    // colkey : "type",
    // name : "菜单类型",
    // renderData : function(rowindex, data, rowdata, column) {
    // if (data == "0") {
    // return "目录";
    // }
    // if (data == "1") {
    // return "菜单";
    // }
    // if (data == "2") {
    // return "按钮";
    // }
    //
    // return "数据异常";
    //
    // }
    // }, {
    // colkey : "resKey",
    // name : "唯一KEY"
    // }, {
    // colkey : "resUrl",
    // name : "URL地址"
    // }, {
    // colkey : "ishide",
    // name : "是否隐藏",
    // renderData : function(rowindex, data, rowdata, column) {
    // if (data == "0") {
    // return "否";
    // } else if (data == "1") {
    // return "是";
    // }
    // }
    // }, {
    // colkey : "description",
    // width : "100px",
    // name : "描述"
    // }
    // , {
    // colkey : "icon",
    //			
    // name : "图标",
    // renderData : function(rowindex, data, rowdata, column) {
    // return "<i class=\"fa "+data+" fa-3x \" > </i>";
    // }
    // }
    // ],
    // jsonUrl : rootPath + '/res/res_do/list.do',
    // checkbox : true,
    // usePage : false,
    // records : "treelists",
    // treeGrid : {
    // type : 1,
    // tree : true,
    // name : 'name',
    // id : "id",
    // pid : "parentId"
    // }
    // });
    $("#paging").load("/res/res_do/list.do");
    //	
    // $("#search").click("click", function() {// 绑定查询按扭
    // var searchParams = $("#searchForm").serializeJson();
    // grid.setOptions({
    // data : searchParams
    // });
    // });
    $("#addFun").click("click", function() {
        addFun();
    });
    $("#batchaddFun").click("click", function() {
        batchaddFun();
    });
   
    
    // $("#editFun").click("click", function() {
    // editFun();
    // });
    // $("#delFun").click("click", function() {
    // delFun();
    // });
    // $("#lyGridUp").click("click", function() {// 上移
    // var jsonUrl = rootPath + '/background/resources/sortUpdate.shtml';
    // grid.lyGridUp(jsonUrl);
    // });
    // $("#lyGridDown").click("click", function() {// 下移
    // var jsonUrl = rootPath + '/background/resources/sortUpdate.shtml';
    // grid.lyGridDown(jsonUrl);
    // });
});
function editFun(cbox) {
    // var cbox = grid.getSelectedCheckbox();
    // if (cbox.length > 1 || cbox == "") {
    // layer.alert("只能选中一个");
    // return;
    // }
    pageii = layer.open({
        title : "编辑",
        type : 2,
        maxmin : true, // 开启最大化最小化按钮
        area : [
                "600px", "80%"],
        content : rootPath + '/res/res_do/toupdate.do?id=' + cbox
    });
}
function addFun() {
    pageii = layer.open({
        title : "新增",
        type : 2,
        area : [
                "600px", "80%"],
        maxmin : true, // 开启最大化最小化按钮
        content : rootPath + '/res/res_do/toadd.do'
    });
}
function batchaddFun(){
    pageii = layer.open({
        title : "新增",
        type : 2,
        area : [
                "600px", "80%"],
        maxmin : true, // 开启最大化最小化按钮
        content : rootPath + '/res/res_do/batchindex.do'
    });
    
}


function delFun(ids) {
    // var cbox = grid.getSelectedCheckbox();
    // if (cbox == "") {
    // layer.alert("请选择删除项！！");
    // return;
    // }
    layer.confirm('是否删除？', function(index) {
        var url = rootPath + '/res/res_do/remove.do';
        var s = CommnUtil.ajax(url, {
            ids : ids
        }, "json");
        if (s.msg == "操作成功") {
            layer.msg('删除成功');
            $("#paging").load("/res/res_do/list.do");
        } else {
            layer.msg('删除失败');
        }
    });
}
