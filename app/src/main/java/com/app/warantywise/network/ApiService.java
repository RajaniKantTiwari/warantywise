package com.app.warantywise.network;


import com.app.warantywise.network.request.LoginRequest;
import com.app.warantywise.network.request.AddProductRequest;
import com.app.warantywise.network.request.VerifyMobileRequest;
import com.app.warantywise.network.request.dashboard.MerchantRequest;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.network.response.LoginResponse;
import com.app.warantywise.network.response.VerifyMobileResponse;
import com.app.warantywise.network.response.dashboard.MerchantResponseData;
import com.app.warantywise.network.response.dashboard.ReviewResponseData;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiService {
    @POST("register/user")
    Observable<LoginResponse> getLoginDetail(@Body LoginRequest request);

    @POST("register/verifyotp")
    Observable<VerifyMobileResponse> verifyMobileNumber(@Body VerifyMobileRequest request);

    @POST("merchant/getmerchantreviews")
    Observable<ReviewResponseData> getMerchantReviews(@Body MerchantRequest merchantRequest);

    @POST("merchant/getmerchantdetails")
    Observable<MerchantResponseData> getMerchantDetails(@Body MerchantRequest request);

    @GET("register/logout")
    Observable<BaseResponse> logout();

    @POST("product/insertproductdetails")
    Observable<BaseResponse> addProduct(@Body AddProductRequest request);

    @POST("product/getmyproducts")
    Observable<BaseResponse> yourProduct();



}
