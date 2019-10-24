package com.example.myapplication.ui.message;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;
import com.example.myapplication.im.GenerateUserSig;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMValueCallBack;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class MessageFragment extends Fragment {

    private MessageViewModel messageViewModel;

    protected TIMConversation conversation;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        messageViewModel =
                ViewModelProviders.of(this).get(MessageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_message, container, false);
        final TextView textView = root.findViewById(R.id.text_message);
        messageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        List<TIMConversation> TIMConversations = TIMManager.getInstance().getConversationList();
        int i = TIMConversations.size();
        Toast.makeText(getContext(),"消息列表内条数：" + i  ,Toast.LENGTH_LONG).show();





        final Button button1 = root.findViewById(R.id.message_login1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TIMManager.getInstance().logout(new TIMCallBack() {
                @Override
                public void onError(int code, String desc) {

                    //错误码 code 和错误描述 desc，可用于定位请求失败原因
                    //错误码 code 列表请参见错误码表
                    Toast.makeText(getContext(), "退出账号1失败 code: " + code + " errmsg: " + desc, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess() {
                    //登出成功
                }

                });

                String userId = "1";
                String userSig = GenerateUserSig.genTestUserSig(userId);

                TIMManager.getInstance().login(userId, userSig , new TIMCallBack() {
                    @Override
                    public void onError(int code, String desc) {
                        //错误码 code 和错误描述 desc，可用于定位请求失败原因
                        //错误码 code 列表请参见错误码表
                        Toast.makeText(getContext(), "login failed. code: " + code + " errmsg: " + desc, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess() {
                        String loginUser = TIMManager.getInstance().getLoginUser();
                        Toast.makeText(getContext(), "IM账号1登录成功:" + loginUser , Toast.LENGTH_LONG).show();
                    }

                });
            }
        });

        final Button button2 = root.findViewById(R.id.message_login2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TIMManager.getInstance().logout(new TIMCallBack() {
                    @Override
                    public void onError(int code, String desc) {

                        //错误码 code 和错误描述 desc，可用于定位请求失败原因
                        //错误码 code 列表请参见错误码表
                        Toast.makeText(getContext(), "退出账号2失败 code: " + code + " errmsg: " + desc, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess() {
                        //登出成功
                    }

                });

                String userId = "2";
                String userSig = GenerateUserSig.genTestUserSig(userId);

                TIMManager.getInstance().login(userId, userSig , new TIMCallBack() {
                    @Override
                    public void onError(int code, String desc) {
                        //错误码 code 和错误描述 desc，可用于定位请求失败原因
                        //错误码 code 列表请参见错误码表
                        Toast.makeText(getContext(), "login failed. code: " + code + " errmsg: " + desc, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess() {
                        String loginUser = TIMManager.getInstance().getLoginUser();
                        Toast.makeText(getContext(), "IM账号2登录成功:" + loginUser , Toast.LENGTH_LONG).show();
                    }

                });
            }
        });



        final Button button3 = root.findViewById(R.id.message_send1); //获取Button的View对象，并将改View转型为Button
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String peer = "2";
                conversation = TIMManager.getInstance().getConversation(
                        TIMConversationType.C2C,
                        peer
                );

                //构造一条消息
                TIMMessage msg = new TIMMessage();

                //添加文本内容
                TIMTextElem elem = new TIMTextElem();
                elem.setText("a new msg");

                //将elem添加到消息
                if(msg.addElement(elem) != 0) {
                    Toast.makeText(getContext(),"addElement failed" ,Toast.LENGTH_LONG).show();
                    return;
                }

                //发送消息
                conversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>() {//发送消息回调
                    @Override
                    public void onError(int code, String desc) {//发送消息失败
                        //错误码 code 和错误描述 desc，可用于定位请求失败原因
                        //错误码 code 含义请参见错误码表
                        Toast.makeText(getContext(),"send message failed. code: " + code + " errmsg: " + desc ,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSuccess(TIMMessage msg) {//发送消息成功
                        Toast.makeText(getContext(),"1向2发送消息成功" ,Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        final Button button4 = root.findViewById(R.id.message_send2); //获取Button的View对象，并将改View转型为Button
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String peer = "1";
                conversation = TIMManager.getInstance().getConversation(
                        TIMConversationType.C2C,
                        peer
                );

                //构造一条消息
                TIMMessage msg = new TIMMessage();

                //添加文本内容
                TIMTextElem elem = new TIMTextElem();
                elem.setText("a new msg");

                //将elem添加到消息
                if(msg.addElement(elem) != 0) {
                    Toast.makeText(getContext(),"addElement failed" ,Toast.LENGTH_LONG).show();
                    return;
                }

                //发送消息
                conversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>() {//发送消息回调
                    @Override
                    public void onError(int code, String desc) {//发送消息失败
                        //错误码 code 和错误描述 desc，可用于定位请求失败原因
                        //错误码 code 含义请参见错误码表
                        Toast.makeText(getContext(),"send message failed. code: " + code + " errmsg: " + desc ,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSuccess(TIMMessage msg) {//发送消息成功
                        Toast.makeText(getContext(),"2向1发送消息成功" ,Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        final Button button5 = root.findViewById(R.id.message_view1);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                                Toast.makeText(getContext(), "get message failed. code: " + code + " errmsg: " + desc , Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onSuccess(List<TIMMessage> msgs) {//获取消息成功
                                //遍历取得的消息
                                for(TIMMessage msg : msgs) {
                                    //可以通过 timestamp()获得消息的时间戳, isSelf()是否为自己发送的消息
                                    AlertDialog.Builder diaLog = new AlertDialog.Builder(getContext());
                                    diaLog.setTitle("1和2的消息内容");
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    Date date = new Date(msg.timestamp());
                                    String res = simpleDateFormat.format(date);
                                    diaLog.setMessage(
                                            "收到时间：" + res + "\n" + "当前用户发送：" + msg.isSelf() + "\n" +"消息体：\n" + msg);
                                    diaLog.create().show();
                                }
                            }
                        });

            }
        });

        return root;
    }





}