var editor = null;
var vm = new Vue({
    el : '#rrapp',
    data : {
        showList : true,
        title : null,
        genTemp : {}
    },
    methods : {
        query : function() {
            vm.reload();
        },
        add : function() {
            vm.showList = false;
            vm.title = "新增";
            vm.genTemp = {};
        },
        update : function(event) {
            var id = getSelectedRow('genTemptable', 'id');
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";
            vm.getInfo(id)
        },
        saveOrUpdate : function(event) {
            var url = vm.genTemp.id == null ? "/sys/gentemp_do/create.do" : "/sys/gentemp_do/update.do";
            vm.genTemp.gencontext = editor.getValue();
            console.log(vm.genTemp)
            $.ajax({
                type : "POST",
                url : rootPath + url,
                contentType : "application/json",
                data : JSON.stringify(vm.genTemp),
                success : function(r) {
                    vm.reload();
                    alert(r.msg);
                }
            });
        },
        del : function(event) {
            var ids = getSelectedRows('genTemptable', 'id');
            if (ids == null) {
                return;
            }
            confirm('确定要删除选中的记录？', function() {
                $.ajax({
                    type : "POST",
                    url : rootPath + "/sys/gentemp_do/remove.do",
                    data : {
                        ids : ids
                    },
                    success : function(r) {
                        alert(r.msg);
                    }
                });
            });
        },
        getInfo : function(id) {
            $.get(rootPath + "/sys/gentemp_do/get.do?id=" + id, function(r) {
                vm.genTemp = r.genTemp;
                if (editor != null) {
                    $(".CodeMirror").remove()
                }
                setTimeout(function() {
                    editor = CodeMirror.fromTextArea(document.getElementById("gencontext"), {
                        mode : "text/x-java", // 实现Java代码高亮
                        lineNumbers : true, // 显示行号
                        // theme: "dracula", // 设置主题
                        lineWrapping : true, // 代码折叠
                        foldGutter : true,
                        gutters : [
                            "CodeMirror-linenumbers", "CodeMirror-foldgutter" ],
                        matchBrackets : true, // 括号匹配
                    // readOnly: true, //只读
                    });
                }, 100)
            });
        },
        init : function() {
        },
        reload : function(event) {// 查询方法 网站发我
        // editor =
        // CodeMirror.fromTextArea(document.getElementById("gencontext"), {
        // });
            vm.showList = true;
            layui.use('table', function() {
                var table = layui.table;
                table.render({
                    elem : '#genTemptable' // 选定是那个DIV
                    ,
                    url : rootPath + '/sys/gentemp_do/list.do',
                    cols : [
                        [
                            {
                                type : 'checkbox'
                            }, {
                                field : 'id',
                                title : 'ID',
                                minWidth : 100,
                                templet : function(data) {
                                    return data.id;
                                }
                            }, {
                                field : 'description',
                                title : '描述',
                                minWidth : 100,
                                templet : function(data) {
                                    return data.description;
                                }
                            }, {
                                field : 'genfilename',
                                title : '文件名称',
                                minWidth : 100,
                                templet : function(data) {
                                    return data.genfilename;
                                }
                            }, {
                                field : 'state',
                                title : '是否启用',
                                minWidth : 100,
                                templet : function(data) {
                                    if (data.state == 1) {
                                        return "启用";
                                    }
                                    if (data.state == 0) {
                                        return "停用";
                                    }
                                    return data.state;
                                }
                            } ] ],
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
