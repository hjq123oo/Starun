package com.starun.www.starun.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

import com.starun.www.starun.R;
import com.starun.www.starun.model.data.Mp3Info;
import com.starun.www.starun.presenter.MusicPresenter;
import com.starun.www.starun.presenter.impl.MusicPresenterImpl;
import com.starun.www.starun.pview.MusicView;
import com.starun.www.starun.service.MusicService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MusicFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MusicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MusicFragment extends Fragment implements MusicView{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ImageButton musicPrevBtn;
    private ImageButton musicNextBtn;
    private ImageButton musicPlayBtn;
    private ImageButton musicMenuBtn;
    private PopupWindow musicPopWindow;
    private View view;


    private MusicPresenter musicPresenter;

    enum MusicPlayBtnState{
        PLAY,PAUSE,CONTINUE
    };

    private MusicPlayBtnState musicPlayBtnState;


    public MusicFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MusicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MusicFragment newInstance(String param1, String param2) {
        MusicFragment fragment = new MusicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_music, container, false);
        init();
        return view;
    }

    private void init(){
        musicPrevBtn = (ImageButton)view.findViewById(R.id.btn_music_prev);
        musicNextBtn = (ImageButton)view.findViewById(R.id.btn_music_next);
        musicPlayBtn = (ImageButton)view.findViewById(R.id.btn_music_play);
        musicPlayBtnState = MusicPlayBtnState.PLAY;
        musicMenuBtn = (ImageButton)view.findViewById(R.id.btn_music_menu);

        musicPresenter = new MusicPresenterImpl(this);

        musicPresenter.doLoadMusic();

        musicMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMusicPopWindow(v);
            }
        });

        musicPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicPlayBtnState == MusicPlayBtnState.PLAY) {
                    musicPresenter.doMusicPlay();


                } else if (musicPlayBtnState == MusicPlayBtnState.PAUSE) {
                    musicPresenter.doMusicPause();

                } else if (musicPlayBtnState == MusicPlayBtnState.CONTINUE) {
                    musicPresenter.doMusicContinue();

                }
            }
        });

        musicNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicPresenter.doMusicNext();

            }
        });

        musicPrevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicPresenter.doMusicPrev();
                ;
            }
        });


    }

    @Override
    public Activity getMusicActivity(){
        return getActivity();
    }

    @Override
    public void onMusicPlay(){
        musicPlayBtn.setImageDrawable(getResources().getDrawable(R.drawable.music_pause));
        musicPlayBtnState = MusicPlayBtnState.PAUSE;
    }

    @Override
    public void onMusicNext(){
        musicPlayBtn.setImageDrawable(getResources().getDrawable(R.drawable.music_pause));
        musicPlayBtnState = MusicPlayBtnState.PAUSE;
    }
    @Override
    public void onMusicPrev(){
        musicPlayBtn.setImageDrawable(getResources().getDrawable(R.drawable.music_pause));
        musicPlayBtnState = MusicPlayBtnState.PAUSE;
    }

    @Override
    public void onMusicPause(){
        musicPlayBtn.setImageDrawable(getResources().getDrawable(R.drawable.music_continue));
        musicPlayBtnState = MusicPlayBtnState.CONTINUE;
    }

    @Override
    public void onMusicContinue(){
        musicPlayBtn.setImageDrawable(getResources().getDrawable(R.drawable.music_pause));
        musicPlayBtnState = MusicPlayBtnState.PAUSE;
    }


    @Override
    public void onLoadMusic(List<Mp3Info> mp3Infos){
        initMusicPopWindow(mp3Infos);
    }

    private void initMusicPopWindow(List<Mp3Info> mp3Infos){
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.popup_window_music, null);
        ListView musicListView = (ListView)view.findViewById(R.id.lv_music);
        setListAdpter(musicListView, mp3Infos);

        musicPopWindow = new PopupWindow(view,400,500,true);

        musicPopWindow.setAnimationStyle(R.style.MusicPopupWindoAnimation);

        musicPopWindow.setTouchable(true);
        musicPopWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        musicPopWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

        musicListView.setOnItemClickListener(new MusicListItemClickListener());
    }

    private void showMusicPopWindow(View v){
        musicPopWindow.showAsDropDown(v, -350, 0);
    }

    public void setListAdpter(ListView musicListView,List<Mp3Info> mp3Infos) {
        List<HashMap<String, String>> mp3list = new ArrayList<HashMap<String, String>>();
        for (Iterator iterator = mp3Infos.iterator(); iterator.hasNext();) {
            Mp3Info mp3Info = (Mp3Info) iterator.next();
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("title", mp3Info.getTitle());
            map.put("artist", mp3Info.getArtist());
            map.put("duration", String.valueOf(mp3Info.getDuration()));
            map.put("size", String.valueOf(mp3Info.getSize()));
            map.put("url", mp3Info.getUrl());
            map.put("music",mp3Info.getTitle()+"-"+mp3Info.getArtist());
            mp3list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), mp3list,
                R.layout.music_listview_item, new String[] { "music"},
                new int[] { R.id.tv_music});
        musicListView.setAdapter(adapter);
    }


    private class MusicListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            musicPopWindow.dismiss();
            musicPresenter.doMusicPlayByPosition(position);

        }
    }


    @Override
    public void onStop() {
        super.onStop();
        musicPresenter.doMusicExit();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
