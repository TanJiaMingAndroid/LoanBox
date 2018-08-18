package com.ps.loanbox.util;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by 8146 on 2018/1/15.
 * 验证码倒计时
 */

public class CodeCountDownTimer extends CountDownTimer {
    TextView tv;

    public CodeCountDownTimer(long millisInFuture, long countDownInterval, TextView tv) {
        super(millisInFuture, countDownInterval);
        this.tv = tv;
    }

    //计时过程
    @Override
    public void onTick(long millisUntilFinished) {
        tv.setText(millisUntilFinished / 1000 + "s");
        tv.setClickable(false);
    }

    //计时完毕
    @Override
    public void onFinish() {
        tv.setText("Kirim ulang");
        tv.setClickable(true);
    }
}
