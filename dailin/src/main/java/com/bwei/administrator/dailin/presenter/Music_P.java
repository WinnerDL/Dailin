package com.bwei.administrator.dailin.presenter;

import com.bwei.administrator.dailin.bean.MyBean;
import com.bwei.administrator.dailin.model.IMusic_M;
import com.bwei.administrator.dailin.model.Music_M;
import com.bwei.administrator.dailin.view.IMusic_view;

/**
 * Created by Administrator on 2017/10/27 0027.
 */

public class Music_P implements IMusic_P,FinishMUsic{
    private IMusic_view iMusic_view;
    private IMusic_M music_m;

    public Music_P(IMusic_view iMusic_view) {
        this.iMusic_view = iMusic_view;
        music_m = new Music_M();
    }

    @Override
    public void getdata() {

        music_m.addget(this);
    }


    @Override
    public void dd(MyBean myBean) {
        iMusic_view.stedaa(myBean);
    }
}
