////单独验证某一个input  class="checkpass"
//
//$(function() {
//    $("form").validate({
//        submitHandler : function(form) {// 必须写在验证前面，否则无法ajax提交
//            ly.ajaxSubmit(form, {// 验证新增是否成功
//                type : "post",
//                dataType : "json",
//                success : function(data) {
//                    alert(data.msg);
//                    parent.layer.close(parent.pageii);
//                    parent.grid.loadData();
//                }
//            });
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
function add(form) {
    ly.ajaxSubmit(form, {// 验证新增是否成功
        type : "post",
        dataType : "json",
        success : function(data) {
            alert(data.msg);
        }
    });
    return false;
}