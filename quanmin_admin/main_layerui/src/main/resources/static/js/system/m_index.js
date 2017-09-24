layui.define(['layer', 'form'], function(exports){
  var layer = layui.layer
  ,form = layui.form;
  
 	var method_obj = {
		indexFn: function() {
			console.log("我是模块一的一个方法！");
		}
	}
  
  exports('m_index', method_obj); 
});    
     