package com.ning.demosky.view.singleton;

/**
 * Created by yorki on 2016/6/21.
 */
public class SingleInstance {

    private static volatile SingleInstance instance = null;

    public static SingleInstance getInstance(){

        SingleInstance _instance = instance;

        if (null == _instance){

            synchronized (SingleInstance.class){

                _instance = instance;

                if (null == _instance){

                    _instance = new SingleInstance();

                    instance  = _instance;
                }
            }
        }

        return _instance;
    }


    private static class SingleHolder{
        private static final SingleInstance instance = new SingleInstance();
    }

    public static SingleInstance _getInstance(){
        return SingleHolder.instance;
    }
}
