package com.example.myapplication.im;

import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageStatus;
import com.tencent.imsdk.TIMTextElem;

import java.util.ArrayList;
import java.util.List;




public class MessageInfoUtil {

    //以下用于创建一条文本消息
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

    /**
     * 把SDK的消息bean转化为TUIKit的消息bean
     *
     * @param timMessage SDK的群消息bean
     * @param isGroup    是否是群消息
     * @return
     */
    public static List<MessageInfo> TIMMessage2MessageInfo(TIMMessage timMessage, boolean isGroup) {
        if (timMessage == null || timMessage.status() == TIMMessageStatus.HasDeleted || timMessage.getElementCount() == 0) {
            return null;
        }
        List<MessageInfo> list = new ArrayList<>();
        for (int i = 0; i < timMessage.getElementCount(); i++) {
            final MessageInfo msgInfo = new MessageInfo();
            //IMMessage timMessage = timMessages.get(i);
            if (ele2MessageInfo(msgInfo, timMessage, timMessage.getElement(i), isGroup) != null) {
                list.add(msgInfo);
            }
        }
        return list;
    }

    /**
     * 把SDK的消息bean列表转化为TUIKit的消息bean列表
     *
     * @param timMessages SDK的群消息bean列表
     * @param isGroup     是否是群消息
     * @return
     */

    public static List<MessageInfo> TIMMessages2MessageInfos(List<TIMMessage> timMessages, boolean isGroup) {
        if (timMessages == null) {
            return null;
        }
        List<MessageInfo> messageInfos = new ArrayList<>();
        for (int i = 0; i < timMessages.size(); i++) {
            TIMMessage timMessage = timMessages.get(i);
            List<MessageInfo> info = TIMMessage2MessageInfo(timMessage, isGroup);
            if (info != null) {
                messageInfos.addAll(info);
            }
        }
        return messageInfos;
    }

    private static MessageInfo ele2MessageInfo(final  MessageInfo msgInfo, TIMMessage timMessage, TIMElem ele , boolean isGroup){

        //以下情况丢掉这条消息，其实应该也没事？
        if (msgInfo == null
                || timMessage == null
                || timMessage.status() == TIMMessageStatus.HasDeleted
                || timMessage.getElementCount() == 0
                || ele == null
                || ele.getType() == TIMElemType.Invalid) {
            return null;
        }

        String sender = timMessage.getSender();//获取发送用户的ID
        msgInfo.setTIMMessage(timMessage);

        TIMElemType type = ele.getType();

        if(type == TIMElemType.Text){
            TIMTextElem txtEle = (TIMTextElem) ele;
            msgInfo.setExtra(txtEle.getText());
        }

        return msgInfo;
    }


}
