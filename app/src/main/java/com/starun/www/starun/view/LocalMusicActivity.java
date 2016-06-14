package com.starun.www.starun.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.starun.www.starun.R;
import com.starun.www.starun.dao.MusicDao;
import com.starun.www.starun.model.data.Mp3Info;
import com.starun.www.starun.model.logic.MusicLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class LocalMusicActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextView tvListName;

    private ListView lvMusic;

    private SimpleAdapter adapter = null;
    private MusicDao musicDao;
    private MusicLogic musicLogic;
    private List<Mp3Info> mp3Infos;
    private List<HashMap<String, String>> mp3list;

    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        btnBack = (ImageButton)findViewById(R.id.btn_back);
        tvListName = (TextView)findViewById(R.id.tv_listname);
        lvMusic = (ListView)findViewById(R.id.lv_music);

        tvListName.setText("播放列表");

        musicLogic = new MusicLogic(this);
        musicDao = new MusicDao(this);

        mp3Infos = musicLogic.getMp3Infos();

        setListAdpter(lvMusic, mp3Infos);

        lvMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                alert = null;
                builder = new AlertDialog.Builder(LocalMusicActivity.this);
                alert = builder
                        .setItems(new String[]{"添加到播放列表"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                musicDao.addMp3Info(mp3Infos.get(position));

                                Toast.makeText(LocalMusicActivity.this,"已添加到播放列表",Toast.LENGTH_SHORT).show();
                            }
                        }).create();
                alert.show();

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocalMusicActivity.this,MusicActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }


    public void setListAdpter(ListView lvMusic,List<Mp3Info> mp3Infos) {
        mp3list = new ArrayList<HashMap<String, String>>();
        int curOrder = 1;

        for (Iterator iterator = mp3Infos.iterator(); iterator.hasNext();) {
            Mp3Info mp3Info = (Mp3Info) iterator.next();
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("order",curOrder+"");
            curOrder++;
            map.put("title", mp3Info.getTitle());
            map.put("artist", mp3Info.getArtist());
            map.put("album",mp3Info.getAlbum());

            long duration = mp3Info.getDuration();
            int tol = (int)duration/1000;
            int min = tol/60;
            int sec = tol-min*60;
            map.put("duration", min+":"+ sec);
            map.put("url", mp3Info.getUrl());

            mp3list.add(map);
        }

        adapter = new SimpleAdapter(this, mp3list,
                R.layout.music_item, new String[] { "order","title","artist","album"},
                new int[] { R.id.tv_order_num,R.id.tv_tilte,R.id.tv_artist,R.id.tv_album});
        lvMusic.setAdapter(adapter);
    }
}
