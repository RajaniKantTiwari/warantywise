package com.app.warantywise.injector.module;


import com.app.warantywise.BuildConfig;
import com.app.warantywise.injector.JsonExclusionStrategy;
import com.app.warantywise.injector.scope.PerApplication;
import com.app.warantywise.network.Repository;
import com.app.warantywise.network.RetrofitRepository;
import com.app.warantywise.utility.AppConstants;
import com.app.warantywise.utility.PreferenceUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 *
 */
@Module
public class NetworkModule {

    public static final String API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ";

    @Provides
    @PerApplication
    Repository provideRepository(Retrofit retrofit) {
        return new RetrofitRepository(retrofit);
    }

    @Provides
    @PerApplication
    Retrofit provideRetrofit() {
        Gson gson = new GsonBuilder()
                .setDateFormat(API_DATE_FORMAT)
                .addSerializationExclusionStrategy(new JsonExclusionStrategy())
                .addDeserializationExclusionStrategy(new JsonExclusionStrategy())
                .create();
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(1200, TimeUnit.SECONDS);
        httpClient.readTimeout(1200, TimeUnit.SECONDS);
        httpClient.writeTimeout(1200, TimeUnit.SECONDS);
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            String accessToken = PreferenceUtils.getAuthToken();
            //String accessToken ="FUPCSStq7aW1abD8yed7b_wJpgT8WBqq";
            if(accessToken!=null){
                Request.Builder requestBuilder = original.newBuilder()
                        .header(AppConstants.AUTHORIZATION, "Bearer "+accessToken)
                        .method(original.method(), original.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            } else {
                return chain.proceed(original);
            }
        });
        httpClient.addInterceptor(logging);
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(httpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

}
