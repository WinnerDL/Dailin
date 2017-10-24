package com.bwei.administrator.zhoukao2.presenter;

import com.bwei.administrator.zhoukao2.bean.SongData;
import com.bwei.administrator.zhoukao2.modle.Song;
import com.bwei.administrator.zhoukao2.modle.SongModle;
import com.bwei.administrator.zhoukao2.view.SongView;

import java.util.List;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class SongPreserter implements SongPresert{
    SongView songView;
    private final Song song;

    public SongPreserter(SongView songView) {
        this.songView = songView;
        song = new SongModle();
    }

    @Override
    public void setData(List<SongData.SongListBean> songListBeen, SongData songData) {

        songView.getData(songListBeen,songData);
    }

    @Override
    public void getData(int page) {
        song.addData(page,this);

    }
}
