package com.ning.mylibrary.view2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.ning.mylibrary.R;


/**
 * Created by wy on 2016/10/17.
 *
 * paint.setXfermode 设置叠加模式
 */
public class CustomView3 extends View {

    /**
     * 第一圈的颜色
     */
    private int mFirstColor;

    /**
     * 第二圈的颜色
     */
    private int mSecondColor;

    /**
     * 圆环的宽度
     */
    private int mCircleWidth;

    /**
     * 速度
     */
    private int mSpeed;

    /**
     * 当前进度
     */
    private int mProgress = 0;

    /**
     * 是否开始下一圈
     */
    private boolean isNext = false;

    private boolean isRun = true;

    private Paint mPaintFirst;

    private Paint mPaintSecond;

    private int radius,centre;


    public CustomView3(Context context) {
        this(context, null);
    }

    public CustomView3(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView3,
                defStyleAttr, 0);

        int count = typedArray.getIndexCount();

        for (int i = 0; i < count; i++) {

            int attr = typedArray.getIndex(i);

            if (attr == R.styleable.CustomView3_first_color) {
                mFirstColor = typedArray.getColor(attr, Color.BLUE);

            } else if (attr == R.styleable.CustomView3_second_color) {
                mSecondColor = typedArray.getColor(attr, Color.BLACK);

            } else if (attr == R.styleable.CustomView3_circle_width) {
                mCircleWidth = typedArray.getDimensionPixelSize(attr,
                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, getResources().getDisplayMetrics()));

            } else if (attr == R.styleable.CustomView3_speed) {
                mSpeed = typedArray.getInt(attr, 20);

            }
        }
        typedArray.recycle();

        mPaintFirst = new Paint();
        mPaintSecond = new Paint();

        mPaintFirst.setStrokeWidth(mCircleWidth); // 设置圆环的宽度
        mPaintFirst.setAntiAlias(true); // 消除锯齿
        mPaintFirst.setStyle(Paint.Style.STROKE); // 设置空心

        mPaintSecond.setStrokeWidth(mCircleWidth); // 设置圆环的宽度
        mPaintSecond.setAntiAlias(true); // 消除锯齿
        mPaintSecond.setStyle(Paint.Style.STROKE); // 设置空心

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (isRun) {

                    mProgress+= 30;

                    if (mProgress == 360) {

                        mProgress = 0;
                        isNext = !isNext;
                    }

                    postInvalidate();

                    try {
                        Thread.sleep(mSpeed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    RectF oval;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        centre = getWidth() / 2; // 获取圆心的x坐标
        /**
         * 画弧半径为 圆心位置到 弧的宽度的一半 的距离
         * */
        radius = centre - mCircleWidth - 20;// 半径

    }

    @Override
    protected void onDraw(Canvas canvas) {


        if (oval == null)

            oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius); // 用于定义的圆弧的形状和大小的界限
        if (!isNext) {// 第一颜色的圈完整，第二颜色跑
//            mPaint.setColor(mFirstColor); // 设置圆环的颜色
//            canvas.drawCircle(centre, centre, radius, mPaint); // 画出圆环
            mPaintFirst.setColor(mSecondColor); // 设置圆环的颜色
            mPaintSecond.setColor(mFirstColor);
//            canvas.drawArc(oval, -90, mProgress, false, mPaint); // 根据进度画圆弧

        } else {
//            mPaint.setColor(mSecondColor); // 设置圆环的颜色
//            canvas.drawCircle(centre, centre, radius, mPaint); // 画出圆环
            mPaintFirst.setColor(mFirstColor);
            mPaintSecond.setColor(mSecondColor); // 设置圆环的颜色
//            canvas.drawArc(oval, -90, mProgress, false, mPaint); // 根据进度画圆弧
        }
        canvas.drawRect(oval,mPaintFirst);
        /**
         * 参数 1  弧的外切矩形 确定弧的位置
         * 参数 2  弧的起始角度
         * 参数 3  确定弧长的角度 ，弧的长度
         * */
        canvas.drawArc(oval, 0, mProgress, false, mPaintFirst);
        canvas.drawArc(oval, mProgress , 360 - mProgress, false, mPaintSecond);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isRun = false;
    }
}
