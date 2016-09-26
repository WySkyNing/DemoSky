package com.ning.demosky.view.headfirst.duck;

/**
 * Created by wy on 2016/9/20.
 *
 */
public class Main {

    public static void main(String[] a){

        GreenHeadDuck greenHeadDuck = new GreenHeadDuck();
        greenHeadDuck.dishPlay();
        greenHeadDuck.performFly();
        greenHeadDuck.performQuack();


        RubberDuck rubberDuck = new RubberDuck();
        rubberDuck.dishPlay();
        rubberDuck.performFly();
        rubberDuck.performQuack();
    }
}
