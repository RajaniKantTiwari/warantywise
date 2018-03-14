package com.app.warantywise.ui.dashboard;

import android.app.Activity;


import com.app.warantywise.network.DefaultApiObserver;
import com.app.warantywise.network.Repository;
import com.app.warantywise.network.request.DeviceTokenRequest;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.network.response.LoginResponse;
import com.app.warantywise.ui.base.MvpView;
import com.app.warantywise.ui.base.Presenter;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.LogUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class DashboardPresenter implements Presenter<MvpView> {


    private MvpView mView;
    private Repository mRepository;


    @Override
    public void attachView(MvpView view) {
        mView = view;
    }

    @Inject
    public DashboardPresenter(Repository repository) {
        this.mRepository = repository;
    }

    public void logout(DashBoardActivity activity) {
        mView.showProgress();
        mRepository.logout().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
                mView.hideProgress();
                mView.onSuccess(response, AppConstants.LOGOUT);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(), AppConstants.LOGOUT);
            }
        });
    }


}
