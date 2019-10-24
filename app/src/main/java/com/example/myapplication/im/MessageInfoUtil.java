package com.example.myapplication.im;

import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMTextElem;

//以下用于创建一条文本消息


public class MessageInfoUtil {

    public static MessageInfo buildTextMessage(String message){
        MessageInfo info = new MessageInfo();
        TIMMessage TIMMsg = new TIMMessage();
        TIMTextElem ele = new TIMTextElem();
        ele.setText(message);
        TIMMsg.addElement(ele);
        info.setExtra(message);
        info.setMsgTime(System.currentTimeMillis() / 1000);
        info.setElement(ele);
        info.setSelf(true);
        info.setTIMMessage(TIMMsg);
        info.setFromUser(TIMManager.getInstance().getLoginUser());
        info.setMsgType(MessageInfo.MSG_TYPE_TEXT);
        return info;
    }

}
