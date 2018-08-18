package com.ps.loanbox.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.dou361.dialogui.DialogUIUtils;
import com.jph.takephoto.app.TakePhotoActivity;
import com.ps.loanbox.activity.login.WelcomeActivity;
import com.ps.loanbox.util.ActivityCollector;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 8146 on 2017/1/12.
 * Activity-页面-基类
 */
public abstract class BasePhotoActivity extends TakePhotoActivity {
    private Unbinder unbinder;
    private boolean useBuffer=false;
    private Dialog dialog;
  //  public static int PERMISSION = 1;
    /**
     * 权限列表
     */
   // private ArrayList<String> mPermissionList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if(!this.isTaskRoot()){
            Intent mainIntent = getIntent();
            String action = mainIntent.getAction();
            if(mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER)&&action.equals(Intent.ACTION_MAIN)){
                finish();
                return;
            }
        }
      //  App.mAppCompatActivity=this;
       // setFullScreen();

        if (null != savedInstanceState) {
            Intent intent = new Intent(this, WelcomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return;
        }
        //requestPermission();
        if(getLayout()==0){
            throw new IllegalArgumentException("111");
        }
        initVariables();
        setContentView(getLayout());
        useBuffer= isUseButterKnife();
        if(useBuffer){
            unbinder= ButterKnife.bind(this);
        }
        ActivityCollector.getInstance().addActivity(this);
        initPresenter();
        initView(savedInstanceState);


    }

    protected  void setFullScreen(){
      if (Build.VERSION.SDK_INT >= 21) {
          View decorView = getWindow().getDecorView();
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }

    }
    /**
     * 初始化变量，intent携带的数据和Activity的变量
     * @return
     */
    protected abstract void initVariables();


    /**
     * 初始化p层
     * @return
     */
    protected abstract void initPresenter();


    /**
     * 布局文件
     * @return
     */
    public abstract int getLayout();

    /**
     * 初始化
     * @return
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 是否使用注解
     * @return
     */
    public abstract boolean isUseButterKnife();
    /**
     * 是否全屏
     * @return
     */
    protected abstract boolean isAllowFullScreen();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.getInstance().removeActivity(this);
        if(unbinder!=null){
            unbinder.unbind();
        }
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        enterActivityAnimation();
    }

    @Override
    public void finish() {
        super.finish();
        exitActivityAnimation();
    }

    /**
     * 其它activity不继承自baseactivity可调用此方法展开动画
     */
    public void enterActivityAnimation() {
        //overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void exitActivityAnimation() {
      // overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
/*
    *//**
     * 开启请求权限
     *//*
    public void requestPermission(){
        String[] permission=getPermission();
        if(mPermissionList==null){
            mPermissionList=new ArrayList<>();
        }
        if(permission!=null && permission.length!=0){
            for(String myPermission:permission){
                if(ContextCompat.checkSelfPermission(this,myPermission)!= PackageManager.PERMISSION_GRANTED){
                    mPermissionList.add(myPermission);
                }
            }
            if(mPermissionList!=null && mPermissionList.size()!=0){
                ActivityCompat.requestPermissions(this,mPermissionList.toArray(new String[mPermissionList.size()]),PERMISSION);
            }
        }
    }

    protected String[] getPermission(){
        return null;
    }*/
    public void showMyProgressDialog(String message) {
        dialog = DialogUIUtils.showMdLoading(this, message, true, false, false, true).show();
    }

    public void hiddenProgressDialog() {
        try {
            DialogUIUtils.dismiss(dialog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
