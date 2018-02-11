package com.app.warantywise.ui.dashboard;

import android.app.Activity;


import com.app.warantywise.network.DefaultApiObserver;
import com.app.warantywise.network.Repository;
import com.app.warantywise.network.request.dashboard.MerchantRequest;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.network.response.dashboard.MerchantResponseData;
import com.app.warantywise.network.response.dashboard.ReviewResponseData;
import com.app.warantywise.ui.base.MvpView;
import com.app.warantywise.ui.base.Presenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


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
    public void getMerchantDetails(Activity activity, MerchantRequest merchantRequest) {
        mView.showProgress();
        mRepository.getMerchantDetail(merchantRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<MerchantResponseData>(activity) {
            @Override
            public void onResponse(MerchantResponseData response) {
                mView.hideProgress();
                mView.onSuccess(response, 1);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(), 1);
            }
        });
    }

    public void getMerchantReviews(DashBoardActivity activity, MerchantRequest merchantRequest) {
        mRepository.getMerchantReviews(merchantRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<ReviewResponseData>(activity) {
            @Override
            public void onResponse(ReviewResponseData response) {
                mView.hideProgress();
                mView.onSuccess(response, 2);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(), 2);
            }
        });
    }

}
