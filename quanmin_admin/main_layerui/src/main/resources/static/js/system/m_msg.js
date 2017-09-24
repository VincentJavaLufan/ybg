layui.define(['layer', 'form'], function(exports) {
	var layer = layui.layer,
		form = layui.form;

	var method_obj = {
		m_msgFn: function() {
			console.log("我是模块二的一个方法！");
		}
	}

	exports('m_msg', method_obj);
});