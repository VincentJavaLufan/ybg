var vm = new Vue({
    el : '#rrapp',
    data : {
        q : {
            paytype : null
        },
        showList : true,
        title : null,
        seller : {}
    },
    methods : {
        query : function() {
            vm.reload();
        },
        add : function() {
            vm.showList = false;
            vm.title = "新增";
            vm.seller = {};
        },
        update : function() {
            var payid = getSelectedRow('sellertable', 'payid');
            if (payid == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";
            vm.getInfo(payid);
        },
        getInfo : function(payid) {
            $.get(rootPath + "/pay/seller/get.do?id=" + payid, function(r) {
                vm.payAccount = r.payAccount;
            });
        },
        saveOrUpdate : function(event) {
            var url = vm.seller.payid == null ? "/pay/seller/create.do" : "/pay/seller/update.do";
            $.ajax({
                type : "POST",
                url : rootPath + url,
                contentType : "application/json",
                data : JSON.stringify(vm.seller),
                success : function(r) {
                    vm.reload();
                    alert(r.msg);
                }
            });
        },
        del : function() {
            var payids = getSelectedRows('sellertable', 'payid');
            if (payids == null) {
                return;
            }
            confirm('确定要删除选中的记录？', function() {
                $.ajax({
                    type : "POST",
                    url : rootPath + "/pay/seller/remove.do",
                    data : {
                        ids : payids
                    },
                    success : function(r) {
                        alert(r.msg);
                        vm.reload();
                    }
                });
            });
        },
        reload : function(event) {
            vm.showList = true;
            layui.use('table', function() {
                var table = layui.table;
                table.render({
                    elem : '#sellertable' // 选定是那个DIV
                    ,
                    url : rootPath + '/pay/seller/list.do',
                    cols : [
                        [
                            {
                                type : 'checkbox'
                            }, {
                                field : 'payid',
                                width : 180,
                                title : '支付账号id'
                            }, {
                                field : 'partner',
                                width : 180,
                                title : '商户ID/平台ID'
                            },  {
                                field : 'publickey',
                                width : 180,
                                title : '支付平台公钥',
                                templte:function(data){
                                    if (data.publickey.length > 32) {
                                        return data.publickey.substring(0, 32) + "...";
                                    }
                                    return data.publickey;
                                }
                            },  {
                                field : 'privatekey',
                                width : 180,
                                title : '支付平台公钥',
                                templte:function(data){
                                    if (data.privatekey.length > 32) {
                                        return data.privatekey.substring(0, 32) + "...";
                                    }
                                    return data.publickey;
                                }
                            }, {
                                field : 'notifyurl',
                                width : 180,
                                title : '异步回调地址'
                            },  {
                                field : 'returnurl',
                                width : 180,
                                title : '同步回调地址'
                            },  {
                                field : 'seller',
                                width : 180,
                                title : '收款账号, 针对支付宝'
                            },  {
                                field : 'signtype',
                                width : 180,
                                title : '签名类型'
                            },  {
                                field : 'inputcharset',
                                width : 180,
                                title : '枚举值，字符编码 utf-8,gbk等等'
                            },  {
                                field : 'paytype',
                                width : 180,
                                title : '消息类型，text,xml,json'
                            },  {
                                field : 'test',
                                width : 180,
                                title : '是否为测试环境'
                            }, 
                            
                            ] ],
                    page : true, // 开启分页
                    request : laypagerequest,
                    response : laypageresponse,
                    where : $("#searchForm").serializeJSON()
                });
            });
        }
    }
});
vm.reload();
