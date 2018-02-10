package com.app.warantywise.ui.base;


/**
 *
 */
public interface Presenter<T extends MvpView> {
    void attachView(T view);
}
