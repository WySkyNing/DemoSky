package com.ning.demosky.view.thread;

/**
 * Created by wy on 2016/9/30.
 *
 */
public class Main {

    public static void main(String[] a) {

        /**
         * Thread 类中的 start()方法通知“线程规划器”此线程已经准备就绪，
         * 等待调用线程对象的 run() 方法，这个过程其实就是让系统安排个时间
         * 来调用 Thread 的 run() 方法，也就是启动线程，具有异步执行的效果。
         * 如果直接调用 thread.run() 方法，就不会交给“线程规划器”处理，
         * 而是主线程调用该方法，切具有同步的效果。
         *
         * 并且线程的启动顺序于调用 start（）方法的顺序无关
         * */

        /**
         * Thread 类也实现了 Runnable 接口，也就意味着构造函数 Thread(Runnable target)
         * 不光可以传入 Runnable 接口对象，也可以传入 Thread 对象，这样做完全可以将一个
         * Thread 中的 run() 方法交给其他线程来调用
         * */

        /** 继承 Thread */
        MyThreadE myThreadE = new MyThreadE();

        //Thread thread = new Thread(myThreadE);
       // thread.setName("a");
        //在 thread.start()之后 执行thread.run(); 的时候 执行了 myThreadE.run()方法，
        // 所以当前运行的线程是 Thread thread
        // 这是 Thread 里面的方法.target 通过构造函数赋值为 myThreadE
        //  public void run() {
//        if (target != null) {
//            target.run();
//        }
//    }
//      thread.start();


        myThreadE.start();

        /** 实现 Runnable */
        Runnable runnable = new MyTreadI();
        Thread myThreadI = new Thread(runnable);
        myThreadI.start();
    }
}
