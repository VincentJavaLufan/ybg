var vm = new Vue({
    el : '#rrapp',
    data : {
        title : "第三方配置",
        mayun : null,
        sina : null,
        baidu : null,
        weixin : null,
        thirdpart:1,
    },
    methods : {
        query : function() {
            vm.title = "第三方设置";
            $.ajax({
                type : "POST",
                url : rootPath + "/thirdoartlogin_do/info.do",
                dataType : "json",
                success : function(data) {
                    vm.mayun = data.mayun;
                    vm.sina = data.sina;
                    vm.baidu = data.baidu;
                    vm.weixin = data.weixin;
                }
            });
        },
        update : function() {
            $.ajax({
                type : "POST",
                url : rootPath + "/thirdoartlogin_do/update.do",
                data : {
                    mayunid : vm.mayun.client_ID,
                    mayunSERCRET : vm.mayun.client_SERCRET,
                    mayunurl : vm.mayun.redirect_URI,
                    baiduid : vm.baidu.client_ID,
                    baiduSERCRET : vm.baidu.client_SERCRET,
                    baiduurl : vm.baidu.redirect_URI,
                    sinaid : vm.sina.client_ID,
                    sinaSERCRET : vm.sina.client_SERCRET,
                    sinaurl : vm.sina.redirect_URI,
                    weixinid : vm.weixin.appId,
                    weixinSERCRET : vm.weixin.secret
                },
                success : function(data) {
                    alert(data.msg);
                }
            });
        }
    }
});
vm.query();
