package com.starun.www.starun.model.data;

/**
 * Created by hjq on 2016/4/10.
 */
public class WarmUpData {
    private int warmUpId;//热身记录id
    private String title;//热身标题
    private String gist;//热身要领
    private String imgPath;//图片路径
    private String soundPath;//语音路径

    public int getWarmUpId() {
        return warmUpId;
    }

    public void setWarmUpId(int warmUpId) {
        this.warmUpId = warmUpId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGist() {
        return gist;
    }

    public void setGist(String gist) {
        this.gist = gist;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getSoundPath() {
        return soundPath;
    }

    public void setSoundPath(String soundPath) {
        this.soundPath = soundPath;
    }
}
