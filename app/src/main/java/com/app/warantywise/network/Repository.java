package com.app.warantywise.network;

import com.app.warantywise.network.request.AddProductRequest;
import com.app.warantywise.network.request.LoginRequest;
import com.app.warantywise.network.request.VerifyMobileRequest;
import com.app.warantywise.network.request.dashboard.ProductDetailsRequest;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.network.response.LoginResponse;
import com.app.warantywise.network.response.VerifyMobileResponse;
import com.app.warantywise.network.response.dashboard.GetProductDetailData;

import io.reactivex.Observable;


public interface Repository {
    Observable<LoginResponse> getLoginDetail(LoginRequest request);

    Observable<VerifyMobileResponse> verifyMobileNumber(VerifyMobileRequest verifyMobileRequest);

    Observable<BaseResponse> logout();

    Observable<BaseResponse> addProduct(AddProductRequest request);

    Observable<BaseResponse> yourProduct();

    Observable<GetProductDetailData> getProductDetails(ProductDetailsRequest request);
}
