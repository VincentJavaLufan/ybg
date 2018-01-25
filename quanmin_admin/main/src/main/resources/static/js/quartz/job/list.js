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
            var jobId = getSelectedRow('jobtable', 'job_Id');
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
            var jobIds = getSelectedRows('jobtable', 'job_Id');
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
            var jobIds = getSelectedRows('jobtable', 'job_Id');
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
            var jobIds = getSelectedRows('jobtable', 'job_Id');
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
            var jobIds = getSelectedRows('jobtable', 'job_Id');
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
            layui.use('table', function() {
                var table = layui.table;
                table.render({
                    elem : '#jobtable' // 选定是那个DIV
                    ,
                    url : rootPath + '/sys/schedule_do/list.do',
                    cols : [
                        [
                            {
                                type : 'checkbox'
                            }, {
                                field : 'job_Id',
                                width : 180,
                                title : '任务ID'
                            }, {
                                field : 'bean_Name',
                                width : 100,
                                title : 'Spring Bean'
                            }, {
                                field : 'method_Name',
                                title : '方法名',
                                width : 100
                            }, {
                                field : 'params',
                                title : '参数',
                                width : 100
                            }, {
                                field : 'status',
                                title : '状态',
                                width : 100,
                                templet : function(data) {
                                    if (data.status == 0) {
                                        return "开启";
                                    }
                                    if (data.status == 1) {
                                        return "暂停";
                                    }
                                    return "数据异常";
                                }
                            }, {
                                field : 'remark',
                                title : '备注',
                                width : 100
                            }, {
                                field : 'cron_Expression',
                                title : '定时表达式',
                                width : 100
                            }, {
                                field : 'create_Time',
                                title : '创建时间',
                                width : 100
                            } ] ],
                    page : true, // 开启分页
                    request : laypagerequest,
                    response : laypageresponse,
                // where : $("#searchForm").serializeJSON()
                });
            });
        }
    }
});
vm.reload();
