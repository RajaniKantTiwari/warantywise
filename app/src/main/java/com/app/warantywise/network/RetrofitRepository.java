package com.app.warantywise.network;

import com.app.warantywise.network.request.AddProductRequest;
import com.app.warantywise.network.request.LoginRequest;
import com.app.warantywise.network.request.UpdateProfileRequest;
import com.app.warantywise.network.request.VerifyMobileRequest;
import com.app.warantywise.network.request.WarrantyCardImageRequest;
import com.app.warantywise.network.request.dashboard.ExtendeWarrantyRequest;
import com.app.warantywise.network.request.dashboard.OfferRequest;
import com.app.warantywise.network.request.dashboard.ProductDetailsRequest;
import com.app.warantywise.network.request.dashboard.ProductInsuranceRequest;
import com.app.warantywise.network.request.dashboard.ProductsRequest;
import com.app.warantywise.network.request.dashboard.ServiceCenterRequest;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.network.response.LoginResponse;
import com.app.warantywise.network.response.VerifyMobileResponse;
import com.app.warantywise.network.response.dashboard.AllProductData;
import com.app.warantywise.network.response.dashboard.ExtendedWarrantyCardData;
import com.app.warantywise.network.response.dashboard.ManufactorServiceCentorResponseData;
import com.app.warantywise.network.response.dashboard.OfferResponseData;
import com.app.warantywise.network.response.dashboard.ProductDetailData;
import com.app.warantywise.network.response.dashboard.ProductInsuranceResponseData;
import com.app.warantywise.network.response.dashboard.WarrantyCardImageData;

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

    @Override
    public Observable<WarrantyCardImageData> getWarrantyCardImage(WarrantyCardImageRequest request) {
        return apiService.getWarrantyCardImage(request);
    }

    @Override
    public Observable<ExtendedWarrantyCardData> getExtendedWarranty(ExtendeWarrantyRequest request) {
        return apiService.getExtendedWarranty(request);
    }

    @Override
    public Observable<ProductInsuranceResponseData> getProductInsurance(ProductInsuranceRequest request) {
        return apiService.getProductInsurance(request);
    }

    @Override
    public Observable<ManufactorServiceCentorResponseData> getManufactorServiceCenter() {
        return apiService.getManufactorServiceCenter();
    }

    @Override
    public Observable<BaseResponse> getManufactorServiceCenterDetail(ServiceCenterRequest request) {
        return apiService.getManufactorServiceCenterDetail(request);
    }

    @Override
    public Observable<BaseResponse> getManufactorServiceCenterImages(ServiceCenterRequest request) {
        return apiService.getManufactorServiceCenterImages(request);
    }

    @Override
    public Observable<BaseResponse> getManufactorServiceCenterReviews(ServiceCenterRequest request) {
        return apiService.getManufactorServiceCenterReviews(request);
    }

    @Override
    public Observable<BaseResponse> getMyProductDetails(ProductsRequest request) {
        return apiService.getMyProductDetails(request);
    }

    @Override
    public Observable<BaseResponse> getMyProductFeedback(ProductsRequest request) {
        return apiService.getMyProductFeedback(request);
    }

    @Override
    public Observable<OfferResponseData> getProductOffers(OfferRequest request) {
        return apiService.getProductOffers(request);
    }


}
