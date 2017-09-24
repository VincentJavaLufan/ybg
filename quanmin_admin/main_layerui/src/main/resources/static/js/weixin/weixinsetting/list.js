var pageii = null;
var grid = null;
$(function() {
    grid = lyGrid({
        pagId : 'paging',
        l_column : [
                {
                    colkey : "id",
                    name : "id",
                    width : "50px",
                    hide : true
                }, {
                    colkey : "appid",
                    name : "微信号"
                }, {
                    colkey : "secret",
                    name : "密钥"
                }, {
                    colkey : "partnerkey",
                    name : "微信商API"
                }, {
                    colkey : "notifyurl",
                    name : "微信商通知地址"
                }, {
                    colkey : "mchid",
                    name : "商户ID"
                }, {
                    colkey : "domain",
                    name : "域名"
                }, {
                    colkey : "isuse",
                    name : "是否可用"
                }, {
                    colkey : "state",
                    name : "状态"
                }, {
                    colkey : "scope",
                    name : "范围"
                }, {
                    colkey : "redirecturi",
                    name : "回调地址"
                }],
        jsonUrl : rootPath + '/weixin/weixinsetting_do/list.do',
        checkbox : true,
        serNumber : true
    });
    $("#search").click("click", function() {// 绑定查询按扭
        var searchParams = $("#searchForm").serializeJson();// 初始化传参数
        grid.setOptions({
            data : searchParams
        });
    });
    $("#addFun").click("click", function() {
        addFun();
    });
      
});
function editFun() {
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
        content : rootPath + '/weixin/weixinsetting_do/toupdate.do?id=' + cbox
    });
}
function addFun() {
    pageii = layer.open({
        title : "新增",
        type : 2,
        maxmin : true, // 开启最大化最小化按钮
        area : [
                "600px", "60%"],
        content : rootPath + '/weixin/weixinsetting_do/toadd.do'
    });
}
function delRole() {
    var cbox = grid.getSelectedCheckbox();
    if (cbox == "") {
        layer.msg("请选择删除项！！");
        return;
    }
    layer.confirm('是否删除？', function(index) {
        var url = rootPath + '/weixin/weixinsetting_do/remove.do';
        var s = CommnUtil.ajax(url, {
            ids : cbox.join(",")
        }, "json");
        if (s.msg == "操作成功") {
            layer.msg('删除成功');
            grid.loadData();
        } else {
            layer.msg('删除失败');
        }
    });
}
