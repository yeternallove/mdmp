package com.eternallove.mdmp.api;

import com.eternallove.mdmp.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/3/21 16:09
 */
public class Client {
    private static final String BaseUrl = "http://{localhost}:8088/mdmp/";
    private static Client sClient;

    private String mUserId = "";
    private Server mService;

    public static Client getInstance() {
        if (sClient == null) {
            sClient = new Client();
        }
        return sClient;
    }

    private Client() {
        mService = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createClient())
                .build()
                .create(Server.class);

    }

    private OkHttpClient createClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
        }
//        initParameter(builder);
        initTimeOut(builder);
        //将这些配置设置给retrofit
        return builder.build();
    }

    //获取服务器地址
    private String getBaseUrl() {
        return BaseUrl.replace("{localhost}", "106.14.201.240");
    }

    //设置超时和重连
    private void initTimeOut(OkHttpClient.Builder builder) {
        //设置超时
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
    }

    //添加公共参数
    private void initParameter(OkHttpClient.Builder builder) {
        Interceptor parameterInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request request;
                String method = originalRequest.method();
                Headers headers = originalRequest.headers();
                HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                        .addQueryParameter("platform", "android")
                        .addQueryParameter("version", "1.0.0")
                        .build();
                request = originalRequest.newBuilder().url(modifiedUrl).build();
                return chain.proceed(request);
            }
        };
        builder.addInterceptor(parameterInterceptor);
    }

}
