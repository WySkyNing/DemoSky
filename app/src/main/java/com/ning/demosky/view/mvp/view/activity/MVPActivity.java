package com.ning.demosky.view.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.ning.demosky.R;
import com.ning.demosky.view.mvp.Base.BaseActivity;
import com.ning.demosky.view.mvp.presenter.MVPPresenter;
import com.ning.demosky.view.mvp.presenter.NetworkConnectInter;
import com.ning.demosky.view.mvp.view.ViewInter;

/**
 * Created by yorki on 2016/6/14.
 *
 */
public class MVPActivity extends BaseActivity implements ViewInter{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);

        NetworkConnectInter networkConnectInter = new MVPPresenter(this);

        String url = "http://www.baidu.com";
        networkConnectInter.starNetRequest(url);
    }

    @Override
    public void showLoadData(Object result) {
        //Log.e("result_mvp",result.toString()+"-------");
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void dealError() {

    }
}
