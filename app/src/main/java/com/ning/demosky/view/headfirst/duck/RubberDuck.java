package com.ning.demosky.view.headfirst.duck;

/**
 * Created by wy on 2016/9/20.
 *
 */
public class RubberDuck extends Duck{

    public RubberDuck(){
        flyBehaviorInter = new FlyNoWay();
        quackBehaviorInter = new QuackForZhiZhi();
    }

    @Override
    void dishPlay() {
        System.out.println("我是橡皮鸭");
    }
}
