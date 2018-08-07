package com.ps.eachgold.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ps.eachgold.R;

import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/5/8 0008.
 */

public abstract class BaseFragment extends Fragment {

    private long showTime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //initVariables();
        if (getLayout() == 0) {
            throw new IllegalArgumentException("请指定fragment布局文件");

        }
        View containerView = inflater.inflate(getLayout(), container, false);
        if (isUseBuffer()) {
            ButterKnife.bind(this, containerView);
        }
        initView(savedInstanceState);
        return containerView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPresenter();
        loadData();
    }
//
//    public void showDiaglog(String message) {
//        showTime= SystemClock.uptimeMillis();
//        if(mWaitingDialog==null){
//            mWaitingDialog = new WaitingDialog();
//        }
//        mWaitingDialog.setDialogText(message);
//        if(!mWaitingDialog.isVisible()){
//            mWaitingDialog.show(getFragmentManager(), getClass().getSimpleName());
//        }
//    }

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            if (msg.what == 11) {
//                if (mWaitingDialog != null) {
//                    mWaitingDialog.dismissAllowingStateLoss();
//                }
//            }
        }
    };

//    public void hideDiaglog() {
//        if (mWaitingDialog != null) {
//            if(SystemClock.uptimeMillis()-showTime>1000){
//                if(mWaitingDialog!=null){
//                    mWaitingDialog.dismissAllowingStateLoss();
//                }
//            }else{
//                mHandler.sendEmptyMessageDelayed(11, 1000 + showTime - SystemClock.uptimeMillis());
//            }
//
//        }
//    }

    /**
     * 其它activity不继承自baseactivity可调用此方法展开动画
     */
    public void enterActivityAnimation() {
        getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void exitActivityAnimation() {
        getActivity().overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        enterActivityAnimation();
    }

    //初始化变量，intent携带的数据和Activity的变量
    protected abstract void initVariables();
    /**
     * 初始化
     *
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void initPresenter();

    protected abstract void loadData();
    public abstract int getLayout();

    public abstract boolean isUseBuffer();

//    public void setView() {
//        SuperSwipeRefreshLayout layout = getMySuperSwipeRefreshLayout();
//        if (layout != null) {
//            ViewImp viewFoot = LayoutInflater.from(getActivity()).inflate(R.layout.refresh_footer, null);
//            ViewImp viewHead = LayoutInflater.from(getActivity()).inflate(R.layout.refresh_header, null);
//            layout.setHeaderView(viewHead);
//            layout.setFooterView(viewFoot);
//        }
//    }
//
//    protected SuperSwipeRefreshLayout getMySuperSwipeRefreshLayout() {
//        return null;
//    }

    @Override
    public void onDestroyView() {
//        if(mWaitingDialog!=null && !(mWaitingDialog.isHidden())){
//            hideDiaglog();
//        }
        if (mHandler != null) {
            mHandler.removeMessages(11);
        }
        super.onDestroyView();
    }
}
