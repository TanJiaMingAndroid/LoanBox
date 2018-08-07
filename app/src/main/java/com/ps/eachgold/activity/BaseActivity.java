package com.ps.eachgold.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.ps.eachgold.App;
import com.ps.eachgold.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Unbinder;



/**
 * Created by 8146 on 2017/1/12.
 * Activity-页面-基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder unbinder;
    private boolean useBuffer=false;
    public static int PERMISSION = 1;

    /**
     * 权限列表
     */
    private ArrayList<String> mPermissionList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.mAppCompatActivity=this;
        setFullScreen();
        super.onCreate(savedInstanceState);
        requestPermission();
        if(getLayout()==0){
            throw new IllegalArgumentException("请为您的activity指定布局文件");
        }
        initVariables();
        setContentView(getLayout());
        useBuffer= isUseButterKnife();
        if(useBuffer){
            unbinder= ButterKnife.bind(this);
        }
//       setView();
        initPresenter();
        initView(savedInstanceState);

    }

    protected  void setFullScreen(){
        if(isAllowFullScreen()){
            if(getSupportActionBar()!=null){
                getSupportActionBar().hide();
            }
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void exitActivityAnimation() {
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    /**
     * 开启请求权限
     */
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
    }

}
