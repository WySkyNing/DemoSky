package com.ning.demosky.view.thread;

/**
 * Created by wy on 2016/9/30.
 *
 */
public class MyThreadE extends Thread{

    public MyThreadE(){
        // Thread.currentThread() 可以返回代码段正在被哪个线程调用的信息
        System.out.println("MyThreadE current =  " + Thread.currentThread().getName());
        System.out.println("MyThreadE this =   " + this.getName());
    }

    @Override
    public void run() {

        super.run();

        System.out.println("MyThreadE_run current =  " + Thread.currentThread().getName());

        System.out.println("MyThreadE_run current =  " + Thread.currentThread().isAlive());

        System.out.println("MyThreadE_run this =   " + this.getName());

        System.out.println("MyThreadE_run this =   " + this.isAlive());

    }
}
