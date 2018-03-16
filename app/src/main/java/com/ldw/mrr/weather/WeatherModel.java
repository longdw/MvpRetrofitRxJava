package com.ldw.mrr.weather;

import com.ldw.mrr.BaseEntity;
import com.ldw.mrr.HttpConstants;
import com.ldw.mrr.retrofit.RetrofitFactory;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by www.longdw.com on 2018/3/16 下午4:03.
 */

public class WeatherModel implements WeatherContract.IModel {

    @Override
    public Observable<BaseEntity<Weather>> requestData(Map<String, String> params) {
        return RetrofitFactory.getInstance(HttpConstants.HOST).getData(params).subscribeOn(Schedulers.io()).observeOn
                (AndroidSchedulers.mainThread());
    }
}
