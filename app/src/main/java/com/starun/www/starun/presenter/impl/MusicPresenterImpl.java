package com.starun.www.starun.presenter.impl;

import android.content.Intent;

import com.starun.www.starun.model.data.Mp3Info;
import com.starun.www.starun.model.logic.MusicLogic;
import com.starun.www.starun.presenter.MusicPresenter;
import com.starun.www.starun.pview.MusicView;
import com.starun.www.starun.service.MusicService;

import java.util.List;

/**
 * Created by hjq on 2016/5/17.
 */
public class MusicPresenterImpl implements MusicPresenter {
    private MusicView musicView;
    private List<Mp3Info> mp3Infos;
    private MusicLogic musicLogic;

    public MusicPresenterImpl(MusicView musicView){
        this.musicView = musicView;
        musicLogic = new MusicLogic(musicView.getMusicActivity());
        mp3Infos = musicLogic.getMp3Infos();
    }


    @Override
    public void doMusicPlay() {
        Intent intent = new Intent();
        intent.putExtra("POSITION", 0);
        intent.putExtra("MSG", MusicService.MSG_PLAY);
        intent.setClass(musicView.getMusicActivity(), MusicService.class);
        musicView.getMusicActivity().startService(intent);       //启动服务
        musicView.onMusicPlay();

    }

    @Override
    public void doMusicNext() {
        Intent intent = new Intent();
        intent.putExtra("MSG", MusicService.MSG_NEXT);
        intent.setClass(musicView.getMusicActivity(), MusicService.class);
        musicView.getMusicActivity().startService(intent);       //启动服务
        musicView.onMusicNext();
    }

    @Override
    public void doMusicPrev() {
        Intent intent = new Intent();
        intent.putExtra("MSG", MusicService.MSG_PREV);
        intent.setClass(musicView.getMusicActivity(), MusicService.class);
        musicView.getMusicActivity().startService(intent);       //启动服务
        musicView.onMusicPrev();
    }

    @Override
    public void doMusicPause() {
        Intent intent = new Intent();
        intent.putExtra("MSG", MusicService.MSG_PAUSE);
        intent.setClass(musicView.getMusicActivity(), MusicService.class);
        musicView.getMusicActivity().startService(intent);       //启动服务
        musicView.onMusicPause();
    }

    @Override
    public void doMusicContinue() {
        Intent intent = new Intent();
        intent.putExtra("MSG", MusicService.MSG_CONTINUE);
        intent.setClass(musicView.getMusicActivity(), MusicService.class);
        musicView.getMusicActivity().startService(intent);       //启动服务
        musicView.onMusicContinue();
    }

    @Override
    public void doLoadMusic() {
        musicView.onLoadMusic(mp3Infos);
    }

    @Override
    public void doMusicPlayByPosition(int position) {
        Intent intent = new Intent();
        intent.putExtra("POSITION",position);
        intent.putExtra("MSG", MusicService.MSG_PLAY);
        intent.setClass(musicView.getMusicActivity(), MusicService.class);
        musicView.getMusicActivity().startService(intent);       //启动服务
        musicView.onMusicPlay();
    }

    public void doMusicExit(){
        Intent intent = new Intent();
        intent.setClass(musicView.getMusicActivity(), MusicService.class);
        musicView.getMusicActivity().stopService(intent);

    }
}
