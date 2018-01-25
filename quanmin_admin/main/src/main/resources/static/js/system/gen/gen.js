var vm = new Vue({
	el : '#rrapp',
	data : {
		showList : true,
		title : null,
		tablePrefix : null,
		javapackage : null,
		author : null,
		pathName : null,
		email : null
	},

	methods : {
		query : function() {
			vm.reload();
		},

		addConfig : function() {
			vm.showList = false;
			vm.title = "代码生成配置";
			var url = '/sys/generator_do/getsetting.do';
			$.ajax({
				type : "POST",
				url : url,
				// contentType : "application/json",
				dataType : 'json',
				data : {

				},
				success : function(r) {

					vm.javapackage=r.package;
					vm.tablePrefix=r.tablePrefix;
					vm.author=r.author;
					vm.pathName=r.pathName;
					vm.email=r.email;

				}
			});
		},
		saveOrUpdate : function() {
			var url = rootPath + "/sys/generator_do/updatesetting.do";
			$.ajax({
				dataType : 'json',
				type : "POST",
				url : url,
				// contentType : "application/json",
				data : {

					tablePrefix : vm.tablePrefix,
					javapackage : vm.javapackage,
					author : vm.author.value,
					pathName : vm.pathName,
					email : vm.email

				},
				success : function(r) {
					alert(r.msg);
				}
			});
		},

		reload : function() {
			vm.showList = true;
			layui.use('table', function() {
                var table = layui.table;
                table.render({
                    elem : '#gentable' // 选定是那个DIV
                    ,
                    url : rootPath + '/sys/generator_do/list.do',
                    cols : [
                        [
                            {
                                type : 'checkbox'
                            }, {
                                field : 'tableName',
                                width : 180,
                                title : '表名'
                            }, {
                                field : 'comments',
                                title : '备注',
                                minWidth : 100,
                                templet : function(data) {
                                    return data.comments;
                                }
                            } ] ],
                    page : true, // 开启分页
                    request : laypagerequest,
                    response : laypageresponse,
                    where : $("#searchForm").serializeJSON()
                });
            });
		},
		gencode:function(){
		    
		    var roleIds = getSelectedRows('gentable', 'tableName');
            if (roleIds == null) {
                return;
            }
		    layer.confirm('是否生成？', function(index) {
		        location.href = '/sys/generator_do/code.do?tables=' + roleIds;

		    });
		}
	}
});


vm.reload();
