package com.bwie.test.view.iview;

import java.io.IOException;

import okhttp3.Request;

/**
 *
 */

public interface BaseIView<T> {

    void onSuccess(T t);

    void onFailure(Request request, IOException e);

}
