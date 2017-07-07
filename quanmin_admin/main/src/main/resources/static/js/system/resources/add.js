//$(function() {
//    // 异步加载所有菜单列表
//    $("form").validate({
////        submitHandler : function(form) {// 必须写在验证前面，否则无法ajax提交
////            ly.ajaxSubmit(form, {// 验证新增是否成功
////                type : "post",
////                dataType : "json",
////                success : function(data) {
////                    if (data.msg == "操作成功") {
////                        layer.confirm('添加成功!是否关闭窗口?', function(index) {
////                            parent.grid.loadData();
////                            parent.layer.close(parent.pageii);
////                            return false;
////                        });
////                        $("#form")[0].reset();
////                    } else {
////                        layer.alert('添加失败！', 3);
////                    }
////                }
////            });
////        },
//        rules : {
//            "name" : {
//                required : true,
//                remote : { // 异步验证是否存在
//                    type : "POST",
//                    url : rootPath + '/res/res_do/exist.do',
//                    data : {
//                        name : function() {
//                            return $("#name").val();
//                        }
//                    }
//                }
//            },
//            "reskey" : {
//                required : true,
//                remote : { // 异步验证是否存在
//                    type : "POST",
//                    url : rootPath + '/res/res_do/exist.do',
//                    data : {
//                        reskey : function() {
//                            return $("#reskey").val();
//                        }
//                    }
//                }
//            },
//            "resurl" : {
//                required : true
//            }
//        },
////        messages : {
////            "name" : {
////                required : "菜单名称不能为空",
////                remote : "该菜单名称已经存在"
////            },
////            "reskey" : {
////                required : "菜单标识不能为空",
////                remote : "该标识已经存在"
////            },
////            "resurl" : {
////                required : "url连接不能为空"
////            }
////        },
//        errorPlacement : function(error, element) {// 自定义提示错误位置
//            $(".l_err").css('display', 'block');
//            // element.css('border','3px solid #FFCCCC');
//            $(".l_err").html(error.html());
//        },
//        success : function(label) {// 验证通过后
//            $(".l_err").css('display', 'none');
//        }
//    });
//    var url = rootPath + '/res/res_do/reslists.do';
//    // var data = CommnUtil.ajax(url, null, "json");
//   
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
      //  showBut();
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

  //  $("#divbut").css("display", "none");
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
function add(form) {
    ly.ajaxSubmit(form, {
        type : "post",
        dataType : "json",
        success : function(data) {
            alert(data.msg);
        }
    });
    return false;
}
