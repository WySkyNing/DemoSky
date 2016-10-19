package com.ning.demosky.view.mvp.presenter;

/**
 * Created by yorki on 2016/6/14.
 * <p/>
 * 原来只是一个 PresenterInter  现在分为 加载网络数据和数据库数据
 * 有 presenter 实现
 */
public interface NetworkConnectInter {

    /**
     * 开始加载网络数据请求
     * */
    void starNetRequest(String url);


    /**
     * 加载网络数据成功
     */
    void onLodeNetDataCompleted(String netResult);


    /**
     * 加载失败
     */
    void onFailed();

}
