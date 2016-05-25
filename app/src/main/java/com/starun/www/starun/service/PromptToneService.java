package com.starun.www.starun.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.starun.www.starun.R;

/**
 * Created by yearsj on 2016/4/14.
 */
public class PromptToneService extends Service {
    private MediaPlayer mediaPlayer;   //媒体播放器对象
    private boolean isPause = false;
    public final static int WARMUP = 1;
    public final static int RUN = 2;
    public final static int PAUSE = 3;
    public final static int RESUME = 4;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int msg = intent.getIntExtra("MSG", 0);         //播放信息
        if (msg ==WARMUP) {
            play(R.raw.music);
        } else if (msg == RUN) {
            play(R.raw.music);
        } else if(msg == RESUME){
            resume();
        } else if(msg == PAUSE){
            pause();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }

    /**
     * 播放音乐
     * @param id
     */
    private void play(int id){
        mediaPlayer.reset();
        mediaPlayer = MediaPlayer.create(this, id);
        mediaPlayer.start();
        isPause = false;
    }

    /**
     * 暂停音乐
     */
    private void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            isPause = true;
        }
    }

    /**
     * 继续播放
     */
    private void resume() {
        if (isPause) {
            mediaPlayer.start();
            isPause = false;
        }
    }
}
