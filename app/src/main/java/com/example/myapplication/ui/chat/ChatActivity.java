package com.example.myapplication.ui.chat;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMValueCallBack;

import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private TIMConversation conversation;
    private String peer;

    private Context context;
    private String editText;
    private TextView messageHistoryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        context = this;
        final EditText editTextBox = (EditText) findViewById(R.id.msg_edit);
        final Button sendButton = (Button) findViewById(R.id.msg_sent);
        messageHistoryView = (TextView) findViewById(R.id.my_msg);

        //获取会话扩展实例
        TIMConversation con = TIMManager.getInstance().getConversation(TIMConversationType.C2C, "2");

        //获取此会话的消息
        con.getMessage(10, //获取此会话最近的 10 条消息
                null, //不指定从哪条消息开始获取 - 等同于从最新的消息开始往前
                new TIMValueCallBack<List<TIMMessage>>() {//回调接口
                    @Override
                    public void onError(int code, String desc) {//获取消息失败
                        //接口返回了错误码 code 和错误描述 desc，可用于定位请求失败原因
                        //错误码 code 含义请参见错误码表
                        Toast.makeText(context, "get message failed. code: " + code + " errmsg: " + desc, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSuccess(List<TIMMessage> msgs) {//获取消息成功

                        for (int y = 0; y < msgs.size(); y++) {

                            TIMMessage lastmsg = msgs.get(y);
                            //conversationEnity.setLastMessage(conversation.getLastMsg());
                        }

                        //遍历取得的消息
                        for (TIMMessage msgHistory : msgs) {
                            //可以通过 timestamp()获得消息的时间戳, isSelf()是否为自己发送的消息
                            if(messageHistoryView == null){
                                return;
                            }else {
                                messageHistoryView.append(msgHistory.getSender()+"\n");
                            }

                        }
                    }
                });


        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("和2的聊天");
        actionBar.setDisplayHomeAsUpEnabled(true);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TIMMessage msg = new TIMMessage();//构造一条消息
                TIMTextElem elem = new TIMTextElem();

                conversation = TIMManager.getInstance().getConversation(
                        TIMConversationType.C2C,
                        peer
                );

                peer = "2"; //设置聊天对象
                editText = editTextBox.getText().toString();

                elem.setText(editText);//添加文本内容
                Toast.makeText(context,"消息框内容："+ editText,Toast.LENGTH_LONG).show();

                //将elem添加到消息
                if (msg.addElement(elem) != 0) {
                    Toast.makeText(context, "addElement failed", Toast.LENGTH_LONG).show();
                    return;
                }

                //发送消息
                conversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>() {//发送消息回调
                    @Override
                    public void onError(int code, String desc) {//发送消息失败
                        //错误码 code 和错误描述 desc，可用于定位请求失败原因
                        //错误码 code 含义请参见错误码表
                        Toast.makeText(context, "send message failed. code: " + code + " errmsg: " + desc, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSuccess(TIMMessage msg) {//发送消息成功
                        Toast.makeText(context, "1向2发送消息成功", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}