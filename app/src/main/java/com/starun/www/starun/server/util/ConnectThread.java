package com.starun.www.starun.server.util;

/**
 * Created by yearsj on 2016/4/28.
 */

//连接线程
public class ConnectThread extends Thread {
    private String response = null;
    private String operation =null;
    private String message = null;

    public ConnectThread(){}

    public ConnectThread(String operation, String message){
        this.operation = operation;
        this.message = message;
    }

    @Override
    public void run() {
        super.run();
        response = ConnectUtil.getResponse(operation, message);
    }

    public String getResponse() {
        return response;
    }
}
