package com.ning.mylibrary.view2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.ning.mylibrary.R;


/**
 * Created by wy on 2016/10/12.
 *
 */
public class CustomView2 extends View {

    /**
     * 控件得宽
     */
    private int mWidth;

    /**
     * 控件得高
     */
    private int mHeight;

    /**
     * 控件中得图片
     */
    private Bitmap mIamge;

    /**
     * 图片得缩放模式
     */
    private int mIamgeScale;
    private final int IMAGE_SCALE_FITXY = 0;
    private final int IMAGE_SCALE_CENTER = 1;

    /**
     * 图片介绍
     */
    private String mTitleText;

    /**
     * 字体大小
     */
    private int mTextSize;

    /**
     * 字体颜色
     */
    private int mTextColor;

    /**
     * 对文本的约束 文字背景
     */
    private Rect mTextBound;

    /**
     * 控制整体布局 边框
     */
    private Rect rect;

    private Paint mPaint;

    private TextPaint paint;

    public CustomView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView2(Context context) {
        this(context, null);
    }

    public CustomView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CustomView2,
                defStyleAttr, 0);

        int count = typedArray.getIndexCount();

        for (int i = 0; i < count; i++) {
            int attr = typedArray.getIndex(i);

            if (attr == R.styleable.CustomView2_image) {
                mIamge = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(attr,
                        R.mipmap.ic_launcher));

            } else if (attr == R.styleable.CustomView2_imageScaleType) {
                mIamgeScale = typedArray.getInt(attr, 0);

            } else if (attr == R.styleable.CustomView2_titleText) {
                mTitleText = typedArray.getString(attr);

            } else if (attr == R.styleable.CustomView2_titleTextSize) {
                mTextSize = typedArray.getDimensionPixelSize(attr,
                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));

            } else if (attr == R.styleable.CustomView2_titleTextColor_) {
                mTextColor = typedArray.getColor(attr, Color.BLACK);

            }
        }
        typedArray.recycle();

        mPaint = new Paint();
        rect = new Rect();
        mTextBound = new Rect();
        mPaint.setTextSize(mTextSize);

        // 计算了描绘字体需要的范围
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mTextBound);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            /**
             * match_parent(也属于定值) , accurate
             * */
            mWidth = specSize;
        } else {
            //由图片决定得宽
            int desireByImg = getPaddingLeft() + getPaddingRight() + mIamge.getWidth();

            //由字体决定得宽
            int desireByText = getPaddingLeft() + getPaddingRight() + mTextBound.width();

            if (specMode == MeasureSpec.AT_MOST) {
                /**
                 * wrap_content
                 * */
                int desireWidth = Math.max(desireByImg, desireByText);

                //desireWidth > 屏幕宽度的时候
                mWidth = Math.min(desireWidth, specSize);
            }
        }

        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {

            mHeight = specSize;
        } else {

            int desireHeight = getPaddingTop() + getPaddingBottom() + mIamge.getHeight();

            if (specMode == MeasureSpec.AT_MOST) {

                mHeight = Math.min(desireHeight, specSize);

            }
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        /**
         * 边框
         */
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        rect.left = getPaddingLeft();
        rect.right = mWidth - getPaddingRight();
        rect.top = getPaddingTop();
        rect.bottom = mHeight - getPaddingBottom();

        mPaint.setColor(mTextColor);
        mPaint.setStyle(Paint.Style.FILL);
        /**
         * 当前设置的宽度小于字体需要的宽度，将字体改为xxx...
         */
        if (mTextBound.width() > mWidth) {

            if (null == paint) paint = new TextPaint(mPaint);

            String msg = TextUtils.ellipsize(mTitleText, paint, (float) mWidth - getPaddingLeft() - getPaddingRight(),
                    TextUtils.TruncateAt.END).toString();

            canvas.drawText(msg, getPaddingLeft(), mHeight - getPaddingBottom(), mPaint);

        } else {
            //正常情况，将字体居中
            canvas.drawText(mTitleText, mWidth / 2 - mTextBound.width() * 1.0f / 2, mHeight - getPaddingBottom(), mPaint);
        }

        //取消使用掉的块儿
        rect.bottom -= mTextBound.height();

        if (mIamgeScale == IMAGE_SCALE_FITXY) {
            canvas.drawBitmap(mIamge, null, rect, mPaint);
        } else {
            //计算居中的矩形范围
            rect.left = mWidth / 2 - mIamge.getWidth() / 2;
            rect.right = mWidth / 2 + mIamge.getWidth() / 2;
            rect.top = (mHeight - mTextBound.height()) / 2 - mIamge.getHeight() / 2;
            rect.bottom = (mHeight - mTextBound.height()) / 2 + mIamge.getHeight() / 2;

            /**
             * 参数 2 要画的 bitmap 的区域，在原始 bitmap 的尺寸上截取矩形
             * 参数 3 截取之后的 bitmap 矩形要在屏幕上显示的位置
             * */
            canvas.drawBitmap(mIamge, null, rect, mPaint);

        }
    }
}
