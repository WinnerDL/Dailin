package com.bwie.test.presenter;

import com.bwie.test.model.bean.ThemesResult;
import com.bwie.test.model.http.OkHttp;
import com.bwie.test.view.iview.BaseIView;

import java.io.IOException;

import okhttp3.Request;

/**
 *主题日报页面Presenter
 */

public class ThemesPresenter<T extends BaseIView<ThemesResult>> extends BasePresenter<T>{

    public void getThemes(String url) {
        OkHttp.EntityCallBack callback = new OkHttp.EntityCallBack<ThemesResult>(){
            @Override
            public void onSuccess(ThemesResult result) {
                getView().onSuccess(result);
            }

            @Override
            public void onFailure(Request request, IOException e) {
                getView().onFailure(request, e);
            }
        };
        OkHttp.getAsync(url, callback);
    }

}
