package com.starun.www.starun.presenter.impl;

import android.os.Handler;
import android.os.Looper;

import com.starun.www.starun.model.WarmUpService;
import com.starun.www.starun.presenter.WarmUpPresenter;
import com.starun.www.starun.pview.WarmUpView;

/**
 * Created by hjq on 2016/3/28.
 */
public class WarmUpPresenterImpl implements WarmUpPresenter{
    private WarmUpView warmUpView;

    private WarmUpService warmUpService;

    private Handler handler;

    private Runnable runnable;
    private boolean run;

    public WarmUpPresenterImpl(WarmUpView warmUpView){
        this.warmUpView = warmUpView;
        warmUpService = new WarmUpService(warmUpView.getActivity());
        handler = new Handler(Looper.getMainLooper());
        runnableInit();
    }


    private void runnableInit(){
        runnable = new Runnable() {
            @Override
            public void run() {
                if(run){
                    handler.postDelayed(this, 1000);

                    if(warmUpService.getWarmUpStatusCode() == 0){
                        warmUpView.onWarmUpStop();
                        run = false;
                        return;
                    }else if(warmUpService.getWarmUpStatusCode() == 1){
                        warmUpView.onUpdateWarmUpProgress(warmUpService.getProgress());
                    }else if(warmUpService.getWarmUpStatusCode() == 2){
                        warmUpView.onUpdateWarmUpInfo(warmUpService.getProgress(), warmUpService.getWarmUpData());
                    }

                    warmUpService.increaseProgress();
                }

            }
        };
    }

    @Override
    public void doWarmUpStart() {
        warmUpView.onWarmUpStart(WarmUpService.PROGRESS_MAX);
        warmUpService.start();
        run = true;
        handler.postDelayed(runnable,1000);

    }

    @Override
    public void doWarmUpPause() {
        warmUpService.pause();
        run = false;
        handler.removeCallbacks(runnable);
        warmUpView.onWarmUpPause();
    }

    @Override
    public void doWarmUpStop() {
        warmUpService.stop();
        run = false;
        handler.removeCallbacks(runnable);
        warmUpView.onWarmUpStop();
    }

}
