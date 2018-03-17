package com.app.warantywise.network;

import com.app.warantywise.network.request.AddProductRequest;
import com.app.warantywise.network.request.LoginRequest;
import com.app.warantywise.network.request.UpdateProfileRequest;
import com.app.warantywise.network.request.VerifyMobileRequest;
import com.app.warantywise.network.request.dashboard.ProductDetailsRequest;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.network.response.LoginResponse;
import com.app.warantywise.network.response.VerifyMobileResponse;
import com.app.warantywise.network.response.dashboard.AllProductData;
import com.app.warantywise.network.response.dashboard.ProductDetailData;

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
    public Observable<BaseResponse> logout() {
        return apiService.logout();
    }

    @Override
    public Observable<BaseResponse> addProduct(AddProductRequest request) {
        return apiService.addProduct(request);
    }

    @Override
    public Observable<BaseResponse> yourProduct() {
        return apiService.yourProduct();
    }

    @Override
    public Observable<ProductDetailData> getProductDetails(ProductDetailsRequest request) {
        return apiService.getProductDetails(request);
    }

    @Override
    public Observable<AllProductData> getAllProductList() {
        return apiService.getAllProductList();
    }

    @Override
    public Observable<BaseResponse> updateProfile(UpdateProfileRequest updateProfileRequest) {
        return apiService.updateProfile(updateProfileRequest);
    }


}
