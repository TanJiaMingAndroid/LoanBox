package com.ps.loanbox.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


/*
* 该框架经过自己封装  资源文件下有部分可以直接使用的dialog布局
*
* */

/**
 * 1.0.0版本: 弹窗实现基本功能
 * OnBindViewListener
 * 1.1.0版本: 添加点击事件封装回调方法
 * addOnClickListener()
 * setOnViewClickListener()
 * 1.2.0版本:
 * 分离出列表弹窗TListDialog
 * 解决弹窗按Home键时出现的bug
 * 1.3.0版本:
 * 处理setCancelable()方法,禁止弹窗点击取消
 * 弹窗内容直接传入View: setDialogView()
 * 1.3.1版本:
 * 添加弹窗隐藏时回调监听方法:setOnDismissListener()
 *
 * @author Timmy
 * @time 2018/1/4 16:28
 * @GitHub https://github.com/Timmy-zzh/TDialog
 **/
public class TDialog extends BaseDialogFragment {


    /*dialog显示多长时间  用于计算 延迟消失*/
    private long duration = 0;

    private static final String KEY_TCONTROLLER = "TController";
    protected TController tController;

    public TDialog() {
        tController = new TController();
    }


    /**
     * 当设备旋转时,会重新调用onCreate,进行数据恢复
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            tController = (TController) savedInstanceState.getSerializable(KEY_TCONTROLLER);
        }
    }

    /**
     * 进行数据保存
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(KEY_TCONTROLLER, tController);
        super.onSaveInstanceState(outState);
    }

    /**
     * 弹窗消失时回调方法
     */
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        DialogInterface.OnDismissListener onDismissListener = tController.getOnDismissListener();
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }

    /**
     * 获取弹窗xml布局界面
     */
    @Override
    protected int getLayoutRes() {
        return tController.getLayoutRes();
    }

    @Override
    protected View getDialogView() {
        return tController.getDialogView();
    }

    @Override
    protected void bindView(View view) {
        //控件点击事件处理
        BindViewHolder viewHolder = new BindViewHolder(view, this);
        if (tController.isCancelable() && tController.getIds() != null && tController.getIds().length > 0) {
            for (int id : tController.getIds()) {
                viewHolder.addOnClickListener(id);
            }
        }
        //回调方法获取到布局,进行处理
        if (tController.getOnBindViewListener() != null) {
            tController.getOnBindViewListener().bindView(viewHolder);
        }
    }


    @Override
    public int getGravity() {
        return tController.getGravity();
    }

    @Override
    public float getDimAmount() {
        return tController.getDimAmount();
    }

    @Override
    public int getDialogHeight() {
        return tController.getHeight();
    }

    @Override
    public int getDialogWidth() {
        return tController.getWidth();
    }

    @Override
    public String getFragmentTag() {
        return tController.getTag();
    }

    public OnViewClickListener getOnViewClickListener() {
        return tController.getOnViewClickListener();
    }

    @Override
    public boolean isCancelable() {
        return tController.isCancelable();
    }

    @Override
    protected boolean isCancelableOutside() {
        return tController.isCancelableOutside();
    }

    public TDialog show() {
        //如果宽高都没有设置,则默认给弹窗提供宽度为800
        if (tController.getWidth() <= 0 && tController.getHeight() <= 0) {
            tController.setWidth(600);
        }
        Log.d(TAG, "show");
        show(tController.getFragmentManager());
        duration = System.currentTimeMillis();
        return this;
    }

    /*********************************************************************
     * 使用Builder模式实现
     *
     */
    public static class Builder {

        TController.TParams params;

        public Builder(FragmentManager fragmentManager) {
            params = new TController.TParams();
            params.mFragmentManager = fragmentManager;
        }

        //各种setXXX()方法设置数据
        public Builder setLayoutRes(@LayoutRes int layoutRes) {
            params.mLayoutRes = layoutRes;
            return this;
        }

        public Builder setDialogView(View view) {
            params.mDialogView = view;
            return this;
        }

        /**
         * 设置弹窗宽度是屏幕宽度的比例 0 -1
         */
        public Builder setScreenWidthAspect(Activity activity, float widthAspect) {
            params.mWidth = (int) (getWindowWidth(activity) * widthAspect);
            return this;
        }

        public Builder setWidth(int widthPx) {
            params.mWidth = widthPx;
            return this;
        }

        /**
         * 设置屏幕高度比例 0 -1
         */
        public Builder setScreenHeightAspect(Activity activity, float heightAspect) {
            params.mHeight = (int) (getWindowHeight(activity) * heightAspect);
            return this;
        }

        public Builder setHeight(int heightPx) {
            params.mHeight = heightPx;
            return this;
        }

        public Builder setGravity(int gravity) {
            params.mGravity = gravity;
            return this;
        }

        public Builder setCancelableOutside(boolean cancel) {
            params.mIsCancelableOutside = cancel;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            params.mCancelable = cancelable;
            return this;
        }

        @Deprecated
        public Builder setOnDismissListener(DialogInterface.OnDismissListener dismissListener) {
            params.mOnDismissListener = dismissListener;
            return this;
        }

        public Builder setOnDismissListener(OnDialogDismissListener listener) {
            params.onDialogDismissListener = listener;
            return this;
        }

        public Builder setDimAmount(float dim) {
            params.mDimAmount = dim;
            return this;
        }

        public Builder setTag(String tag) {
            params.mTag = tag;
            return this;
        }

        public Builder setOnBindViewListener(OnBindViewListener listener) {
            params.bindViewListener = listener;
            return this;
        }

        public Builder addOnClickListener(int... ids) {
            params.ids = ids;
            return this;
        }

        public Builder setOnViewClickListener(OnViewClickListener listener) {
            params.mOnViewClickListener = listener;
            return this;
        }

        public TDialog create() {
            TDialog dialog = new TDialog();
            Log.d(TAG, "create");
            //将数据从Buidler的DjParams中传递到DjDialog中
            params.apply(dialog.tController);
            return dialog;
        }
    }

    Handler handler = new Handler();

    /*loading显示框*/
    /*自定义消失弹框
    * 如果 message 不等于空 那么会toast一句话不然就不会提示
    *如要在加载成功后销毁界面等操作  请务必写在对话框消失监听回调
    * */
    public TDialog dismissLoading(final String message, final int type) {
        long currentDate = System.currentTimeMillis();
        long date;
        if (currentDate - duration < 2000) {
            date = 2000 - (currentDate - duration);
        } else {
            date = 0;
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                OnDialogDismissListener listener = tController.getDismissListener();
                if (listener != null) {
                    listener.onDismiss(TDialog.this, type);
                }
                if(!TextUtils.isEmpty(message)){
                    Toast.makeText(getActivity(),message, Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        }, date);//3秒后执行Runnable中的run方法
        return this;
    }


    /*loading显示框*/
    /*自定义消失弹框
    * 如果 message 不等于空 那么会toast一句话不然就不会提示
    *如要在加载成功后销毁界面等操作  请务必写在对话框消失监听回调
    * */
    public TDialog dismissLoading(final String message) {
        long currentDate = System.currentTimeMillis();
        long date;
        if (currentDate - duration < 2000) {
            date = 2000 - (currentDate - duration);
        } else {
            date = 0;
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                OnDialogDismissListener listener = tController.getDismissListener();
                if (listener != null) {
                    listener.onDismiss(TDialog.this);
                }
                if(!TextUtils.isEmpty(message)){
                    Toast.makeText(getActivity(),message, Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        }, date);//3秒后执行Runnable中的run方法
        return this;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacks(null);
            handler = null;
        }
    }
}
