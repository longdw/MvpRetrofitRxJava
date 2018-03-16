package com.ldw.mrr.weather;

import android.content.Context;

import com.ldw.mrr.BaseEntity;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by www.longdw.com on 2018/3/16 下午4:05.
 */

public class WeatherPresenter extends WeatherContract.APresenter {

    WeatherContract.IModel mModel;

    public WeatherPresenter(Context context) {
        super(context);
        mModel = new WeatherModel();
    }

    @Override
    public void requestData(String code) {
        Map<String, String> params = new HashMap<>();
        params.put("code", code);
        mModel.requestData(params).subscribe(new Observer<BaseEntity<Weather>>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(BaseEntity<Weather> weatherBaseEntity) {
                mView.onRefreshSuccess(weatherBaseEntity);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
