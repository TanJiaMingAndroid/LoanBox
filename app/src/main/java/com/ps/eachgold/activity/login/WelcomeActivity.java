package com.ps.eachgold.activity.login;


import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.TelephonyManager;

import com.ps.eachgold.R;
import com.ps.eachgold.activity.BaseActivity;
import com.ps.eachgold.gate.Main2Activity;
import com.ps.eachgold.activity.MainActivity;
import com.ps.eachgold.bean.BannerBean;
import com.ps.eachgold.contract.login.WelcomeContract;
import com.ps.eachgold.presenter.WelcomePresenter;
import com.ps.eachgold.util.SPutils;
import com.ps.eachgold.util.T;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.List;

/**
 * Created by 8146 on 2017/1/12.
 * 启动页-页面
 *
 *
 * 因上架问题。
 */
public class WelcomeActivity extends BaseActivity implements WelcomeContract.View {
    //P层
    private WelcomePresenter mPresenter;
    //广告页地址
    private String imgUrl = "";
    //广告页点击地址
    private String clickUrl = "";
    //基础图片域名
    private String mbaseUrl = "";
    //计时器
    private CountDownTimer countDownTimer;
    //imei 号
    private String imei;
    //是否获取到 基础图片域名  的判断
    private boolean imgUrlFlag=false;
    //是否获取到 广告页地址  的判断
    private boolean AdImgFlag=false;
    //倒计时结束判断-处理倒计时结束后 网络状态原因 未能及时获取数据的处理
    private boolean checkFlag=false;

    //判断是否加 壳子
    private boolean  gate=false;

    @Override
    protected void initVariables() {
        countDownTimer = new CountDownTimer(2400, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }
            @Override
            public void onFinish() {
                checkFirst();
            }
        };
    }

    @Override
    protected void initPresenter() {
        mPresenter = new WelcomePresenter(this, this);

    }

    @Override
    public int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        initData();
        //如果设置    targetSdkVersion >-23 会执行下面的流程 ,现设置22.暂不使用
        if (Build.VERSION.SDK_INT >= 23) {
            AndPermission.with(this)
                    .requestCode(200)
                    .permission(Manifest.permission.READ_PHONE_STATE)
                    .rationale(new RationaleListener() {
                        @Override
                        public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                            AndPermission.rationaleDialog(WelcomeActivity.this, rationale).show();
                        }
                    })
                    .callback(listener)
                    .start();
        } else {
            getImei();
            countDownTimer.start();
        }
    }

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。

            if (requestCode == 200) {
                getImei();
                countDownTimer.start();
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if (requestCode == 200) {
                // TODO ...
            }
        }
    };

    private void getImei() {
        imei = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE))
                .getDeviceId();
    }

    private void initData() {
        mPresenter.getImgUrl();
        mPresenter.getAdImg();
        mPresenter.openGate();
    }

    @Override
    public boolean isUseButterKnife() {
        return true;
    }

    @Override
    protected boolean isAllowFullScreen() {
        return true;
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

    //检测是否第一次登录（目前没有 引导页-故无第一次登录跳引导页的的判断）
    @Override
    public void checkFirst() {
        //倒计时结束 如果广告图片不为空 则跳转广告页
        //如果请求完 没获取到 超时  则等待
        checkFlag=true;
        if(imgUrlFlag){
            if(gate){
                Main2Activity.createActivity(this, 0);
                checkFlag=false;
            }else {
                if(AdImgFlag){
                    AdActivity.createActivity(this, imgUrl, clickUrl, mbaseUrl);
                    finish();
                    checkFlag=false;
                }else {
                    MainActivity.createActivity(this, 0);
                    checkFlag=false;
                }
            }

        }
      //  isFirst = (Boolean) SPutils.get(this, "First", true);

    }

    @Override
    public void getImgUrlSuccess(String baseUrl) {
        SPutils.put(this, "baseImgUrl", baseUrl);
        imgUrlFlag=true;
        mbaseUrl = baseUrl;
        if(checkFlag){
            if(gate){
                Main2Activity.createActivity(this, 0);
                checkFlag=false;
                imgUrlFlag=false;
            }else {
                MainActivity.createActivity(this, 0);
                checkFlag=false;
                imgUrlFlag=false;
            }

        }

    }

    @Override
    public void getAdImgSuccess(List<BannerBean> list) {
        if (list.size() > 0) {
            AdImgFlag=true;
            if (list.get(0).getPic() != null)
                imgUrl = list.get(0).getPic();
            if (list.get(0).getUrl() != null)
                clickUrl = list.get(0).getUrl();
        }
    }

    @Override
    public void openGateSuccess(String result) {
       // （uploading：审核中；uploaded：审核通过）
        if("uploading".equals(result)){
            gate=true;
        }else if("uploaded".equals(result)){
            gate=false;
        }
        if(checkFlag&&imgUrlFlag){
            if(gate){
                Main2Activity.createActivity(this, 0);
                checkFlag=false;
                imgUrlFlag=false;
            }else {
                MainActivity.createActivity(this, 0);
                checkFlag=false;
                imgUrlFlag=false;
            }
        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetach();
        }
        if (countDownTimer != null)
            countDownTimer.cancel();
    }

}
