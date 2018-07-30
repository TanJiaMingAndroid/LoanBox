package com.ps.eachgold.popupWindow;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;

import com.ps.eachgold.R;
import com.ps.eachgold.popupWindow.base.BasePopupWindow;

/**
 * Created by 8146 on 2018/1/27.
 */

public class SpinnerPop extends BasePopupWindow implements View.OnClickListener {

    private View popupView;
    private Activity mActivity;


    public SpinnerPop(Activity context) {

        super(context);
        mActivity = context;

        bindEvent();
    }

    @Override
    protected Animation initShowAnimation() {
        return getTranslateAnimation(250 * 2, 0, 300);
    }

    @Override
    public View getClickToDismissView() {
        return popupView.findViewById(R.id.click_to_dismiss);
    }

    @Override
    public View onCreatePopupView() {
        popupView = LayoutInflater.from(getContext()).inflate(R.layout.pop_spinner, null);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }

    private void bindEvent() {
        if (popupView != null) {
            popupView.findViewById(R.id.online).setOnClickListener(this);
            popupView.findViewById(R.id.phone).setOnClickListener(this);
            popupView.findViewById(R.id.help).setOnClickListener(this);
            popupView.findViewById(R.id.cancel).setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.online:


                break;
            case R.id.phone:
                dismiss();

                break;
            case R.id.help:

                dismiss();
                break;
            case R.id.cancel:
                dismiss();
                break;
            default:
                break;
        }

    }

    private onClickPopuListener mListener;

    public interface onClickPopuListener {
        void clickLineService();
    }

    public void setOnClickPopuListener(onClickPopuListener listener) {
        mListener = listener;
    }
}
