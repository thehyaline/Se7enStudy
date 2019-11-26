package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.im.GenerateUserSig;
import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.imsdk.session.SessionWrapper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏模式，隐藏状态栏

        getSupportActionBar().hide();//隐藏标题栏
        setContentView(R.layout.activity_splash);




        if (SessionWrapper.isMainProcess(getApplicationContext())) {
            TIMSdkConfig config = new TIMSdkConfig(GenerateUserSig.SDKAPPID)
                    .enableLogPrint(true)
                    .setLogLevel(TIMLogLevel.DEBUG)
                    .setLogPath(Environment.getExternalStorageDirectory().getPath() + "/justfortest/");
            //初始化 SDK
            TIMManager.getInstance().init(getApplicationContext(), config);
            //bfe4ee75dcca47d07d19b3dfa4c4a949a16578711e1a2ee05449227587354df7
        }


        Thread myThread=new Thread(){
            public void run(){
                try{
                    sleep(2000);//使程序休眠2秒
                    Intent it = new Intent(getApplicationContext(),MainActivity.class);//启动MainActivity
                    startActivity(it);
                    finish();//关闭当前活动
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();//启动线程
    }



}