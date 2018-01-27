 <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>云报告</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css"/>
  <!-- Font Awesome 字体库 -->
  <link rel="stylesheet" href="/css/font-awesome.css"/>
  <!-- Ionicons -->
  <link rel="stylesheet" href="/css/ionicons.min.css"/>
  <!-- Theme style select2 CSS -->
  <link rel="stylesheet" href="/js/plugins/select2/select2.css"/>
  <link rel="stylesheet" href="/css/AdminLTE.min.css"/>
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="/css/skins/_all-skins.min.css"/>
  <link rel="stylesheet" href="/js/plugins/ztree/css/metroStyle/metroStyle.css"/>
  <link rel="stylesheet" href="/js/plugins/jqgrid/ui.jqgrid-bootstrap.css"/>
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <link rel="stylesheet" href="/js/plugins/treegrid/jquery.treegrid.css"/>
  <!--
 <link rel="stylesheet" href="/css/bootstrap-table.min.css"/>-->
  <link rel="stylesheet" href="/js/plugins/layui/css/layui.css" media="all"/>

  <!-- jQuery 2.2.3 -->
<script src="/js/plugins/jQuery/jquery-2.3.4.min.js"></script>
<script src="/js/plugins/jQuery/jquery.serializejson.js"></script>
<script src="/js/plugins/layui/layui.js" charset="utf-8"></script>

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
<script src="/js/plugins/treegrid/jquery.treegrid.min.js"></script>
<script src="/js/plugins/treegrid/jquery.treegrid.bootstrap3.js"></script>
<script src="/js/plugins/treegrid/jquery.treegrid.extension.js"></script>
<script src="/js/plugins/treegrid/tree.table.js"></script>
<script src="/js/plugins/jqgrid/grid.locale-cn.js"></script>
<script src="/js/plugins/jqgrid/jquery.jqGrid.min.js"></script>
<script src="/js/plugins/ztree/jquery.ztree.all.min.js"></script>
<script src="/js/plugins/select2/select2.full.js"></script>
<!--[if lt IE 9]> <script src="/js/jquery/ie/html5shiv.js"></script> 
<script src="/js/jquery/ie/respond.min.js"></script><![endif]-->


<!-- base end -->
<script type="text/javascript">

//项目目录
var rootPath = "";
//layui分页插件参数常量
var laypagerequest={  pageName: 'curPage' , limitName: "pageSize", method: 'post'};
var laypagerequestget={  pageName: 'curPage' , limitName: "pageSize", method: 'get'};
var laypageresponse= {
                        statusName: 'code' , // 数据状态的字段名称，默认：code                      
                        statusCode: 200,    //状态码
                        msgName: "msg",     //附加消息
                        limitName: 'pageSize', // 每页数据量的参数名，默认：limit                        
                        countName: 'totals', // 数据总数的字段名称，默认：count                        
                        dataName: 'result' // 数据列表的字段名称，默认：data
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