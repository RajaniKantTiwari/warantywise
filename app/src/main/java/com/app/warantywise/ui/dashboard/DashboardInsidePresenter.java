package com.app.warantywise.ui.dashboard;

import android.app.Activity;


import com.app.warantywise.network.Repository;
import com.app.warantywise.ui.base.MvpView;
import com.app.warantywise.ui.base.Presenter;

import javax.inject.Inject;



public class DashboardInsidePresenter implements Presenter<MvpView> {
    private MvpView mView;
    private Repository mRepository;


    @Override
    public void attachView(MvpView view) {
        mView = view;
    }

    @Inject
    public DashboardInsidePresenter(Repository repository) {
        this.mRepository = repository;
    }


}
