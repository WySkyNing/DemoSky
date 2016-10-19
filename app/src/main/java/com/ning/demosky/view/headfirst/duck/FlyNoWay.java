package com.ning.demosky.view.headfirst.duck;

/**
 * Created by wy on 2016/9/20.
 */
public class FlyNoWay implements FlyBehaviorInter{

    @Override
    public void fly() {
        System.out.println("不会飞");
    }
}
