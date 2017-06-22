<!--   微信tips模板  -->


<!--  Toast   -->
<div id="toast" style="display: none;">
    <div class="weui_mask_transparent"></div>
    <div class="weui_toast">
        <i class="weui_icon_toast"></i>
        <p class="weui_toast_content" id="wxtoastbd">已完成</p>
    </div>
</div>

<!-- 正在加载中  -->

<div id="loadingToast" class="weui_loading_toast" style="display:none;">
    <div class="weui_mask_transparent"></div>
    <div class="weui_toast">
        <div class="weui_loading">
            <!-- :) -->
            <div class="weui_loading_leaf weui_loading_leaf_0"></div>
            <div class="weui_loading_leaf weui_loading_leaf_1"></div>
            <div class="weui_loading_leaf weui_loading_leaf_2"></div>
            <div class="weui_loading_leaf weui_loading_leaf_3"></div>
            <div class="weui_loading_leaf weui_loading_leaf_4"></div>
            <div class="weui_loading_leaf weui_loading_leaf_5"></div>
            <div class="weui_loading_leaf weui_loading_leaf_6"></div>
            <div class="weui_loading_leaf weui_loading_leaf_7"></div>
            <div class="weui_loading_leaf weui_loading_leaf_8"></div>
            <div class="weui_loading_leaf weui_loading_leaf_9"></div>
            <div class="weui_loading_leaf weui_loading_leaf_10"></div>
            <div class="weui_loading_leaf weui_loading_leaf_11"></div>
        </div>
        <p class="weui_toast_content" id="wxloading" >数据加载中</p>
    </div>
</div>




<!-- 类似alert的 警告-->
<div class="weui_dialog_alert" style="display:none;">
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd">
          <strong class="weui_dialog_title" > <div id="wxalerttitle" style="inline"> 弹窗标题</div>  </strong>
      </div>
        <div class="weui_dialog_bd" id="wxalertbd">弹窗内容，告知当前页面信息等</div>
        <div class="weui_dialog_ft">
            <a href="javascript:;" class="weui_btn_dialog primary" id="wxalertenter">确定</a>
        </div>
    </div>
</div>

<!-- 类似confirm 警告 -->
<div class="weui_dialog_confirm" style="display:none;">
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd">
          <strong class="weui_dialog_title"  id="wxconfirmtitle" >弹窗标题</strong>
        </div>
        <div class="weui_dialog_bd" id="wxconfirmbd">自定义弹窗内容<br>...</div>
        <div class="weui_dialog_ft">
            <a href="javascript:;" class="weui_btn_dialog default" id="wxconfirmno">取消</a>
            <a href="javascript:;" class="weui_btn_dialog primary" id="wxconfirmyes">确定</a>
        </div>
    </div>
</div>
