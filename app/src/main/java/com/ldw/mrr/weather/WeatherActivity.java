package com.ldw.mrr.weather;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ldw.mrr.BaseActivity;
import com.ldw.mrr.BaseEntity;
import com.ldw.mrr.R;

public class WeatherActivity extends BaseActivity<WeatherContract.IView, WeatherPresenter> implements WeatherContract.IView {

    private EditText mCodeEt;
    private Button mBtn;

    @Override
    protected WeatherPresenter initPresenter() {
        return new WeatherPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void fv() {
        mCodeEt = findViewById(R.id.codeEt);
        mBtn = findViewById(R.id.btn);
    }

    @Override
    protected void initViews() {
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = mCodeEt.getText().toString();
                mPresenter.requestData(code);
            }
        });
    }

    @Override
    public void onRefreshSuccess(BaseEntity<Weather> entity) {
        //这里对数据进行处理
    }

    @Override
    public void onRefreshError(Throwable e) {

    }

    @Override
    public void onError(BaseEntity entity) {

    }
}
