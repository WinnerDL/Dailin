package com.bwie.test.model.http;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.bwie.test.view.app.MyApplication;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 缓存拦截器
 * http://blog.csdn.net/u010286855/article/details/52608485
 *
 * 两种缓存：
 * 一、无论有无网络我们都去获取缓存的数据（我们会设置一个缓存时间，在某一段时间内（例如60S）去获取缓存数据。超过60S我们就去网络重新请求数据）
 * 二、有网络的时候我们就去直接获取网络上面的数据。当没有网络的时候我们就去缓存获取数据。
 *
 * 这里用的是第二种方式
 */
public class CacheInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();//获取请求
        //这里就是说判读我们的网络条件，要是有网络的话我么就直接获取网络上面的数据，要是没有网络的话我么就去缓存里面取数据
        if(!isConnected()){
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response originalResponse = chain.proceed(request);
        if(isConnected()){
            return originalResponse.newBuilder()
                    //这里设置的为0就是说不进行缓存，我们也可以设置缓存时间
                    .header("Cache-Control", "public, max-age=" + 0)
                    .removeHeader("Pragma")
                    .build();
        }else{
            int maxTime = 4*24*60*60;
            return originalResponse.newBuilder()
                    //这里的设置的是我们的没有网络的缓存时间，想设置多少就是多少。
                    .header("Cache-Control", "public, only-if-cached, max-stale="+maxTime)
                    .removeHeader("Pragma")
                    .build();
        }
    }

    //判断网络是否已连接
    public boolean isConnected() {
        try {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) MyApplication.getContext()
                    .getSystemService(Activity.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if(mNetworkInfo!=null){
                return mNetworkInfo.isAvailable();
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
