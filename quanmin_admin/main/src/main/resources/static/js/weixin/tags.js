var vm = new Vue({
    el : '#rrapp',
    data : {
        q : {
            beanName : null
        },
        showList : true,
        title : null,
        tags : {}
    },
    methods : {
        query : function() {
            vm.reload();
        },
        add : function() {
            vm.showList = false;
            vm.title = "新增";
            vm.tags = {};
        },
        update : function() {
            var jobId = getSelectedRow();
            if (jobId == null) {
                return;
            }
            $.get(rootPath + "/weixin/tags/info.do?jobId=" + jobId, function(r) {
                vm.showList = false;
                vm.title = "修改";
                vm.tags = r.tags;
            });
        },
        saveOrUpdate : function(event) {
            var url = vm.tags.jobId == null ? rootPath + '/weixin/tags/create.do' : rootPath + '/weixin/tags/update.do';
            $.ajax({
                type : "POST",
                url : rootPath + url,
                contentType : "application/json",
                data : JSON.stringify(vm.tags),
                success : function(r) {
                    vm.reload();
                    alert(r.msg);
                }
            });
        },
        del : function() {
            var jobIds = getSelectedRows();
            if (jobIds == null) {
                return;
            }
            confirm('确定要删除选中的记录？', function() {
                $.ajax({
                    type : "POST",
                    url : rootPath + "/weixin/tags/remove.do",
                    // contentType : "application/json",
                    data : {
                        ids : jobIds
                    },
                    success : function(r) {
                        vm.reload();
                        alert(r.msg);
                    }
                });
            });
        },
        pause : function() {
            var jobIds = getSelectedRows();
            if (jobIds == null) {
                return;
            }
            confirm('确定要暂停选中的记录？', function() {
                $.ajax({
                    type : "POST",
                    url : rootPath + "/weixin/tags/pause.do",
                    // contentType : "application/json",
                    data : {
                        jobIds : jobIds
                    },
                    success : function(r) {
                        vm.reload();
                        alert(r.msg);
                    }
                });
            });
        },
        resume : function() {
            var jobIds = getSelectedRows();
            if (jobIds == null) {
                return;
            }
            confirm('确定要恢复选中的记录？', function() {
                $.ajax({
                    type : "POST",
                    url : rootPath + "/weixin/tags/resume.do",
                    // contentType : "application/json",
                    data : {
                        jobIds : jobIds
                    },
                    success : function(r) {
                        vm.reload();
                        alert(r.msg);
                    }
                });
            });
        },
        runOnce : function() {
            var jobIds = getSelectedRows();
            if (jobIds == null) {
                return;
            }
            confirm('确定要立即执行选中的记录？', function() {
                $.ajax({
                    type : "POST",
                    url : rootPath + "/weixin/tags/run.do",
                    // contentType : "application/json",
                    data : {
                        jobIds : jobIds
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
                    name : "标签ID",
                    width : "50px",
                    hide : true
                }, {
                    colkey : "name",
                    name : "标签名称"
                }, {
                    colkey : "count",
                    name : "标签人数",
                }],
        jsonUrl : rootPath + '/weixin/tags/list.do',
        checkbox : true,
        checkValue : 'id',
    });
   
});
