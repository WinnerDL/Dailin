package com.bwei.administrator.dailin.model;

import com.bawei.okhttplibrary.utils.GsonObjectCallback;
import com.bawei.okhttplibrary.utils.OkhttpUtils;
import com.bwei.administrator.dailin.bean.MyBean;
import com.bwei.administrator.dailin.presenter.FinishMUsic;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/10/27 0027.
 */

public class Music_M implements IMusic_M{
    String url = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=0";
    @Override
    public void addget(final FinishMUsic finishMUsic) {
        OkhttpUtils.doGet(url, new GsonObjectCallback<MyBean>() {
            @Override
            public void onUi(MyBean myBean) {

                finishMUsic.dd(myBean);

            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
