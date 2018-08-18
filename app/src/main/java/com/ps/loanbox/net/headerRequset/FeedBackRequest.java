package com.ps.loanbox.net.headerRequset;

import com.ps.loanbox.net.ApiAction;

/**
 * Created by 8146 on 2018/1/31.
 */

public class FeedBackRequest extends BaseRequset {


    private String content;
    private String feedbackPictureUrl;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFeedbackPictureUrl() {
        return feedbackPictureUrl;
    }

    public void setFeedbackPictureUrl(String feedbackPictureUrl) {
        this.feedbackPictureUrl = feedbackPictureUrl;
    }
    public FeedBackRequest() {
        createHeader();
    }
    @Override
    public void createHeader() {
        super.createHeader();
        header.setAction(ApiAction.PERSONFEEDBACK);
    }

}
