package com.ps.loanbox.gate;

/**
 * Created by 8146 on 2018/2/9.
 */

public class NewBean {

    /**
     * category : 1
     * id : 1
     * source : 新华网
     * time : 2018-02-07 09:01:00
     * title : 2018年中国反腐开局五大亮点：4名“老虎”落马
     */

    private String category;
    private int id;
    private String source;
    private String time;
    private String title;

    private String imgUrl ;
    private String summary;
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
