package com.ning.demosky.view.mvp.presenter;


import com.ning.demosky.view.mvp.model.MVPModel;
import com.ning.demosky.view.mvp.model.ModelInter;
import com.ning.demosky.view.mvp.view.ViewInter;
import com.ning.demosky.view.mvp.view.activity.MVPActivity;

/**
 * Created by wy on 2016/6/14.
 *
 */
public class MVPPresenter implements NetworkConnectInter{

    private ViewInter viewInter;
    private ModelInter modelInter;

    public MVPPresenter(MVPActivity mvpActivity){

        viewInter = mvpActivity;
        modelInter = new MVPModel();
    }

    @Override
    public void starNetRequest(String url) {
        modelInter.startLoadConnect(url,this);

    }

    @Override
    public void onLodeNetDataCompleted(String netResult) {

        viewInter.showLoadData(netResult);

    }

    @Override
    public void onFailed() {

    }
}
