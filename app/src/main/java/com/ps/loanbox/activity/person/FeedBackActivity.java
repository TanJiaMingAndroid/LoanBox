package com.ps.loanbox.activity.person;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dou361.dialogui.DialogUIUtils;
import com.dou361.dialogui.bean.TieBean;
import com.dou361.dialogui.listener.DialogUIItemListener;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.model.TakePhotoOptions;
import com.ps.loanbox.R;
import com.ps.loanbox.activity.BasePhotoActivity;
import com.ps.loanbox.contract.person.FeedBackContract;
import com.ps.loanbox.contract.person.PicContract;
import com.ps.loanbox.presenter.FeedBackPresenter;
import com.ps.loanbox.presenter.person.PicPresenter;
import com.ps.loanbox.util.Bitmap2Base64;
import com.ps.loanbox.util.ButtonUtils;
import com.ps.loanbox.util.T;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 8146 on 2017/1/12.
 * 意见反馈-页面
 */
public class FeedBackActivity extends BasePhotoActivity implements FeedBackContract.View,PicContract.View {
    @BindView(R.id.left_icon)
    ImageView leftIcon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_icon)
    ImageView rightIcon;
    @BindView(R.id.et_text)
    EditText etText;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    @BindView(R.id.ib_feed_back)
    ImageView ibFeedBack;
    @BindView(R.id.fl_feedback_delete)
    FrameLayout flFeedbackDelete;
    //P层
    private FeedBackPresenter mPresenter;
    private PicPresenter mPicPresenter;
    private TakePhoto takePhoto;
    private CompressConfig config;


    private String url, pic, picPath;
    private Bitmap bitmap;

    @Override
    protected void initVariables() {
    }

    @Override
    protected void initPresenter() {
        mPresenter = new FeedBackPresenter(this, this);
        mPicPresenter = new PicPresenter(this, this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        title.setText("Feedback");
        takePhoto = getTakePhoto();
    }

    @Override
    public boolean isUseButterKnife() {
        return true;
    }

    @Override
    protected boolean isAllowFullScreen() {
        return false;
    }

    //跳转
    public static void createActivity(Context context) {
        Intent intent = new Intent(context, FeedBackActivity.class);
        context.startActivity(intent);
    }
    @Override
    public void getError(Throwable e) {
        Log.e("FeedBackActivity","getError(FeedBackActivity.java:115)");
        setBtnClick();
        hiddenProgressDialog();
    }


    @Override
    public String getText() {
        return etText.getText().toString().trim();
    }

    @Override
    public String getPicUrl() {
        return url;
    }


    @Override
    public void commitSuccess(String result) {
        T.showShort("Terima kasih atas feedback anda");
        finish();
    }

    private void setBtnClick(){
        if (tvSure!=null){
            tvSure.setClickable(true);
        }
    }
    @OnClick({R.id.left_icon, R.id.tv_sure, R.id.ib_feed_back, R.id.fl_feedback_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left_icon:
                finish();
                break;
            case R.id.tv_sure:
                if (!ButtonUtils.isFastDoubleClick(R.id.tv_sure)) {
                    if (TextUtils.isEmpty(getText())) {
                        T.showShort("Ohon diisi informasi yang kosong");
                    } else if (TextUtils.isEmpty(picPath)) {
                        showMyProgressDialog("Sedang proses");
                        mPresenter.commitText();
                    } else {
                        showMyProgressDialog("Sedang proses");
                        bitmap = BitmapFactory.decodeFile(picPath);
                        pic = Bitmap2Base64.bitmapToBase64(bitmap);
                        mPicPresenter.submitPic();
                    }
                }
                break;
            case R.id.ib_feed_back:
                //弹出Dialog 相册、相机、取消
                List<TieBean> strings = new ArrayList<TieBean>();
                strings.add(new TieBean(getString(R.string.phone)));
                strings.add(new TieBean(getString(R.string.camera)));
                DialogUIUtils.showSheet(FeedBackActivity.this, strings, "取消", Gravity.BOTTOM, true, true, new DialogUIItemListener() {
                    @Override
                    public void onItemClick(CharSequence text, int position) {
                        TakePhotoOptions.Builder builder=new TakePhotoOptions.Builder();
                        builder.setCorrectImage(true);
                        takePhoto.setTakePhotoOptions(builder.create());
                        LubanOptions option=new LubanOptions.Builder()
                                .setMaxSize(102400)
                                .create();
                        config= CompressConfig.ofLuban(option);
                        config.enableReserveRaw(false);
                        takePhoto.onEnableCompress(config, true);
                        switch (position){
                            case 0:
                                takePhoto.onPickFromGallery();
                                break;
                            case 1:
                                File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                                if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
                                Uri imageUri = Uri.fromFile(file);
                                takePhoto.onPickFromCapture(imageUri);
                                break;
                        }
                    }
                    @Override
                    public void onBottomBtnClick() {
                    }
                }).show();

                break;
            case R.id.fl_feedback_delete:
                deletePic();
                break;
        }
    }

    private void deletePic() {
        url = "";
        picPath = "";
        ibFeedBack.setImageDrawable(null);
        flFeedbackDelete.setVisibility(View.GONE);
        ibFeedBack.setImageResource(R.mipmap.feedback_pic);

    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        picPath=result.getImages().get(0).getCompressPath();
        Log.e("FeedBackActivity","takeSuccess(FeedBackActivity.java:216)"+picPath);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Glide.with(FeedBackActivity.this).load(picPath).into(ibFeedBack);
                flFeedbackDelete.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (bitmap!=null){
            bitmap.recycle();
            bitmap=null;
        }
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetach();
        }
    }


    @Override
    public String getPic() {
        return pic;
    }

    @Override
    public String getImageType() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(picPath, options);
        String type = options.outMimeType;
        return type;
    }



    @Override
    public void submitPicSuccess(String picUrl) {
        url = picUrl;
        mPresenter.commitText();
    }


}
