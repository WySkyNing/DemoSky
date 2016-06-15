package com.ning.demosky.view.mvp.view;

/**
 * Created by yorki on 2016/6/14.
 */
public interface ViewInter<T> {

    /**
     * @param result 加载时返回的网络数据的实体类对象
     * */
    void showLoadData(T result);

    void showLoading();

    void hideLoading();

    /**
     * 处理一些错误
     * */
    void dealError();
}
