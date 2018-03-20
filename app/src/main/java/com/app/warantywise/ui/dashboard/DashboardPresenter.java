package com.app.warantywise.ui.dashboard;


import com.app.warantywise.network.DefaultApiObserver;
import com.app.warantywise.network.Repository;
import com.app.warantywise.network.request.UpdateProfileRequest;
import com.app.warantywise.network.request.WarrantyCardImageRequest;
import com.app.warantywise.network.request.dashboard.ExtendeWarrantyRequest;
import com.app.warantywise.network.request.dashboard.OfferRequest;
import com.app.warantywise.network.request.dashboard.ProductDetailsRequest;
import com.app.warantywise.network.request.dashboard.ProductInsuranceRequest;
import com.app.warantywise.network.request.dashboard.ProductsRequest;
import com.app.warantywise.network.request.dashboard.ServiceCenterRequest;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.network.response.dashboard.ExtendedWarrantyCardData;
import com.app.warantywise.network.response.dashboard.ManufactorServiceCentorResponseData;
import com.app.warantywise.network.response.dashboard.OfferResponseData;
import com.app.warantywise.network.response.dashboard.ProductInsuranceResponseData;
import com.app.warantywise.network.response.dashboard.WarrantyCardImageData;
import com.app.warantywise.ui.base.MvpView;
import com.app.warantywise.ui.base.Presenter;
import com.app.warantywise.utility.AppConstants;

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


    public void yourProduct(DashBoardActivity activity) {
        mView.showProgress();
        mRepository.yourProduct().
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

    public void getProductDetails(DashBoardActivity activity, ProductDetailsRequest request) {
        mView.showProgress();
        mRepository.getProductDetails(request).
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

    public void updateProfile(DashBoardActivity activity, UpdateProfileRequest updateProfileRequest) {
        mView.showProgress();
        mRepository.updateProfile(updateProfileRequest).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
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

    public void getWarrantyCardImage(DashBoardActivity activity, WarrantyCardImageRequest request) {
        mView.showProgress();
        mRepository.getWarrantyCardImage(request).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<WarrantyCardImageData>(activity) {
            @Override
            public void onResponse(WarrantyCardImageData response) {
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

    public void getExtendedWarranty(DashBoardActivity activity, ExtendeWarrantyRequest request) {
        mView.showProgress();
        mRepository.getExtendedWarranty(request).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<ExtendedWarrantyCardData>(activity) {
            @Override
            public void onResponse(ExtendedWarrantyCardData response) {
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

    public void getProductInsurance(DashBoardActivity activity, ProductInsuranceRequest request) {
        mView.showProgress();
        mRepository.getProductInsurance(request).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<ProductInsuranceResponseData>(activity) {
            @Override
            public void onResponse(ProductInsuranceResponseData response) {
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

    public void getManufactorServiceCenter(DashBoardActivity activity) {
        mView.showProgress();
        mRepository.getManufactorServiceCenter().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<ManufactorServiceCentorResponseData>(activity) {
            @Override
            public void onResponse(ManufactorServiceCentorResponseData response) {
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

    public void getManufactorServiceCenterDetail(DashBoardActivity activity,ServiceCenterRequest request) {
        mView.showProgress();
        mRepository.getManufactorServiceCenterDetail(request).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
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

    public void getManufactorServiceCenterImages(DashBoardActivity activity, ServiceCenterRequest request) {
        mView.showProgress();
        mRepository.getManufactorServiceCenterImages(request).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
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

    public void getManufactorServiceCenterReviews(DashBoardActivity activity, ServiceCenterRequest request) {
        mView.showProgress();
        mRepository.getManufactorServiceCenterReviews(request).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
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

    public void getMyProductDetails(DashBoardActivity activity, ProductsRequest request) {
        mView.showProgress();
        mRepository.getMyProductDetails(request).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
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

    public void getMyProductFeedback(DashBoardActivity activity, ProductsRequest request) {
        mRepository.getMyProductFeedback(request).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<BaseResponse>(activity) {
            @Override
            public void onResponse(BaseResponse response) {
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

    public void getProductOffers(DashBoardActivity activity, OfferRequest request) {
        mRepository.getProductOffers(request).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DefaultApiObserver<OfferResponseData>(activity) {
            @Override
            public void onResponse(OfferResponseData response) {
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
}
