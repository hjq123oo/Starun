package com.starun.www.starun.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class WarmUpService extends Service {
    public WarmUpService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
