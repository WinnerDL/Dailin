package com.bwei.administrator.zhoukao2.view;

import com.bwei.administrator.zhoukao2.bean.SongData;

import java.util.List;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public interface SongView {
    void getData(List<SongData.SongListBean> songListBeen, SongData songData);
}
