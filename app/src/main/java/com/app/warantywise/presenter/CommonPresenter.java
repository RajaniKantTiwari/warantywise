package com.app.warantywise.presenter;

import android.app.Activity;
import android.widget.AdapterView;


import com.app.warantywise.network.DefaultApiObserver;
import com.app.warantywise.network.Repository;
import com.app.warantywise.network.request.AddProductRequest;
import com.app.warantywise.network.request.LoginRequest;
import com.app.warantywise.network.request.VerifyMobileRequest;
import com.app.warantywise.network.request.dashboard.CompanyDetailsRequest;
import com.app.warantywise.network.request.dashboard.ProductDetailsRequest;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.network.response.LoginResponse;
import com.app.warantywise.network.response.VerifyMobileResponse;
import com.app.warantywise.network.response.dashboard.CompanyDetailData;
import com.app.warantywise.network.response.dashboard.ProductDetailData;
import com.app.warantywise.ui.authentication.AddProductActivity;
import com.app.warantywise.ui.base.MvpView;
import com.app.warantywise.ui.base.Presenter;
import com.app.warantywise.utility.AppConstants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by arvind on 09/11/17.
 */

public class CommonPresenter implements Presenter<MvpView> {

    private final Repository mRepository;
    private MvpView mView;


    public CommonPresenter(Repository repository) {
        this.mRepository = repository;
    }

    @Override
    public void attachView(MvpView view) {
        this.mView = view;
    }

    public void getLoginDetail(Activity activity, LoginRequest loginRequest) {
        mView.showProgress();
        mRepository.getLoginDetail(loginRequest).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<LoginResponse>(activity) {
            @Override
            public void onResponse(LoginResponse response) {
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

    public void verifyMobileNumber(Activity activity, VerifyMobileRequest verifyMobileRequest) {
        mView.showProgress();
        mRepository.verifyMobileNumber(verifyMobileRequest).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<VerifyMobileResponse>(activity) {
            @Override
            public void onResponse(VerifyMobileResponse response) {
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


    public void get(Activity activity, LoginRequest loginRequest) {
        mView.showProgress();
        mRepository.getLoginDetail(loginRequest).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<LoginResponse>(activity) {
            @Override
            public void onResponse(LoginResponse response) {
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

    public void addProduct(AddProductActivity activity, AddProductRequest request) {
        mView.showProgress();
        mRepository.addProduct(request).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
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

    public void getAllProductList(Activity activity) {
        mView.showProgress();
        mRepository.getAllProductList().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
                mView.hideProgress();
                mView.onSuccess(response, 4);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(), 4);
            }
        });
    }

    public void getProductDetails(AddProductActivity activity, ProductDetailsRequest request) {
        //mView.showProgress();
        mRepository.getProductDetails(request).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<ProductDetailData>(activity) {
            @Override
            public void onResponse(ProductDetailData response) {
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

    public void getCompanyDetails(AddProductActivity activity, CompanyDetailsRequest request) {
        mRepository.getCompanyDetails(request).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<CompanyDetailData>(activity) {
            @Override
            public void onResponse(CompanyDetailData response) {
                mView.hideProgress();
                mView.onSuccess(response, 3);
            }

            @Override
            public void onError(Throwable call, BaseResponse baseResponse) {
                mView.hideProgress();
                mView.onError(baseResponse.getMsg(), 3);
            }
        });
    }
}
