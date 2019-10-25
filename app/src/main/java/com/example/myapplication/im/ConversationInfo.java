package com.example.myapplication.im;

import android.graphics.Bitmap;

import java.io.Serializable;

public class ConversationInfo implements Serializable {
    private int unRead; //未读数
    private String conversationId; //会话ID
    private String iconUrl; //头像URL
    private String title; //会话标题
    private Bitmap icon; //会话头像
    private boolean isGroup; //是否是群会话
    private boolean top; //是否是置顶会话
    private long lastMessageTime; //最后一条消息时间

    private String id; // 用户ID 或 群组 ID
    private MessageInfo lastMessage; //最后一条消息

    public ConversationInfo(){

    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public MessageInfo getLastMessage(){
        return lastMessage;
    }

    public void setLastMessage(MessageInfo lastMessage){
        this.lastMessage = lastMessage;
    }

    @Override
    public String toString(){
        return "ConversationInfo{" +
                "id=" + id + '\'' +
                "lasMessage" + lastMessage +
                '}';
    }
}
