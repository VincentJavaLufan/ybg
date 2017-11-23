 <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>云报告</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="/css/font-awesome.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="/css/skins/_all-skins.min.css">
  <link rel="stylesheet" href="/js/plugins/ztree/css/metroStyle/metroStyle.css">
  <link rel="stylesheet" href="/js/plugins/jqgrid/ui.jqgrid-bootstrap.css">
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <link rel="stylesheet" href="/js/plugins/treegrid/jquery.treegrid.css">
 <link rel="stylesheet" href="/css/bootstrap-table.min.css">
  <!-- jQuery 2.2.3 -->
<script src="/js/plugins/jQuery/jquery-2.3.4.min.js"></script>
<!-- AdminLTE App -->
<script src="/js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="/js/demo.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/js/libs/bootstrap.min.js"></script>
<script src="/js/libs/bootstrap-table.min.js"></script>
<script type="text/javascript" src="/common/vue.js"></script>
<script src="/common/vue-router.js"></script>
<!-- Slimscroll -->
<script src="/js/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="/js/plugins/fastclick/fastclick.js"></script>

<script type="text/javascript" src="/js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/js/jquery/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="/js/jquery/jquery-validation/messages_cn.js"></script>
<script type="text/javascript" src="/js/layer-v1.9.2/layer/layer.js"></script>
<script type="text/javascript" src="/common/lyGrid.js"></script>



<script src="/js/plugins/treegrid/jquery.treegrid.min.js"></script>
<script src="/js/plugins/treegrid/jquery.treegrid.bootstrap3.js"></script>
<script src="/js/plugins/treegrid/jquery.treegrid.extension.js"></script>
<script src="/js/plugins/treegrid/tree.table.js"></script>
<script src="/js/plugins/jqgrid/grid.locale-cn.js"></script>
<script src="/js/plugins/jqgrid/jquery.jqGrid.min.js"></script>
<script src="/js/plugins/ztree/jquery.ztree.all.min.js"></script>


<!--[if lt IE 9]> <script src="/js/jquery/ie/html5shiv.js"></script> 
<script src="/js/jquery/ie/respond.min.js"></script><![endif]-->

<style type="text/css">
.l_err{
    background: none repeat scroll 0 0 #FFFCC7;
    border: 1px solid #FFC340;
    font-size: 12px;
    padding: 4px 8px;
    width: 200px;
    display: none;
}
.error{
  border: 3px solid #FFCCCC;
}
.form-group{
  padding-left: 15px
}
.left{
	text-align: left;
	padding-left: 10px;
}
.right{
	text-align: right;
}
</style>
<!-- base end -->
<script type="text/javascript">
function onloadurl(){
	$("[data-url]").each(function () {
		var tb = $(this);
		tb.html(CommnUtil.loadingImg());
		tb.load(rootPath+tb.attr("data-url"));
    });
}
layer.config({
		extend : [ 'style.css' ], //加载新皮肤skin/style.css
		fix : false, // 用于设定层是否不随滚动条而滚动，固定在可视区域。
		skin : 'layui-layer-molv' //一旦设定，所有弹层风格都采用此主题。layui-layer-molv
	});
var rootPath = "";
/**
 * options:
 * url : 获取select数据的路径
 * name : input name
 * textFiled :显示
 * valueFiled:value
 * data : 查询条件{}
 * value ：默认值
 */
function getSelectEx(byId,options){
	if(byId && options){
		if(options.url && options.textFiled 
				&& options.valueFiled && options.name){
			$.ajax({
				type : "post", //使用get方法访问后台
			    dataType : "json", //json格式的数据
			    async : true, //同步   不写的情况下 默认为true
			    url : rootPath + options.url,
			    data : options.data,
			    success : function(data){
			    	for(var i = 0; i < data.length;i++){
				    	var selectObj = $("#"+byId).find("ul"); 
				    	if(selectObj){
				    		if(options.value == "" && i == 0){
				    			$("#"+byId).append("<button data-toggle='dropdown' class='btn btn-sm btn-default dropdown-toggle'> "+
										"<span class='dropdown-label'>"+data[i][options.textFiled]+"</span> <span class='caret'></span></button>");
				    			$("#"+byId).find("ul").append("<li class='active'><a href='#'><input type='radio' name="+options.name+
							    		" value="+data[i][options.valueFiled]+" checked='checked'>"+data[i][options.textFiled]+"</a></li>");
						    }else{
						    	 if(options.value == data[i][options.valueFiled]){
						    		    $("#"+byId).append("<button data-toggle='dropdown' class='btn btn-sm btn-default dropdown-toggle'> "+
												"<span class='dropdown-label'>"+data[i][options.textFiled]+"</span> <span class='caret'></span></button>");
								    	$("#"+byId).find("ul").append("<li class='active'><a href='#'><input type='radio' name="+options.name+
									    		" value="+data[i][options.valueFiled]+" checked='checked'>"+data[i][options.textFiled]+"</a></li>");
								 }else{
									 $("#"+byId).find("ul").append("<li class=''><a href='#'><input type='radio' name="+options.name+
									    		" value="+data[i][options.valueFiled]+" >"+data[i][options.textFiled]+"</a></li>");
								 }
							}
					    }	
			    	}	
				}
			});
			
		}
	}
}
</script>
<script type="text/javascript" src="/common/common.js"></script>
<script type="text/javascript" src="/common/base.js"></script>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?577038104fe0a8e00d90faee2f037aac";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
<script>
  	var _mtac = {};
  	(function() {
  		var mta = document.createElement("script");
  		mta.src = "http://pingjs.qq.com/h5/stats.js?v2.0.4";
  		mta.setAttribute("name", "MTAH5");
  		mta.setAttribute("sid", "500553015");

  		var s = document.getElementsByTagName("script")[0];
  		s.parentNode.insertBefore(mta, s);
  	})();
</script>
<script type="text/javascript" src="http://tajs.qq.com/stats?sId=64208831" charset="UTF-8"></script>