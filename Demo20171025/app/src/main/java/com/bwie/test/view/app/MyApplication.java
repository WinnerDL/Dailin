package com.bwie.test.view.app;

import android.app.Application;
import android.content.Context;

import com.bwie.test.model.http.ImageLoadProxy;

public class MyApplication extends Application{

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
        //初始化ImageLoader
        ImageLoadProxy.initImageLoader(getContext());
    }

    public static Context getContext(){
        return instance.getApplicationContext();
    }
}
