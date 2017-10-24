package com.bwei.administrator.zhoukao2.modle;

import android.os.Handler;
import android.util.Log;

import com.bawei.okhttplibrary.utils.OkhttpUtils;
import com.bwei.administrator.zhoukao2.bean.SongData;
import com.bwei.administrator.zhoukao2.presenter.SongPresert;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class SongModle implements Song{
    private List<SongData.SongListBean> list;
    private Handler handler = new Handler();
    @Override
    public void addData(final int page, final SongPresert songPresert) {

        String url = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type="+page+"&size=10&offset=0";

        OkhttpUtils.doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String string = response.body().string();
                Log.i("TAG", string);
               handler.post(new Runnable() {
                   @Override
                   public void run() {
                       Gson gson = new Gson();
                       SongData songData = gson.fromJson(string, SongData.class);
                       List<SongData.SongListBean> song = songData.getSong_list();
                       if (page == 21) {
                           list = new ArrayList<SongData.SongListBean>();
                       }
                       for (int i = 0; i < song.size(); i++) {
                           list.add(song.get(i));
                       }

                      songPresert.setData(list,songData);
                   }
               });
            }
        });
    }
}
