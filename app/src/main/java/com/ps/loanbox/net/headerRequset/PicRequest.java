package com.ps.loanbox.net.headerRequset;


import com.ps.loanbox.net.ApiAction;

/**
 * Created by 8303 on 2018/3/30.
 */

public class PicRequest extends BaseRequset {
    private String imageType;
    private String imageBase64;

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public PicRequest() {
        createHeader();
    }

    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.SUBMIT_PIC);
    }
}
