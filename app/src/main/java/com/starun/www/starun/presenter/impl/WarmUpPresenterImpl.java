package com.starun.www.starun.presenter.impl;

import android.os.Handler;
import android.os.Looper;

import com.starun.www.starun.model.WarmUp;
import com.starun.www.starun.presenter.WarmUpPresenter;
import com.starun.www.starun.pview.WarmUpView;

/**
 * Created by hjq on 2016/3/28.
 */
public class WarmUpPresenterImpl implements WarmUpPresenter{
    private WarmUpView warmUpView;

    private WarmUp warmUp;

    private Handler handler;

    private Runnable runnable;
    private boolean run;

    public WarmUpPresenterImpl(WarmUpView warmUpView){
        this.warmUpView = warmUpView;
        warmUp = new WarmUp(warmUpView.getActivity());
        handler = new Handler(Looper.getMainLooper());
        runnableInit();
    }


    private void runnableInit(){
        runnable = new Runnable() {
            @Override
            public void run() {
                if(run){
                    handler.postDelayed(this, 1000);

                    if(warmUp.getWarmUpStatusCode() == 0){
                        warmUpView.onWarmUpStop();
                        run = false;
                        return;
                    }else if(warmUp.getWarmUpStatusCode() == 1){
                        warmUpView.onUpdateWarmUpProgress(warmUp.getProgress());
                    }else if(warmUp.getWarmUpStatusCode() == 2){
                        warmUpView.onUpdateWarmUpInfo(warmUp.getProgress(),warmUp.getWarmUpData());
                    }

                    warmUp.increaseProgress();
                }

            }
        };
    }

    @Override
    public void doWarmUpStart() {
        warmUpView.onWarmUpStart(WarmUp.PROGRESS_MAX);
        warmUp.start();
        run = true;
        handler.postDelayed(runnable,1000);

    }

    @Override
    public void doWarmUpPause() {
        warmUp.pause();
        run = false;
        handler.removeCallbacks(runnable);
        warmUpView.onWarmUpPause();
    }

    @Override
    public void doWarmUpStop() {
        warmUp.stop();
        run = false;
        handler.removeCallbacks(runnable);
        warmUpView.onWarmUpStop();
    }

}
