package com.ps.eachgold.popupWindow;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.ps.eachgold.R;
import com.ps.eachgold.popupWindow.base.BasePopupWindow;

/**
 * Created by 8146 on 2018/1/19.
 */

public class WaittingProgressPop extends BasePopupWindow {

    private TextView loading_content;

    private Activity mcontext;

    public WaittingProgressPop(Activity context,String content) {
        super(context);
        this.mcontext = context;

        loading_content = (TextView) findViewById(R.id.loading_content);

        loading_content.setText(content);

    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.popup_loading);
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.login_dialog_popup_anima);
    }

    @Override
    protected Animation initShowAnimation() {
        return null;
    }

    @Override
    public View getClickToDismissView() {
        return null;
    }
}