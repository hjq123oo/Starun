package com.starun.www.starun.pview;

import android.app.Activity;

import com.starun.www.starun.model.data.Mp3Info;

import java.util.List;

/**
 * Created by hjq on 2016/5/17.
 */
public interface MusicView {
    Activity getMusicActivity();

    /**
     * 音乐开始
     */
    void onMusicPlay();

    /**
     * 下一首
     */
    void onMusicNext();

    /**
     * 上一首
     */
    void onMusicPrev();

    /**
     * 音乐暂停
     */
    void onMusicPause();

    /**
     * 音乐继续
     */
    void onMusicContinue();

    /**
     * 音乐加载
     */
    void onLoadMusic(List<Mp3Info> mp3Infos);
}
