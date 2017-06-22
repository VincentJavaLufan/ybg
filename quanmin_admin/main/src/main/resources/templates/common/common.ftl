<title>云 报告</title>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<!-- name="viewport" 的详细用法  http://www.php100.com/html/webkaifa/HTML5/2012/0831/10979.html -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="/favicon.ico" type="image/x-icon" rel="shortcut icon">
<!-- 
width - viewport的宽度 height - viewport的高度
initial-scale - 初始的缩放比例
minimum-scale - 允许用户缩放到的最小比例
maximum-scale - 允许用户缩放到的最大比例
user-scalable - 用户是否可以手动缩放
 -->
<link rel="stylesheet" href="/notebook/notebook_files/font.css" type="text/css">
<link rel="stylesheet" href="/notebook/notebook_files/app.v1.css" type="text/css">
<link rel="stylesheet" href="/admin_files/awesome.css" type="text/css">
<link rel="stylesheet" href="/css/lanyuan.css" type="text/css">
	<!-- base start 重要部分不可删改-->
<script type="text/javascript" src="/notebook/notebook_files/app.v1.js"></script>
<script type="text/javascript" src="/notebook/notebook_files/app.plugin.js"></script>
<script type="text/javascript" src="/js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/js/jquery/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="/js/jquery/jquery-validation/messages_cn.js"></script>
<script type="text/javascript" src="/js/layer-v1.9.2/layer/layer.js"></script>
<script type="text/javascript" src="/common/common.js"></script>
<script type="text/javascript" src="/common/lyGrid.js"></script>
<script type="text/javascript" src="/common/base.js"></script>
<!--[if lt IE 9]> <script src="/js/jquery/ie/html5shiv.js"></script> <script src="/js/jquery/ie/respond.min.js"></script><![endif]-->
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