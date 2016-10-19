package com.ning.demosky.view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yorki on 2016/5/27.
 * <p>
 *
 */
public abstract class BaseView extends View {

    private MyThread myThread;
    private boolean running = true;

    /**
     * 一般在代码中使用
     */
    public BaseView(Context context) {
        super(context);
    }

    /**
     * 一般在布局中使用
     */
    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 绘制的方法
     */
    protected abstract void drawSub(Canvas canvas);

    /**
     * 绘制逻辑
     */
    protected abstract void logic();

    /**
     * 不允许子类覆盖
     */
    @Override
    protected final void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (null == myThread) {
            myThread = new MyThread();
            myThread.start();
        } else {
            drawSub(canvas);
        }
    }

    /**
     * 当 view 离开屏幕的方法
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        running = false;
    }

    private class MyThread extends Thread {
        @Override
        public void run() {
            super.run();

            while (running) {

                logic();
                postInvalidate();

                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }
}
