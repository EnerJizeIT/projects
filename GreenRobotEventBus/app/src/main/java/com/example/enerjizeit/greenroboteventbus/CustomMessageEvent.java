package com.example.enerjizeit.greenroboteventbus;

public class CustomMessageEvent {
    String msg;

    public CustomMessageEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
