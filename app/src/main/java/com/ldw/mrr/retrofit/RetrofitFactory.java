package com.ldw.mrr.retrofit;

import android.util.Log;

import com.ldw.mrr.HttpConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by www.longdw.com on 2018/3/16 下午3:54.
 */

public class RetrofitFactory {
    public static final String TAG = RetrofitFactory.class.getSimpleName();

    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            Log.d(TAG, message);
        }
    }).setLevel(HttpLoggingInterceptor.Level.BASIC);

    public static OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(new CommonParamsInterceptor() {
                @Override
                public Map<String, String> getHeaderMap() {
                    return null;
                }

                @Override
                public Map<String, String> getQueryParamMap(Request request) {
                    //这里可以对参数进行处理，对参数加密等

                    HashMap<String, String> newParams = new HashMap<>();

                    return newParams;
                }

                @Override
                public Map<String, String> getFormBodyParamMap() {
                    return null;
                }
            })
            .connectTimeout(HttpConstants.HTTP_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(HttpConstants.HTTP_TIMEOUT, TimeUnit.SECONDS)
            .build();


    private static Api mApi;

    public static Api getInstance(String host) {
        if (mApi == null) {
            mApi = new Retrofit.Builder().baseUrl(host)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient)
                    .build()
                    .create(Api.class);
        }
        return mApi;
    }
}
