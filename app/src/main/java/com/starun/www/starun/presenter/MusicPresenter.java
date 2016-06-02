package com.starun.www.starun.presenter;

/**
 * Created by hjq on 2016/5/16.
 */
public interface MusicPresenter {
    /**
     * 播放音乐
     */
    void doMusicPlay();

    /**
     * 下一首
     */
    void doMusicNext();

    /**
     * 上一首
     */
    void doMusicPrev();

    /**
     * 暂停音乐
     */
    void doMusicPause();

    /**
     * 继续音乐
     */
    void doMusicContinue();

    /**
     * 加载音乐
     */
    void doLoadMusic();

    /**
     * 播放音乐
     * @param position 播放第几首音乐，position为音乐列表的位置
     */
    void doMusicPlayByPosition(int position);

    /**
     * 退出音乐
     */
    void doMusicExit();
}
