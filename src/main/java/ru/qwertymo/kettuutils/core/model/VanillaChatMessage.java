package ru.qwertymo.kettuutils.core.model;

public class VanillaChatMessage {
    private String _text = "";
    private long _time = System.currentTimeMillis();

    public VanillaChatMessage(String text){
        _text = text;
    }

    public String getText(){
        return _text;
    }

    public long getTime(){
        return _time;
    }
}
