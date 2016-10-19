package com.ning.demosky.view.view.view2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.ning.demosky.R;
import com.ning.demosky.view.base.MyApplication;

/**
 * Created by wy on 2016/10/19.
 *
 */
public class CustomView4 extends View {
    /**
     * 第一圈的颜色
     */
    private int mFirstColor;

    /**
     * 第二圈的颜色
     */
    private int mSecondColor;
    /**
     * 圈的宽度
     */
    private int mCircleWidth;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 当前进度
     */
    private int mCurrentCount = 3;

    /**
     * 中间的图片
     */
    private Bitmap mImage;
    /**
     * 每个块块间的间隙
     */
    private int mSplitSize;
    /**
     * 个数
     */
    private int mCount;

    private Rect mRect;

    public CustomView4(Context context) {
        this(context, null);
    }

    public CustomView4(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView4(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView4,
                defStyleAttr, 0);

        int count = typedArray.getIndexCount();

        for (int i = 0; i < count; i++) {

            int attr = typedArray.getIndex(i);

            switch (attr) {

                case R.styleable.CustomView4_c4_first_color:
                    mFirstColor = typedArray.getColor(attr, Color.BLACK);
                    break;

                case R.styleable.CustomView4_c4_second_color:
                    mSecondColor = typedArray.getColor(attr, Color.WHITE);
                    break;

                case R.styleable.CustomView4_c4_circle_width:
                    mCircleWidth = typedArray.getDimensionPixelSize(attr,
                            (int) TypedValue.applyDimension
                                    (TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;

                case R.styleable.CustomView4_c4_dot_count:
                    mCount = typedArray.getInt(attr, 20);
                    break;

                case R.styleable.CustomView4_c4_split_size:
                    mSplitSize = typedArray.getInt(attr, 20);
                    break;

                case R.styleable.CustomView4_c4_image:
                    mImage = BitmapFactory.decodeResource(getResources(),
                            typedArray.getResourceId(attr, R.mipmap.ic_launcher));
                    break;
            }
        }

        typedArray.recycle();
        mPaint = new Paint();
        mRect = new Rect();

        mPaint.setAntiAlias(true);//消除锯齿
        mPaint.setStrokeWidth(mCircleWidth); //这是弧的宽度
        mPaint.setStrokeCap(Paint.Cap.ROUND); //定义线段断点为圆头
        mPaint.setStyle(Paint.Style.STROKE); //设置空心


    }

    @Override
    protected void onDraw(Canvas canvas) {

        int centre = getWidth() / 2; //获取圆心 x 坐标
        int radius = centre - mCircleWidth / 2; //外圆半径

        drawOval(canvas,centre,radius);

/**
 * 计算内切正方形的位置
 */
        int relRadius = radius - mCircleWidth / 2;// 获得内圆的半径
        /**
         * 内切正方形的距离顶部 = mCircleWidth + relRadius - √2 / 2
         */
        mRect.left = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth;
//		mRect.left = (int) (getWidth() - 1.0f * Math.sqrt(relRadius * relRadius * 2) / 2);
//		mRect.top = (int) (getWidth() - 1.0f * Math.sqrt(relRadius * relRadius * 2) / 2);
        Log.e("left", mRect.left +
                " 内切正方形边长的一半 " + 1.0f * Math.sqrt(relRadius * relRadius * 2) / 2 +
                "   " + Math.sqrt(2) * 1.0f / 2 * relRadius);
        /**
         * 内切正方形的距离左边 = mCircleWidth + relRadius - √2 / 2
         */
        mRect.top = (int) (relRadius - Math.sqrt(2) * 1.0f / 2 * relRadius) + mCircleWidth;
        mRect.bottom = (int) (mRect.left + Math.sqrt(2) * relRadius);
        mRect.right = (int) (mRect.left + Math.sqrt(2) * relRadius);

        /**
         * 如果图片比较小，那么根据图片的尺寸放置到正中心
         */
        if (mImage.getWidth() < Math.sqrt(2) * relRadius) {
            mRect.left = (int) (mRect.left + Math.sqrt(2) * relRadius * 1.0f / 2 - mImage.getWidth() * 1.0f / 2);
            mRect.top = (int) (mRect.top + Math.sqrt(2) * relRadius * 1.0f / 2 - mImage.getHeight() * 1.0f / 2);
            mRect.right = mRect.left + mImage.getWidth();
            mRect.bottom = mRect.top + mImage.getHeight();

        }
        // 绘图
        canvas.drawBitmap(mImage, null, mRect, mPaint);

    }

    /**
     * @param centre 圆心 x 坐标
     * @param radius 外圆半径
     */
    private void drawOval(Canvas canvas, int centre, int radius) {

        /** 根据需要画的个数以及间隙计算每个块块所占的比例
         *  每个块块的长度*/
        float itemSize = (360 * 1.0f - mCount * mSplitSize) / mCount;

        /** 大圈的轨迹 用于定义的圆弧的形状和大小的界限*/
        RectF rectF =
                new RectF(centre - radius,centre - radius,centre + radius,centre + radius);

        mPaint.setColor(mFirstColor);
        for (int i = 0; i < mCount; i++) {

            //画弧
            canvas.drawArc(rectF,i * (itemSize + mSplitSize),itemSize,false,mPaint);
        }

        mPaint.setColor(mSecondColor);
        for (int i = 0; i < mCurrentCount; i++) {

            //画弧
            canvas.drawArc(rectF,i * (itemSize + mSplitSize),itemSize,false,mPaint);
        }
    }

    private float down;
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                down = event.getY();

                break;

            case MotionEvent.ACTION_UP:

                float up = event.getY();

                if (down - up > 5){

                    if (mCurrentCount == mCount){
                        Toast.makeText(MyApplication.appContext, "已经最大", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    mCurrentCount++;postInvalidate();
                }
                if (up - down > 5){

                    if (mCurrentCount == 0){
                        Toast.makeText(MyApplication.appContext, "已经最小", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    mCurrentCount--; postInvalidate();
                }

                break;

        }

        return true;
    }
}
