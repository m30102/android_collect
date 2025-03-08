package com.fan.frame.eventbus;

import android.os.Environment;

import com.blankj.utilcode.util.ScreenUtils;

//https://www.jianshu.com/p/f9ae5691e1bb
public class MessageEvent {

    private String message;
    public  MessageEvent(String message){
        this.message=message;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
