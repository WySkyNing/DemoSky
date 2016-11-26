package com.ning.mylibrary.view3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.ning.mylibrary.R;


/**
 * Created by wy on 2016/10/26.
 *
 */
public class TextView1 extends View{

    private String mText;

    private int mTextColor;

    private int mTextSize;

    private Rect rect;

    private Paint mPaint;

    public TextView1(Context context) {
        this(context,null);
    }

    public TextView1(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public TextView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomView1,
                defStyleAttr,0);

        int count = typedArray.getIndexCount();

        for (int i = 0; i < count; i++) {

            int attr = typedArray.getIndex(i);

            if (attr == R.styleable.CustomView1_text_) {
                mText = typedArray.getString(attr);

            } else if (attr == R.styleable.CustomView1_textColor) {
                mTextColor = typedArray.getColor(attr, Color.BLUE);

            } else if (attr == R.styleable.CustomView1_textSize) {
                mTextSize = typedArray.getDimensionPixelSize(attr,
                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));

            }
        }

        mPaint = new Paint();
        rect = new Rect();
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);

        mPaint.getTextBounds(mText,0,mText.length(),rect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int mWidth;
        if (MeasureSpec.EXACTLY == widthMode){

            mWidth = widthSize;
        }else {

            mWidth = getPaddingLeft() + getPaddingRight() + rect.width();
        }

        int mHeight;
        if (MeasureSpec.EXACTLY == heightMode){

            mHeight = heightSize;
        }else {

            mHeight = getPaddingTop() + getPaddingBottom() + rect.height();
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.GREEN);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);

        mPaint.setColor(mTextColor);
        canvas.drawText(mText,
                getWidth() / 2 - rect.width() /2,
                getHeight() / 2 + rect.height() / 2 ,
                mPaint);

        Log.e("wy__11",getWidth() / 2 + "  " + rect.width() / 2);
        Log.e("wy__11",getHeight() / 2 + "  " + rect.height() / 2);
        //canvas.drawText(mText, getWidth() / 2 - rect.width() / 2, getHeight() / 2 + rect.height() / 2, mPaint);



    }
}
