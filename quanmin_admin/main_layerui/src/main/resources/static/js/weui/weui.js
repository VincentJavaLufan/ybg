/** *微信Ui调用通用的Js 严宇* */
/** ***** 简单弹出 ** */
function wxsimplealert(body) {
    wxalert('', body, '确定');
}
/** 弹出警告 title 标题 body 警告的内容 ,确认键的内容 *** */
function wxalert(title, body, enter) {
    $('.weui_dialog_alert').css("display", "block");
    $('#wxalertbd').html(body);
    $('#wxalerttitle').html(title);
    $('#wxalertenter').html(enter);
    setwxalertenter('wxclosealert()');// 添加默认的方法
}
/** 设置弹出框 确定的函数 一般是关闭 * */
function setwxalertenter(mothodname) {
    $('#wxalertenter').attr('onclick', mothodname);
}
/** 关闭警告 * */
function wxclosealert() {
    $('.weui_dialog_alert').css("display", "none");
    $('#wxalertbd').html('');
    $('#wsalerttitle').html('');
    $('#wxalertenter').html('');
}
/** 弹出等待 * */
function wxloading(title) {
    $('#loadingToast').css("display", "block");
    $('#wxloading').html(title);
}
/** 关闭等待 * */
function wxcloseloading() {
    $('#loadingToast').css("display", "none");
    $('#wxloading').html("");
}
/** toast 弹出 * */
function wxtoast(title) {
    $('#toast').css("display", "block");
    $('#wxtoastbd').html(title);
    setTimeout('wxclosetoast()', 1000); // 延迟一秒关闭
}
/** toast 关闭 * */
function wxclosetoast() {
    $('#toast').css("display", "none");
    $('#wxtoastbd').html("");
}
/** 类似confirm 参数：[标题]，[内容],[取消键文字],[确认键文字] * */
function wxconfirm(title, body, cancel, yes) {
    $('#wxconfirmtitle').html(title);
    $('#wxconfirmbd').html(body);
    $('#wxwxconfirmno').html(cancel);
    $('#wxwxconfirmyes').html(yes);
    // 取消键默认方法 关闭窗口
    setwxconfirmno('wxcloseconfirm()');
}
/** 类似confirm 参数：[标题]，[内容] * */
function wxsimpleconfirm(title, body) {
    wxconfirm(title, body, "取消", "确定");
    $('.weui_dialog_confirm').css("display", "block");
    // 取消键默认方法 关闭窗口
    setwxconfirmno('wxcloseconfirm()');
}
/** * 关闭confirm窗口 * */
function wxcloseconfirm() {
    $('.weui_dialog_confirm').css("display", "none");
}
/** * 设置confirm 确定执行的函数 ** */
function setwxconfirmyes(mothodname) {
    $("#wxconfirmyes").attr('onclick', mothodname);
}
/** * 设置confirm 取消键执行的函数 一般是关闭窗口 ** */
function setwxconfirmno(mothodname) {
    $("#wxconfirmno").attr('onclick', mothodname);
}
/** 微信跳转* */
function wxhref(url) {
    wxloading("页面跳转中");
    setTimeout(function() {
        location.href = url;
    }, 100);
}
