package com.example.myapplication.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

//这里有一个解释ViewModel的文档：https://www.jianshu.com/p/35d143e84d42
//数据的处理在ViewModel做（如网络请求和数据库数据），Fragment处理UI（渲染数据、响应用户行为，处处理系统的某些交互）


public class HomeViewModel extends ViewModel {

    private static final String TAG = "HomeViewModel";
    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        Log.d(TAG, "HomeViewModel: ");
        
    }

    public static void main(String[] args) {

    }

    public LiveData<String> getText() {
        return mText;
    }

}