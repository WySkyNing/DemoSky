package com.ning.demosky.view.view.view2;

import android.content.Context;

import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;


/**
 * Created by wy on 2016/10/20.
 *
 */
public class CustomViewGroup2 extends LinearLayout{

    private ViewDragHelper viewDragHelper;

    private View cView1,cView2,cView3;

    private Point mPoint = new Point();

    public CustomViewGroup2(Context context) {
        super(context);
    }

    public CustomViewGroup2(Context context, AttributeSet attrs) {
        super(context, attrs);

        /** 其中1.0f是敏感度参数参数越大越敏感 */
        viewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {

            /** 返回true则表示可以捕获该view，你可以根据传入的第一个view参数决定哪些可以捕获 */
            @Override
            public boolean tryCaptureView(View child, int pointerId) {

                return  child == cView1 || child == cView2;
            }


            /** 可以在该方法中对child移动的边界进行控制，left , top 分别为即将移动到的位置，
             * 比如横向的情况下，我希望只在ViewGroup的内部移动，即：最小>=padding left，
             * 最大<=ViewGroup.getWidth()-padding right-child.getWidth */
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {

                final int leftBound = getPaddingLeft();
                final int rightBound = getWidth() - child.getWidth() - leftBound;

                return Math.min(Math.max(left,leftBound),rightBound);
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {

                return top;
            }


            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);

                //mAutoBackView手指释放时可以自动回去
                if (releasedChild == cView2)
                {
                    /** 设置的是手指当前触摸的子 View  ，viewDragHelper 里好像有存储*/
                    viewDragHelper.settleCapturedViewAt(mPoint.x, mPoint.y);
                    invalidate();
                }

            }


            /** 边缘滑动回调 */
            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                super.onEdgeDragStarted(edgeFlags, pointerId);
                //capture 捕获
                viewDragHelper.captureChildView(cView3, pointerId);
            }


            /**
             * 我们把我们的TextView全部加上clickable=true，意思就是子View可以消耗事件
             * 再次运行，你会发现本来可以拖动的View不动了
             * 主要是因为，如果子View不消耗事件，那么整个手势（DOWN-MOVE*-UP）都是直接进入onTouchEvent，
             * 在onTouchEvent的DOWN的时候就确定了captureView。
             * 如果消耗事件，那么就会先走onInterceptTouchEvent方法，判断是否可以捕获，
             * 而在判断的过程中会去判断另外两个回调的方法：
             * getViewHorizontalDragRange和getViewVerticalDragRange，
             * 只有这两个方法返回大于0的值才能正常的捕获。
             * */
            @Override
            public int getViewHorizontalDragRange(View child) {
                return getMeasuredWidth() - child.getMeasuredWidth();
            }

            @Override
            public int getViewVerticalDragRange(View child) {
                return super.getViewVerticalDragRange(child);
            }
        });

        /** 设置边缘滑动方向 */
        viewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }


    public CustomViewGroup2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /** view2 复位 */
    @Override
    public void computeScroll() {
        super.computeScroll();

        if (viewDragHelper.continueSettling(true)){
            invalidate();
        }
    }

    /** 使用viewDragHelper.shouldInterceptTouchEvent(event)来决定我们是否应该拦截当前的事件
     *  ViewDragHelper 中拦截和处理事件时，需要回调 ViewDragHelper.CallBack 中的很多方法来决定一些事，
     *  比如：哪些子 view 可以移动，对这个移动的子 view 边界的控制等等*/
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    /**
     * 当View中所有的子控件均被映射成xml后触发；
     MyView mv = (MyView)View.inflate (context,R.layout.my_view,null);
     当加载完成xml后，就会执行那个方法；
     我们一般使用View的流程是在onCreate中使用setContentView来设置要显示Layout文件或直接创建一个View，
     在当设置了ContentView之后系统会对这个View进行解析，然后回调当前视图View中的onFinishInflate方法。
     只有解析了这个View我们才能在这个View容器中获取到拥有Id的组件，
     同样因为系统解析完View之后才会调用onFinishInflate方法，
     所以我们自定义组件时可以onFinishInflate方法中获取指定子View的引用。
     * */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        cView1 = getChildAt(0);
        cView2 = getChildAt(1);
        cView3 = getChildAt(2);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mPoint.x = cView2.getLeft();
        mPoint.y = cView2.getTop();
    }

}
