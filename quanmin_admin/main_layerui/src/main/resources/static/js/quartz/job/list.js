var vm = new Vue({
    el : '#rrapp',
    data : {
        q : {
            beanName : null
        },
        showList : true,
        title : null,
        schedule : {}
    },
    methods : {
        query : function() {
            vm.reload();
        },
        add : function() {
            vm.showList = false;
            vm.title = "新增";
            vm.schedule = {};
        },
        update : function() {
            var jobId = getSelectedRow();
            if (jobId == null) {
                return;
            }
            $.get(rootPath + "/sys/schedule_do/info.do?jobId=" + jobId, function(r) {
                vm.showList = false;
                vm.title = "修改";
                vm.schedule = r.schedule;
            });
        },
        saveOrUpdate : function(event) {
            var url = vm.schedule.jobId == null ? rootPath + '/sys/schedule_do/create.do' : rootPath + '/sys/schedule_do/update.do';
            $.ajax({
                type : "POST",
                url : rootPath + url,
                contentType : "application/json",
                data : JSON.stringify(vm.schedule),
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
                    url : rootPath + "/sys/schedule_do/delete.do",
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
        pause : function() {
            var jobIds = getSelectedRows();
            if (jobIds == null) {
                return;
            }
            confirm('确定要暂停选中的记录？', function() {
                $.ajax({
                    type : "POST",
                    url : rootPath + "/sys/schedule_do/pause.do",
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
                    url : rootPath + "/sys/schedule_do/resume.do",
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
                    url : rootPath + "/sys/schedule_do/run.do",
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
                    colkey : "job_Id",
                    name : "任务ID",
                    width : "50px",
                    hide : true
                }, {
                    colkey : "bean_Name",
                    name : "Spring Bean"
                }, {
                    colkey : "method_Name",
                    name : "方法名",
                }, {
                    colkey : "params",
                    name : "参数"
                }, {
                    colkey : "status",
                    name : "状态",
                    renderData : function(rowindex, data, rowdata, colkeyn) {
                        if (data == 0) {
                            return "开启";
                        }
                        if (data == 1) {
                            return "暂停";
                        }
                        return "数据异常";
                    }
                }, {
                    colkey : "remark",
                    name : "备注"
                }, {
                    colkey : "cron_Expression",
                    name : "定时表达式"
                }, {
                    colkey : "create_Time",
                    name : "创建时间"
                }],
        jsonUrl : rootPath + '/sys/schedule_do/list.do',
        checkbox : true,
        checkValue : 'job_Id',
    });
    
});

