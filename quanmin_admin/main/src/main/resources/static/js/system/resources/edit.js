//$(function() {
//    // 异步加载所有菜单列表
//    $("form").validate({
//        submitHandler : function(form) {// 必须写在验证前面，否则无法ajax提交
//            ly.ajaxSubmit(form, {// 验证新增是否成功
//                type : "post",
//                dataType : "json",
//                success : function(data) {
//                    if (data.msg == "操作成功") {
//                        layer.confirm('编辑成功!是否关闭窗口?', function(index) {
//                            parent.grid.loadData();
//                            parent.layer.close(parent.pageii);
//                            return false;
//                        });
//                    } else {
//                        layer.alert('编辑失败！', 3);
//                    }
//                }
//            });
//        },
//        rules : {
//            reskey : {
//                required : true
//            },
//            resurl : {
//                required : true
//            }
//        },
//        messages : {
//            reskey : {
//                required : "菜单标识不能为空"
//            },
//            resurl : {
//                required : "url连接不能为空"
//            }
//        },
//        errorPlacement : function(error, element) {// 自定义提示错误位置
//            $(".l_err").css('display', 'block');
//            // element.css('border','3px solid #FFCCCC');
//            $(".l_err").html(error.html());
//        },
//        success : function(label) {// 验证通过后
//            $(".l_err").css('display', 'none');
//        }
//    });
//});
function but(v) {
    if (v.value == 2) {
        var url = rootPath + '/res/res_do/reslists.do?type=1';
        var data = CommnUtil.ajax(url, null, "json");
        var h = "";
        for (var i = 0; i < data.length; i++) {
            h += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
        }
        $("#parentid").html(h);
       // showBut();
    } else
        if (v.value == 1) {
            var url = rootPath + '/res/res_do/reslists.do?type=0';
            var data = CommnUtil.ajax(url, null, "json");
            var h = "";
            for (var i = 0; i < data.length; i++) {
                h += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
            }
            $("#parentid").html(h);
        } else {
            $("#parentid").html("<option value='0'>------顶级目录------</option>");
            $("#divbut").css("display", "none");
        }
}
function toBut(b) {
    $("#description").val($("#" + b.id).html());
}
function showBut() {
    $("#divbut").css("display", "none");
//    var url = rootPath + '/res/res_do/findByButtom.do';
//    var data = CommnUtil.ajax(url, null, "json");
//    if (data != null) {
//        var bb = $("#but");
//        bb.html('');
//        for (var i = 0; i < data.length; i++) {
//            bb.append("<span onclick=\"toBut(this)\" id=\"span_" + data[i].id + "\">" + data[i].button + "</span>");
//        }
//    } else {
//        layer.msg("获取按扭列表失败！");
//    }
}
function byRes(id) {
    var url = rootPath + '/res/res_do/reslists.do';
    var data = CommnUtil.ajax(url, null, "json");
    if (data != null) {
        var h = "<option value='0'>------顶级目录------</option>";
        for (var i = 0; i < data.length; i++) {
            if (parseInt(id, 10) == parseInt(data[i].id, 10)) {
                h += "<option value='" + data[i].id + "' selected='selected'>" + data[i].name + "</option>";
            } else {
                h += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
            }
        }
        $("#parentid").html(h);
    } else {
        bootbox.alert("获取菜单信息错误，请联系管理员！");
    }
}
function update(form) {
    ly.ajaxSubmit(form, {// 验证新增是否成功
        type : "post",
        dataType : "json",
        success : function(data) {
            alert(data.msg);
        }
    });
    return false;
}
