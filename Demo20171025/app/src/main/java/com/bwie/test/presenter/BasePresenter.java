package com.bwie.test.presenter;

/**
 *
 */

public class BasePresenter<T> {

    private T mView;

    public void attachView(T view) {
        this.mView = view;
    }

    public void detachView() {
        this.mView = null;
    }

    public T getView() {
        return mView;
    }

}
