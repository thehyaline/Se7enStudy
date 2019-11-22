package com.example.myapplication;

import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.im.GenerateUserSig;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.imsdk.session.SessionWrapper;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String userId = "1";
        String userSig = GenerateUserSig.genTestUserSig(userId);

        if (SessionWrapper.isMainProcess(getApplicationContext())) {
            TIMSdkConfig config = new TIMSdkConfig(GenerateUserSig.SDKAPPID)
                    .enableLogPrint(true)
                    .setLogLevel(TIMLogLevel.DEBUG)
                    .setLogPath(Environment.getExternalStorageDirectory().getPath() + "/justfortest/");
            //初始化 SDK
            TIMManager.getInstance().init(getApplicationContext(), config);
            //bfe4ee75dcca47d07d19b3dfa4c4a949a16578711e1a2ee05449227587354df7
        }


        TIMManager.getInstance().login(userId, userSig, new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                //错误码 code 和错误描述 desc，可用于定位请求失败原因
                //错误码 code 列表请参见错误码表
                Toast.makeText(MainActivity.this, "login failed. code: " + code + " errmsg: " + desc, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess() {
                String loginUser = TIMManager.getInstance().getLoginUser();
                Toast.makeText(MainActivity.this, "IM账号1登录成功:" + loginUser, Toast.LENGTH_LONG).show();
            }

        });


        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_follow, R.id.navigation_message)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }



}
