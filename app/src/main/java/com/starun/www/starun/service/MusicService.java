package com.starun.www.starun.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import com.starun.www.starun.model.data.Mp3Info;
import com.starun.www.starun.model.logic.MusicLogic;

import java.util.List;

public class MusicService extends Service {
    private MediaPlayer mediaPlayer;   //媒体播放器对象
    private String path = null;                 //音乐文件路径
    private int msg = 0;                        //播放信息
    private boolean isPause = true;           //暂停状态
    private int current = 0;                   // 记录当前正在播放的音乐
    private List<Mp3Info> mp3Infos;            //存放Mp3Info对象的集合


    public final static int MSG_PLAY = 1;
    public final static int MSG_PAUSE = 2;
    public final static int MSG_CONTINUE = 3;
    public final static int MSG_PREV = 4;
    public final static int MSG_NEXT = 5;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mediaPlayer = new MediaPlayer();

        MusicLogic musicLogic = new MusicLogic(this);
        mp3Infos = musicLogic.getMp3Infos();
        isPause = true;
        current = 0;
        path = mp3Infos.get(current).getUrl();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                current++;
                if(current > mp3Infos.size() - 1) { //变为第一首的位置继续播放
                    current = 0;
                }

                path = mp3Infos.get(current).getUrl();
                play(0);
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        msg = intent.getIntExtra("MSG", 0);         //播放信息
        if (msg ==MSG_PLAY) {    //直接播放音乐
            current = intent.getIntExtra("POSITION", -1);   //当前播放歌曲的在mp3Infos的位置
            path = mp3Infos.get(current).getUrl();
            play(0);
        } else if (msg == MSG_PAUSE) {    //暂停
            pause();
        } else if (msg == MSG_CONTINUE) { //继续播放
            resume();
        } else if (msg == MSG_PREV) { //上一首
            prev();
        } else if (msg == MSG_NEXT) {     //下一首
            next();
        }

        return super.onStartCommand(intent, flags, startId);
    }


    /**
     * 播放音乐
     * @param position
     */
    private void play(int position) {
        try {
            mediaPlayer.reset();//把各项参数恢复到初始状态
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();  //进行缓冲
            mediaPlayer.setOnPreparedListener(new PreparedListener(position));//注册一个监听器
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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

    /**
     * 上一首
     */
    private void prev(){
        current--;
        if(current < 0) { //变为第一首的位置继续播放
            current = mp3Infos.size() - 1;
        }

        path = mp3Infos.get(current).getUrl();
        play(0);
    }

    /**
     * 下一首
     */
    private void next(){
        current++;
        if(current > mp3Infos.size() - 1) { //变为第一首的位置继续播放
            current = 0;
        }

        path = mp3Infos.get(current).getUrl();
        play(0);

    }



    @Override
    public void onDestroy() {
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    /**
     *
     * 实现一个OnPrepareLister接口,当音乐准备好的时候开始播放
     *
     */
    private final class PreparedListener implements MediaPlayer.OnPreparedListener {
        private int positon;

        public PreparedListener(int positon) {
            this.positon = positon;
        }

        @Override
        public void onPrepared(MediaPlayer mp) {
            mediaPlayer.start();    //开始播放
            if(positon > 0) {    //如果音乐不是从头播放
                mediaPlayer.seekTo(positon);
            }
        }
    }

}
