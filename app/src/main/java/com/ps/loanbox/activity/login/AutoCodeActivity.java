package com.ps.loanbox.activity.login;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ps.loanbox.R;
import com.ps.loanbox.activity.BaseActivity;
import com.ps.loanbox.activity.MainActivity;
import com.ps.loanbox.bean.Header;
import com.ps.loanbox.bean.LoginBean;
import com.ps.loanbox.contract.login.AutoCodeContract;
import com.ps.loanbox.presenter.AutoCodePresenter;
import com.ps.loanbox.util.SPutils;
import com.ps.loanbox.util.T;
import com.tuo.customview.VerificationCodeView;

import butterknife.BindView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

//tjm 接收验证码页面
@RuntimePermissions
public class AutoCodeActivity extends BaseActivity implements AutoCodeContract.View {

    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.icv)
    VerificationCodeView icv;
    @BindView(R.id.bt_login_code_go)
    Button btLoginCodeGo;
    @BindView(R.id.tv_resend_code)
    TextView tvResendCode;
    private AutoCodePresenter autoCodePresenter;
    private String phone, valCode;
    private boolean isLogin = false;
    private boolean info = false;

    //跳转
    public static void createActivity(Context context, String phone) {
        Intent intent = new Intent(context, AutoCodeActivity.class);
        intent.putExtra("phone", phone);
        context.startActivity(intent);
    }

    @Override
    protected void initVariables() {
        phone = getIntent().getStringExtra("phone");
    }

    @Override
    protected void initPresenter() {
        autoCodePresenter = new AutoCodePresenter(this, this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_auto_code;
    }

    @OnClick({R.id.bt_login_code_go, R.id.left_icon, R.id.tv_resend_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_icon:
                finish();
                break;
            case R.id.bt_login_code_go:
                //登录按钮
                if (TextUtils.isEmpty(getCode())){
                    T.showShort("Silahkan masukkan kode verifikasi");
                }else if (getCode().length()!=4){
                    T.showShort("No OTP salah");
                }else {
                AutoCodeActivityPermissionsDispatcher.loginWithPermissionCheck(this);}
                break;
            case R.id.tv_resend_code:
                autoCodePresenter.sendCode();
                break;

        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        autoCodePresenter.sendCode();
    }


    @Override
    public boolean isUseButterKnife() {
        return true;
    }

    @Override
    protected boolean isAllowFullScreen() {
        return false;
    }

    @Override
    public void getError(Throwable e) {

    }

    @Override
    public void showMyProgressDialog(String message) {

    }

    @Override
    public void hiddenProgressDialog() {

    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public String getCode() {
        return icv.getInputContent();
    }

    @Override
    public TextView getLoginTv() {
        return null;
    }

    @Override
    public TextView getSendCode() {
        return tvResendCode;
    }

    @Override
    public void loginSuccess(final LoginBean bean, Header header) {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        Log.e("AutoCodeActivity","run(AutoCodeActivity.java:153)"+bean.getSessionid());
                        Log.e("AutoCodeActivity","run(AutoCodeActivity.java:154)"+bean.getUserinfo().getPhone());
                        SPutils.put(AutoCodeActivity.this, "sessionid", bean.getSessionid());
                        SPutils.put(AutoCodeActivity.this, "phone", bean.getUserinfo().getPhone());
                        SPutils.put(AutoCodeActivity.this, "login", true);
                        SPutils.put(AutoCodeActivity.this, "info", true);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                MainActivity.createActivity(AutoCodeActivity.this, 0);
                            }
                        });
                    }
                }

        ).start();
    }

    @NeedsPermission(Manifest.permission.READ_PHONE_STATE)
    void login() {
        autoCodePresenter.CodeLogin();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoCodeActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnPermissionDenied(Manifest.permission.READ_PHONE_STATE)
    void loginDenied() {
    }

    @OnNeverAskAgain(Manifest.permission.READ_PHONE_STATE)
    void loginNeverAsk() {
    }
}
