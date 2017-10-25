package com.bwie.test.model.http;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoaderInterface;

/**
 * Banner加载图片
 */

public class BannerImageLoader implements ImageLoaderInterface<ImageView>{
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        ImageLoadProxy.displayImage((String)path, imageView, ImageLoadProxy.getOption4ExactlyType());
    }

    @Override
    public ImageView createImageView(Context context) {
        return new ImageView(context);
    }
}
