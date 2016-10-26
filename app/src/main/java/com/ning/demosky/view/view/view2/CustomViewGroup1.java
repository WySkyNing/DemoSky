package com.ning.demosky.view.view.view2;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wy on 2016/10/19.
 * <p>
 * getWidth(): View在设定好佈局后整个View的宽度。
 * getMeasuredWidth(): 对View上的内容进行测量后得到的View内容佔据的宽度，
 * 前提是你必须在父佈局的onLayout()方法或者此View的onDraw()方法裡调 用measure(0,0);
 * (measure 参数的值你可以自己定义)，否则你得到的结果和getWidth()得到的结果一样。
 * 关於这两个方法的区别就是看你有没有用measure()方法，当然measure（）的位置也是很重要的。
 */
public class CustomViewGroup1 extends ViewGroup {

    /** 只是重写了两个方法 一个测量自己 一个测量子 view */
    public CustomViewGroup1(Context context) {
        super(context);
    }

    public CustomViewGroup1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewGroup1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * onLayout方法是ViewGroup中子View的布局方法，用于放置子View的位置
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        Log.e("CustomViewGroup", "onLayout");
        int cCount = getChildCount();
        int cWidth;
        int cHeight;
        MarginLayoutParams marginLayoutParams;

        /**
         * 遍历所有childView根据其宽和高，以及margin进行布局
         */
        for (int i = 0; i < cCount; i++) {

            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            marginLayoutParams = (MarginLayoutParams) childView.getLayoutParams();

            int cl = 0, ct = 0, cr, cb;

            switch (i) {
                case 0:
                    cl = marginLayoutParams.leftMargin;
                    ct = marginLayoutParams.topMargin;
                    break;

                case 1:
                    cl = getWidth() - cWidth - marginLayoutParams.rightMargin;
                    ct = marginLayoutParams.topMargin;
                    break;

                case 2:
                    cl = marginLayoutParams.leftMargin;
                    ct = getHeight() - cHeight - marginLayoutParams.bottomMargin;
                    break;

                case 3:
                    cl = getWidth() - cWidth - marginLayoutParams.leftMargin;
                    ct = getHeight() - cHeight - marginLayoutParams.bottomMargin;
                    break;
            }
            cr = cl + cWidth;
            cb = ct + cHeight;
            childView.layout(cl, ct, cr, cb);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        Log.e("CustomViewGroup", "generateLayoutParams");
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("CustomViewGroup", "onMeasure");

        /** 获得此 ViewGroup 上级容器为其推荐的宽和高，以及计算模式
         *  获取该ViewGroup父容器为其设置的计算模式和尺寸，
         *  大多情况下，只要不是wrap_content，父容器都能正确的计算其尺寸*/
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        /** 计算出所有 childView 的宽和高
         *  通过ViewGroup的measureChildren方法为其所有的孩子设置宽和高，
         *  此行执行完成后，childView的宽和高都已经正确的计算过了*/
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        /** 记录如果是 wrap_content 计算出的 ViewGroup (本类)的宽和高 */
        int width;
        int height;

        int cCount = getChildCount();

        int cWidth;
        int cHeight;
        MarginLayoutParams marginLayoutParams;

        // 用于计算左边两个 childView 的高度
        int leftChildAllHeight = 0;

        // 用于计算右边两个 childView 的高度
        /** 最终高度取两者之间最大值 */
        int rightChildAllHeight = 0;

        //用于计算上边两个 childView 的宽度
        int topChildAllWidth = 0;

        //用于计算下边两个 childView 的宽度
        /** 最终宽度取两者之间最大值 */
        int bottomChildAllWidth = 0;

        /** 根据 ChileView 测量得出得宽和高以及 Margin 的值，计算出 ViewGroup 的宽和高
         *  主要用于 warp_content
         *  */
        for (int i = 0; i < cCount; i++) {

            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            marginLayoutParams = (MarginLayoutParams) childView.getLayoutParams();

            if (i == 0 || i == 1) {
                topChildAllWidth += cWidth + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            }

            if (i == 2 || i == 3) {
                bottomChildAllWidth += cWidth + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            }

            if (i == 0 || i == 2) {
                leftChildAllHeight += cHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            }

            if (i == 1 || i == 3) {
                rightChildAllHeight += cHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            }
        }

        width = Math.max(topChildAllWidth, bottomChildAllWidth);
        height = Math.max(leftChildAllHeight, rightChildAllHeight);


        /** 如果是 warp_content 则设置我们计算的值
         *  否则 直接设置父容器计算的值 （本类的父容器） */
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : width,
                heightMode == MeasureSpec.EXACTLY ? heightSize : height);
    }
}
