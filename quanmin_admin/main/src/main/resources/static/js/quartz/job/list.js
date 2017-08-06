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
                    colkey : "jobId",
                    name : "任务ID",
                    width : "50px",
                    hide : true
                }, {
                    colkey : "beanName",
                    name : "Spring Bean"
                }, {
                    colkey : "methodName",
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
                    colkey : "cronExpression",
                    name : "定时表达式"
                }, {
                    colkey : "createTime",
                    name : "创建时间"
                }],
        jsonUrl : rootPath + '/sys/schedule_do/list.do',
        checkbox : true,
        checkValue : 'jobId',
    });
    // $("#search").click("click", function() {// 绑定查询按扭
    // var searchParams = $("#searchForm").serializeJson();// 初始化传参数
    // grid.setOptions({
    // data : searchParams
    // });
    // });
    // $("#addRole").click("click", function() {
    // addRole();
    // });
    // $("#editRole").click("click", function() {
    // editRole();
    // });
    // $("#delRole").click("click", function() {
    // delRole();
    // });
    // $("#run").click("click", function() {
    // run();
    // });
    // $("#pause").click("click", function() {
    // pause();
    // });
    // $("#resume").click("click", function() {
    // resume();
    // });
});
// function editRole() {
// var cbox = grid.getSelectedCheckbox();
// if (cbox.length > 1 || cbox == "") {
// layer.msg("只能选中一个");
// return;
// }
// pageii = layer.open({
// title : "编辑",
// type : 2,
// maxmin : true, // 开启最大化最小化按钮
// area : [
// "600px", "60%"],
// content : rootPath + '/sys/schedule_do/toupdate.do?jobId=' + cbox
// });
// }
// function addRole() {
// pageii = layer.open({
// title : "新增",
// type : 2,
// maxmin : true, // 开启最大化最小化按钮
// area : [
// "600px", "60%"],
// content : rootPath + '/sys/schedule_do/toadd.do'
// });
// }
// function delRole() {
// var cbox = grid.getSelectedCheckbox();
// if (cbox == "") {
// layer.msg("请选择删除项！！");
// return;
// }
// layer.confirm('是否删除？', function(index) {
// var url = rootPath + '/sys/schedule_do/remove.do';
// var s = CommnUtil.ajax(url, {
// ids : cbox.join(",")
// }, "json");
// if (s.msg == "操作成功") {
// layer.msg('删除成功');
// grid.loadData();
// } else {
// layer.msg('删除失败');
// }
// });
// }
// function run() {
// var cbox = grid.getSelectedCheckbox();
// if (cbox.length > 1 || cbox == "") {
// layer.msg("只能选中一个");
// return;
// }
// $.ajax({
// url : "/sys/schedule_do/run.do",
// data : {
// jobIds : cbox
// },
// type : "post",
// dataType : "json",
// success : function(data) {
// alert(data.msg);
// }
// });
// }
// function pause() {
// var cbox = grid.getSelectedCheckbox();
// if (cbox.length > 1 || cbox == "") {
// layer.msg("只能选中一个");
// return;
// }
// $.ajax({
// url : "/sys/schedule_do/pause.do",
// data : {
// jobIds : cbox
// },
// type : "post",
// dataType : "json",
// success : function(data) {
// alert(data.msg);
// }
// });
// }
// function resume() {
// var cbox = grid.getSelectedCheckbox();
// if (cbox.length > 1 || cbox == "") {
// layer.msg("只能选中一个");
// return;
// }
// $.ajax({
// url : "/sys/schedule_do/resume.do",
// data : {
// jobIds : cbox
// },
// type : "post",
// dataType : "json",
// success : function(data) {
// alert(data.msg);
// }
// });
// }
