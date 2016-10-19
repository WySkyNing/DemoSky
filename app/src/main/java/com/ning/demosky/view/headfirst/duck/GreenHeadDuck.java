package com.ning.demosky.view.headfirst.duck;

/**
 * Created by wy on 2016/9/20.
 *
 */
public class GreenHeadDuck extends Duck{

    public GreenHeadDuck(){
        flyBehaviorInter = new FlyWithWings();
        quackBehaviorInter = new QuackForGuaGua();

    }

    @Override
    void dishPlay() {

        System.out.println("我是绿头鸭");
    }
}
