package com.app.warantywise.network;


import com.app.warantywise.network.request.LoginRequest;
import com.app.warantywise.network.request.AddProductRequest;
import com.app.warantywise.network.request.UpdateProfileRequest;
import com.app.warantywise.network.request.VerifyMobileRequest;
import com.app.warantywise.network.request.WarrantyCardImageRequest;
import com.app.warantywise.network.request.dashboard.ExtendeWarrantyRequest;
import com.app.warantywise.network.request.dashboard.ProductInsuranceRequest;
import com.app.warantywise.network.request.dashboard.OfferRequest;
import com.app.warantywise.network.request.dashboard.ProductDetailsRequest;
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
import com.app.warantywise.network.response.dashboard.OfferResponseData;
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
    Observable<WarrantyCardImageData> getWarrantyCardImage(@Body WarrantyCardImageRequest request);

    @POST("product/get_extend_warrentees")
    Observable<ExtendedWarrantyCardData> getExtendedWarranty(@Body ExtendeWarrantyRequest request);

    @POST("product/get_product_offers")
    Observable<OfferResponseData> getProductOffers(@Body OfferRequest request);

    @POST("product/get_product_insurance")
    Observable<ProductInsuranceResponseData> getProductInsurance(@Body ProductInsuranceRequest request);

    @POST("product/get_manufacturer_service_centers")
    Observable<ManufactorServiceCentorResponseData> getManufactorServiceCenter();

    @POST("product/get_manufacturer_service_center_detail")
    Observable<BaseResponse> getManufactorServiceCenterDetail(@Body ServiceCenterRequest request);

    @POST("product/get_manufacturer_service_center_images")
    Observable<BaseResponse> getManufactorServiceCenterImages(@Body ServiceCenterRequest request);

    @POST("product/get_manufacturer_service_center_reviews")
    Observable<BaseResponse> getManufactorServiceCenterReviews(@Body ServiceCenterRequest request);

    @POST("product/get_my_product_details")
    Observable<BaseResponse> getMyProductDetails(@Body ProductsRequest request);

    @POST("product/get_my_product_feedback")
    Observable<BaseResponse> getMyProductFeedback(@Body ProductsRequest request);

}
