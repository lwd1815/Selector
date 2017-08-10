package com.example.lwd18.pictureselecotor.event;

/**
 * Created by EtherealPatrick on 2016/10/8.
 */

public class SearchHistoryEvent {
    private String mMsg;
    public SearchHistoryEvent(String msg) {

        mMsg = msg;
    }
    public String getMsg(){
        return mMsg;
    }
}
