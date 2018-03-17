package com.app.warantywise.network;


import com.app.warantywise.network.request.LoginRequest;
import com.app.warantywise.network.request.AddProductRequest;
import com.app.warantywise.network.request.UpdateProfileRequest;
import com.app.warantywise.network.request.VerifyMobileRequest;
import com.app.warantywise.network.request.WarrantyCardImageRequest;
import com.app.warantywise.network.request.dashboard.ProductDetailsRequest;
import com.app.warantywise.network.response.BaseResponse;
import com.app.warantywise.network.response.LoginResponse;
import com.app.warantywise.network.response.VerifyMobileResponse;
import com.app.warantywise.network.response.dashboard.AllProductData;
import com.app.warantywise.network.response.dashboard.ProductDetailData;
import com.app.warantywise.network.response.dashboard.WarrantyCardImageData;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiService {
    @POST("register/user")
    Observable<LoginResponse> getLoginDetail(@Body LoginRequest request);

    @POST("register/verifyotp")
    Observable<VerifyMobileResponse> verifyMobileNumber(@Body VerifyMobileRequest request);

    @POST("register/updateprofile")
    Observable<BaseResponse> updateProfile(@Body UpdateProfileRequest request);

    @GET("register/logout")
    Observable<BaseResponse> logout();

    @POST("product/insertproductdetails")
    Observable<BaseResponse> addProduct(@Body AddProductRequest request);

    @POST("product/getallmasterproducts")
    Observable<AllProductData> getAllProductList();

    @POST("product/getmasterproducts")
    Observable<ProductDetailData> getProductDetails(@Body ProductDetailsRequest request);

    @POST("product/getmyproducts")
    Observable<BaseResponse> yourProduct();

    @POST("product/get_warrantee_card_image")
    Observable<WarrantyCardImageData> warranteeCardImage(@Body WarrantyCardImageRequest request);




}
