package com.bwie.test.presenter;

import com.bwie.test.model.bean.ThemeDetailResult;
import com.bwie.test.model.http.OkHttp;
import com.bwie.test.view.iview.BaseIView;

import java.io.IOException;

import okhttp3.Request;

/**
 *主题日报内容页Presenter
 */

public class ThemeDetailPresenter<T extends BaseIView<ThemeDetailResult>> extends BasePresenter<T>{

    public void getThemeDetail(String url) {
        OkHttp.EntityCallBack callback = new OkHttp.EntityCallBack<ThemeDetailResult>(){
            @Override
            public void onSuccess(ThemeDetailResult result) {
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
