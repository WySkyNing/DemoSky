package com.ning.demosky.view.mvp.model;

import com.ning.demosky.view.mvp.presenter.NetworkConnectInter;

/**
 * Created by yorki on 2016/6/14.
 */
public interface ModelInter {

    /**
     * 加载网络数据
     * @param  url
     * */
    void startLoadConnect(String url, NetworkConnectInter networkConnectInter);
}
