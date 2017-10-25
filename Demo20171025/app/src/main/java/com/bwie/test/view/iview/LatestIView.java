package com.bwie.test.view.iview;

import java.io.IOException;

import okhttp3.Request;

/**
 *
 */

public interface LatestIView<T> extends BaseIView<T> {

    void onGetBeforeSuccess(T t);

    void onGetBeforeFailure(Request request, IOException e);

}
