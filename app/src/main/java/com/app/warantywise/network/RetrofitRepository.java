package com.app.warantywise.network;

import com.app.warantywise.network.request.LoginRequest;
import com.app.warantywise.network.request.VerifyMobileRequest;
import com.app.warantywise.network.request.dashboard.MerchantRequest;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.network.response.LoginResponse;
import com.app.warantywise.network.response.VerifyMobileResponse;
import com.app.warantywise.network.response.dashboard.MerchantResponseData;
import com.app.warantywise.network.response.dashboard.ReviewResponseData;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.Retrofit;


public class RetrofitRepository implements Repository {
    private ApiService apiService;

    public RetrofitRepository(Retrofit retrofit) {
        apiService = retrofit.create(ApiService.class);
    }

    @Override
    public Observable<LoginResponse> getLoginDetail(LoginRequest request) {
        return apiService.getLoginDetail(request);
    }

    @Override
    public Observable<VerifyMobileResponse> verifyMobileNumber(VerifyMobileRequest request) {
        return apiService.verifyMobileNumber(request);
    }

    @Override
    public Observable<MerchantResponseData> getMerchantDetail(MerchantRequest merchantRequest) {
        return apiService.getMerchantDetails(merchantRequest);
    }

    @Override
    public Observable<ReviewResponseData> getMerchantReviews(MerchantRequest merchantRequest) {
        return apiService.getMerchantReviews(merchantRequest);
    }

    @Override
    public Observable<BaseResponse> logout() {
        return apiService.logout();
    }


}
