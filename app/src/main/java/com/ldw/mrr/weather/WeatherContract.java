package com.ldw.mrr.weather;

import android.content.Context;

import com.ldw.mrr.BaseEntity;
import com.ldw.mrr.BaseMVP;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by www.longdw.com on 2018/3/16 下午4:01.
 */

public interface WeatherContract {
    interface IView extends BaseMVP.IBaseView<Weather> {

    }

    abstract class APresenter extends BaseMVP.BasePresenter<IView> {

        public APresenter(Context context) {
            super(context);
        }

        public abstract void requestData(String code);
    }

    interface IModel {
        Observable<BaseEntity<Weather>> requestData(Map<String, String> params);
    }
}
