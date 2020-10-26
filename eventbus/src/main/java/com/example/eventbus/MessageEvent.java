package com.example.eventbus;

public class MessageEvent {
    String msg;
    public MessageEvent(String msg) {
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }
}
