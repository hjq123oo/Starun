package com.starun.www.starun.model.data;

/**
 * Created by hjq on 2016/4/10.
 */
public class WarmUpData {
    private int warmUpId;//热身记录id
    private String title;//热身标题
    private String gist;//热身要领
    private int imgId;//图片资源id
    private int soundId;//语音资源id

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

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getSoundId() {
        return soundId;
    }

    public void setSoundId(int soundId) {
        this.soundId = soundId;
    }
}
