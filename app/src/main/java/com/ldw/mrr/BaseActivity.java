package com.ldw.mrr;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by www.longdw.com on 2018/3/16 下午3:46.
 */

public abstract class BaseActivity<V extends BaseMVP.IBaseView, P extends BaseMVP.BasePresenter<V>> extends AppCompatActivity {

    public String TAG;
    protected Activity mContext;

    protected P mPresenter;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TAG = this.getClass().getName();

        //初始化Presenter
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.attach((V) this);
        }

        setContentView(getLayoutId());
        mContext = this;

        fv();
        initViews();

        handleEvents();
    }

    protected void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onResume() {
        if (mPresenter != null) {
            mPresenter.onResume();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detach();
        }
        super.onDestroy();
    }

    protected abstract P initPresenter();

    protected abstract int getLayoutId();

    protected abstract void fv();

    protected abstract void initViews();

    /** 处理UI事件 */
    protected void handleEvents() {}

    /** 从网络获数据 */
    protected void getData() {}
}