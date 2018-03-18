package com.app.warantywise.network;

import com.app.warantywise.network.request.AddProductRequest;
import com.app.warantywise.network.request.LoginRequest;
import com.app.warantywise.network.request.UpdateProfileRequest;
import com.app.warantywise.network.request.VerifyMobileRequest;
import com.app.warantywise.network.request.WarrantyCardImageRequest;
import com.app.warantywise.network.request.dashboard.ExtendeWarrantyRequest;
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
import com.app.warantywise.network.response.dashboard.ProductDetailData;
import com.app.warantywise.network.response.dashboard.ProductInsuranceResponseData;
import com.app.warantywise.network.response.dashboard.WarrantyCardImageData;

import io.reactivex.Observable;


public interface Repository {
    Observable<LoginResponse> getLoginDetail(LoginRequest request);

    Observable<VerifyMobileResponse> verifyMobileNumber(VerifyMobileRequest verifyMobileRequest);

    Observable<BaseResponse> logout();

    Observable<BaseResponse> addProduct(AddProductRequest request);

    Observable<BaseResponse> yourProduct();

    Observable<ProductDetailData> getProductDetails(ProductDetailsRequest request);

    Observable<AllProductData> getAllProductList();

    Observable<BaseResponse> updateProfile(UpdateProfileRequest updateProfileRequest);

    Observable<WarrantyCardImageData> getWarrantyCardImage(WarrantyCardImageRequest request);

    Observable<ExtendedWarrantyCardData> getExtendedWarranty(ExtendeWarrantyRequest request);

    Observable<ProductInsuranceResponseData> getProductInsurance(ProductInsuranceRequest request);

    Observable<ManufactorServiceCentorResponseData> getManufactorServiceCenter();

    Observable<BaseResponse> getManufactorServiceCenterDetail(ServiceCenterRequest request);

    Observable<BaseResponse> getManufactorServiceCenterImages(ServiceCenterRequest request);

    Observable<BaseResponse> getManufactorServiceCenterReviews(ServiceCenterRequest request);

    Observable<BaseResponse> getMyProductDetails(ProductsRequest request);

    Observable<BaseResponse> getMyProductFeedback(ProductsRequest request);
}
