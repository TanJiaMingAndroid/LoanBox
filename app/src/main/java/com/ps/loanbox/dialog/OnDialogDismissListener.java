package com.ps.loanbox.dialog;

/**
 * 自定义对话框消失弹框监听回调   一般用来处理一些延迟消失对话框的问题  比如 网络请求成功 立即销毁界面
 */

public interface OnDialogDismissListener {
    /*普通回调*/
    void onDismiss(TDialog dialog);

    /*回调时传递参数*/
    void onDismiss(TDialog dialog, int type);


}
