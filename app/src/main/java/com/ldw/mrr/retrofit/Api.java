package com.ldw.mrr.retrofit;

import com.ldw.mrr.BaseEntity;
import com.ldw.mrr.weather.Weather;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by www.longdw.com on 2018/3/16 下午3:58.
 */

public interface Api {
    @GET("weather/detail")
    Observable<BaseEntity<Weather>> getData(@QueryMap Map<String, String> params);
}
