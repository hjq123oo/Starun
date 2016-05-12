package com.starun.www.starun.presenter.impl;

import android.os.Handler;
import android.os.Looper;

import com.starun.www.starun.model.logic.WarmUpLogic;
import com.starun.www.starun.presenter.WarmUpPresenter;
import com.starun.www.starun.pview.WarmUpView;

/**
 * Created by hjq on 2016/3/28.
 */
public class WarmUpPresenterImpl implements WarmUpPresenter{
    private WarmUpView warmUpView;

    private WarmUpLogic warmUpLogic;

    private Handler handler;

    private Runnable runnable;
    private boolean run;

    public WarmUpPresenterImpl(WarmUpView warmUpView){
        this.warmUpView = warmUpView;
        warmUpLogic = new WarmUpLogic(warmUpView.getActivity());
        handler = new Handler(Looper.getMainLooper());
        runnableInit();
    }


    private void runnableInit(){
        runnable = new Runnable() {
            @Override
            public void run() {
                if(run){
                    handler.postDelayed(this, 1000);

                    if(warmUpLogic.getWarmUpStatusCode() == 0){
                        warmUpView.onWarmUpStop();
                        run = false;
                        return;
                    }else if(warmUpLogic.getWarmUpStatusCode() == 1){
                        warmUpView.onUpdateWarmUpProgress(warmUpLogic.PROGRESS_MAX - warmUpLogic.getProgress());
                    }else if(warmUpLogic.getWarmUpStatusCode() == 2){
                        warmUpView.onUpdateWarmUpInfo(warmUpLogic.getProgress(), warmUpLogic.getWarmUpData());
                    }

                    warmUpLogic.increaseProgress();
                }

            }
        };
    }

    @Override
    public void doWarmUpStart() {
        warmUpView.onWarmUpStart();
        warmUpLogic.start();
        run = true;
        handler.postDelayed(runnable,1000);

    }

    @Override
    public void doWarmUpPause() {
        warmUpLogic.pause();
        run = false;
        handler.removeCallbacks(runnable);
        warmUpView.onWarmUpPause();
    }

    @Override
    public void doWarmUpStop() {
        warmUpLogic.stop();
        run = false;
        handler.removeCallbacks(runnable);
        warmUpView.onWarmUpStop();
    }

}
