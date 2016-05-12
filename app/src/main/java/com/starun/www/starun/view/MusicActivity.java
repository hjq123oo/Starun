package com.starun.www.starun.view;

import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.starun.www.starun.R;
import com.starun.www.starun.model.data.Mp3Info;
import com.starun.www.starun.service.MusicService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MusicActivity extends AppCompatActivity {
    private ImageButton musicPrevBtn;
    private ImageButton musicNextBtn;
    private ImageButton musicPlayBtn;
    private ImageButton musicMenuBtn;
    private ListView musicListView;

    private List<Mp3Info> mp3Infos;

    enum MusicPlayBtnState{
        PLAY,PAUSE,CONTINUE
    };

    private MusicPlayBtnState musicPlayBtnState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        musicPrevBtn = (ImageButton)findViewById(R.id.btn_music_prev);
        musicNextBtn = (ImageButton)findViewById(R.id.btn_music_next);
        musicPlayBtn = (ImageButton)findViewById(R.id.btn_music_play);
        musicPlayBtnState = MusicPlayBtnState.PLAY;
        musicMenuBtn = (ImageButton)findViewById(R.id.btn_music_menu);
        musicListView = (ListView)findViewById(R.id.lv_music);

        mp3Infos = getMp3Infos();
        setListAdpter(mp3Infos);

        musicListView.setOnItemClickListener(new MusicListItemClickListener());

        musicPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(musicPlayBtnState == MusicPlayBtnState.PLAY){
                    Intent intent = new Intent();
                    intent.putExtra("POSITION",0);
                    intent.putExtra("MSG", MusicService.MSG_PLAY);
                    intent.setClass(MusicActivity.this, MusicService.class);
                    startService(intent);       //启动服务

                    musicPlayBtn.setImageDrawable(getResources().getDrawable(R.drawable.music_pause));
                    musicPlayBtnState = MusicPlayBtnState.PAUSE;
                }else if(musicPlayBtnState == MusicPlayBtnState.PAUSE){
                    Intent intent = new Intent();
                    intent.putExtra("MSG", MusicService.MSG_PAUSE);
                    intent.setClass(MusicActivity.this, MusicService.class);
                    startService(intent);       //启动服务

                    musicPlayBtn.setImageDrawable(getResources().getDrawable(R.drawable.music_continue));
                    musicPlayBtnState = MusicPlayBtnState.CONTINUE;
                }else if(musicPlayBtnState == MusicPlayBtnState.CONTINUE){
                    Intent intent = new Intent();
                    intent.putExtra("MSG", MusicService.MSG_CONTINUE);
                    intent.setClass(MusicActivity.this, MusicService.class);
                    startService(intent);       //启动服务

                    musicPlayBtn.setImageDrawable(getResources().getDrawable(R.drawable.music_pause));
                    musicPlayBtnState = MusicPlayBtnState.PAUSE;
                }


            }
        });
    }


    public List<Mp3Info> getMp3Infos() {
        Cursor cursor = getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        List<Mp3Info> mp3Infos = new ArrayList<Mp3Info>();
        for (int i = 0; i < cursor.getCount(); i++) {
            Mp3Info mp3Info = new Mp3Info();
            cursor.moveToNext();
            long id = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media._ID));   //音乐id
            String title = cursor.getString((cursor
                    .getColumnIndex(MediaStore.Audio.Media.TITLE)));//音乐标题
            String artist = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ARTIST));//艺术家
            long duration = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DURATION));//时长
            long size = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.SIZE));  //文件大小
            String url = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DATA));              //文件路径
            int isMusic = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));//是否为音乐
            if (isMusic != 0) {     //只把音乐添加到集合当中
                mp3Info.setId(id);
                mp3Info.setTitle(title);
                mp3Info.setArtist(artist);
                mp3Info.setDuration(duration);
                mp3Info.setSize(size);
                mp3Info.setUrl(url);
                mp3Infos.add(mp3Info);
            }
        }
        return mp3Infos;
    }

    public void setListAdpter(List<Mp3Info> mp3Infos) {
        List<HashMap<String, String>> mp3list = new ArrayList<HashMap<String, String>>();
        for (Iterator iterator = mp3Infos.iterator(); iterator.hasNext();) {
            Mp3Info mp3Info = (Mp3Info) iterator.next();
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("title", mp3Info.getTitle());
            map.put("artist", mp3Info.getArtist());
            map.put("duration", String.valueOf(mp3Info.getDuration()));
            map.put("size", String.valueOf(mp3Info.getSize()));
            map.put("url", mp3Info.getUrl());
            mp3list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, mp3list,
                R.layout.music_listview_item, new String[] { "title", "artist"},
                new int[] { R.id.tv_title, R.id.tv_artist});
        musicListView.setAdapter(adapter);
    }


    private class MusicListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            if(mp3Infos != null) {
                Intent intent = new Intent();
                intent.putExtra("POSITION",position);
                intent.putExtra("MSG", MusicService.MSG_PLAY);
                intent.setClass(MusicActivity.this, MusicService.class);
                startService(intent);       //启动服务

                musicPlayBtn.setImageDrawable(getResources().getDrawable(R.drawable.music_pause));
                musicPlayBtnState = MusicPlayBtnState.PAUSE;
            }
        }
    }

}
