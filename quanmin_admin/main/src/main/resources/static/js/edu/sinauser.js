var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		sinaUser: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.sinaUser = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.sinaUser.id == null ? "/edu/sinauser_do//create.do" : "/edu/sinauser_do//update.do";
			$.ajax({
				type: "POST",
			    url: rootPath + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.sinaUser),
			    success: function(r){
				vm.reload();
				alert(r.msg);
			    	
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: rootPath + "/edu/sinauser_do//remove.do",
				    data : {
                        ids : ids
                    },				   
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(rootPath + "/edu/sinauser_do//get.do?id="+id, function(r){
                vm.sinaUser = r.sinaUser;
            });
		},
		reload: function (event) {
			 var searchParams = $("#searchForm").serializeJson();// 初始化传参数
	            grid.setOptions({
	                data : searchParams
	            });
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
                    name : "",
                    width : "50px",
                    hide : true
                }
            ,								        			{
                    colkey : "userid",
                    name : ""                 
            },							        			{
                    colkey : "uid",
                    name : ""                 
            },							        			{
                    colkey : "gmtCreate",
                    name : ""                 
            },							        			{
                    colkey : "gmtModified",
                    name : ""                 
            }							              ],
        jsonUrl : rootPath + '/edu/sinauser_do/list.do',
        checkbox : true
    });   
});
