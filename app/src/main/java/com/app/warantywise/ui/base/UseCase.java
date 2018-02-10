package com.app.warantywise.ui.base;


import io.reactivex.Observable;

/**
 *
 */
public interface UseCase<T> {
    Observable<T> execute();
}
