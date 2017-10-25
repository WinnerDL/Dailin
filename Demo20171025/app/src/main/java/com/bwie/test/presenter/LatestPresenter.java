package com.bwie.test.presenter;

import com.bwie.test.model.bean.LatestResult;
import com.bwie.test.model.http.OkHttp;
import com.bwie.test.view.iview.LatestIView;

import java.io.IOException;

import okhttp3.Request;

/**
 * 最新日报页面Presenter
 */

public class LatestPresenter<T extends LatestIView<LatestResult>> extends BasePresenter<T>{

    public void getLatestNews(String url) {
        OkHttp.EntityCallBack callback = new OkHttp.EntityCallBack<LatestResult>(){
            @Override
            public void onSuccess(LatestResult latestResult) {
                getView().onSuccess(latestResult);
            }

            @Override
            public void onFailure(Request request, IOException e) {
                getView().onFailure(request, e);
            }
        };
        OkHttp.getAsync(url, callback);
    }

    public void getBeforeNews(String url) {
        OkHttp.EntityCallBack callback = new OkHttp.EntityCallBack<LatestResult>(){
            @Override
            public void onSuccess(LatestResult latestResult) {
                getView().onGetBeforeSuccess(latestResult);
            }

            @Override
            public void onFailure(Request request, IOException e) {
                getView().onGetBeforeFailure(request, e);
            }
        };
        OkHttp.getAsync(url, callback);
    }

}
