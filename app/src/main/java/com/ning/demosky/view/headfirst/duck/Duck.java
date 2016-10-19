package com.ning.demosky.view.headfirst.duck;

/**
 * Created by wy on 2016/9/20.
 *
 */
public abstract class Duck {

    /**声明行为类型为接口类型 由子类去定义具体的引用*/
    protected FlyBehaviorInter flyBehaviorInter;
    protected QuackBehaviorInter quackBehaviorInter;

    /**抽象的外观方法*/
    abstract void dishPlay();


    /** 执行鸭子的行为 具体实现被分离又单独的行为类去实现*/
    protected void performFly(){
        flyBehaviorInter.fly();
    }

    protected void performQuack(){
        quackBehaviorInter.quack();

    }


    /** 动态改变鸭子的行为 */
    public void setFlyBehaviorInter(FlyBehaviorInter flyBehaviorInter) {
        this.flyBehaviorInter = flyBehaviorInter;
    }

    public void setQuackBehaviorInter(QuackBehaviorInter quackBehaviorInter) {
        this.quackBehaviorInter = quackBehaviorInter;
    }
}
