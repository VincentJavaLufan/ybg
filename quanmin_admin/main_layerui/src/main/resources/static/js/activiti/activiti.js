var vm = new Vue({
    el : '#rrapp',
    data : {
        showList : true,
        title : null,
        m : {}
    },
    methods : {
        query : function() {
            vm.reload();
        },
        add : function() {
            vm.showList = false;
            vm.title = "新增";
            vm.m = {};
        },
        update : function() {
            var jobId = getSelectedRow();
            if (jobId == null) {
                return;
            }
            $.get(rootPath + "/models/model_do/info.do?jobId=" + jobId, function(r) {
                vm.showList = false;
                vm.title = "修改";
                vm.m = r.m;
            });
        },
        saveOrUpdate : function(event) {
            var url = vm.m.id == null ? rootPath + '/models/model_do/create.do' : rootPath + '/models/model_do/update.do';
            $.ajax({
                type : "POST",
                url : rootPath + url,
                contentType : "application/json",
                data : JSON.stringify(vm.m),
                success : function(r) {
                    vm.reload();
                    alert(r.msg);
                }
            });
        },
        deployment:function(){
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            
            $.ajax({
                type : "POST",
                url : rootPath + "/models/model_do/deployment.do",
                // contentType : "application/json",
                data : {
                    'id' : id
                },
                
                success : function(r) {
                    vm.reload();
                    alert(r.msg);
                }
            });
        },
        del : function() {
            var id = getSelectedRows();
            if (id == null) {
                return;
            }
            confirm('确定要删除选中的记录？', function() {
                $.ajax({
                    type : "POST",
                    url : rootPath + "/models/model_do/remove.do",
                    // contentType : "application/json",
                    data : {
                        ids : id
                    },
                    success : function(r) {
                        vm.reload();
                        alert(r.msg);
                    }
                });
            });
        },
        reload : function(event) {
            vm.showList = true;
            grid.loadData();
        }
    }
});
var pageii = null;
var grid = null;
$(function() {
    grid = lyGrid({
        id : 'paging',
        l_column : [
                {
                    colkey : "id",
                    name : "ID",
                    width : "50px",
                    hide : true
                }, {
                    colkey : "name",
                    name : "流程名称"
                }, {
                    colkey : "key",
                    name : "流程关键字",
                }, {
                    colkey : "createTime",
                    name : "创建时间",
                }, {
                    colkey : "id",
                    name : "操作",
                    renderData : function(rowindex, data, rowdata, colkey)// 渲染数据
                    {
                        return "<a target='_blank' class='btn btn-primary' href='/modeler.html?modelId=" + rowdata.id + "'> <i class='fa fa-pencil-square-o'></i>设计流程</a>";
                    }
                }],
        jsonUrl : rootPath + '/models/model_do/list.do',
        usePage : false,
        checkbox : true,
        checkValue : 'id',
    });
});
