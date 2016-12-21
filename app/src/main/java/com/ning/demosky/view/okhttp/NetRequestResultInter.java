package com.ning.demosky.view.okhttp;

/**
 * Created by yorki on 2016/12/21.
 */

public interface NetRequestResultInter {

    <T> void onNetRequestSuccess(T result,String tag);

    <T> void onNetRequestError(T errorInfo);
}
