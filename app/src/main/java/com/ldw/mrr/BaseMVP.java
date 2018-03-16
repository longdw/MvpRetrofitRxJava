package com.ldw.mrr;

import android.content.Context;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 管理MVP接口
 * Created by www.longdw.com on 2017/11/29 下午4:14.
 */

public interface BaseMVP {

    interface IBaseView<D> {
        void onRefreshSuccess(BaseEntity<D> entity);
        void onRefreshError(Throwable e);
        void onError(BaseEntity entity);
    }

    interface IBaseLoadView<D> extends IBaseView<D> {
        void onLoadSuccess(BaseEntity<D> entity);
        void onLoadError(Throwable e);
    }


    class BasePresenter<V extends IBaseView> {
        protected String TAG;
        protected V mView;
        protected Context mContext;

        //将所有正在处理的Subscription都添加到CompositeSubscription中。统一退出的时候注销观察
        private CompositeDisposable mCompositeDisposable;

        public BasePresenter(Context context) {
            mContext = context;
            TAG = this.getClass().getName();
        }

        public void attach(V view) {
            mView = view;
        }

        public void detach() {
            this.mView = null;
            this.mContext = null;

            unDisposable();
        }

        public void onResume() {
        }

        public void addDisposable(Disposable subscription) {
            if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
                mCompositeDisposable = new CompositeDisposable();
            }
            mCompositeDisposable.add(subscription);
        }

        /**
         * 在界面退出等需要解绑观察者的情况下调用此方法统一解绑，防止Rx造成的内存泄漏
         */
        public void unDisposable() {
            if (mCompositeDisposable != null) {
                mCompositeDisposable.dispose();
            }
        }
    }

    class BaseLoadPresenter<V extends IBaseLoadView> extends BasePresenter<V> {
        private static final int PAGE_SIZE = 6;

        protected int mPageIndex = 1;
        protected int mPageSize = PAGE_SIZE;

        public BaseLoadPresenter(Context context) {
            super(context);
        }
    }
}
