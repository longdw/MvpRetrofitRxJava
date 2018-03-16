package com.ldw.mrr;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

/**
 * Created by www.longdw.com on 2017/11/24 下午1:58.
 */

public class BaseEntity<E> {
    public static String TAG;

    @SerializedName("status_code") public String statusCode;
    @SerializedName("msg") public String msg;
    @SerializedName("hasnext") public String hasnext;

    @SerializedName("data") public E data;

    public static String SUCCESS = "200";
    public static String SIGN_OUT = "30000";//token验证失败

    public BaseEntity() {
        TAG = this.getClass().getName();
    }

    public boolean isSuccess() {
        return SUCCESS.equals(statusCode);
    }

    public boolean isTokenInvalid() {
        return SIGN_OUT.equals(statusCode);
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getHasnext() {
        return hasnext;
    }

    public void setHasnext(String hasnext) {
        this.hasnext = hasnext;
    }

    public boolean hasNext() {
        return !TextUtils.isEmpty(hasnext) && hasnext.equals("1");
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}