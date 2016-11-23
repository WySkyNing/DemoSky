package com.ning.demosky.view.view.view2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import com.ning.demosky.R;

/**
 * Created by wy on 2016/11/21.
 *
 */
public class ViewText extends View{

    private String mText;

    private int mTextColor;

    private int mTextSize;

    private Paint mPaint;

    private Rect rect;

    public ViewText(Context context) {
        this(context,null);
    }

    public ViewText(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ViewText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.CustomView1,defStyleAttr,0);

        int count = typedArray.getIndexCount();

        for (int i = 0; i < count; i++) {

            int attr = typedArray.getIndex(i);

            switch (attr){

                case R.styleable.CustomView1_text_:
                    mText = typedArray.getString(attr);
                    break;

                case R.styleable.CustomView1_textColor:
                    mTextColor = typedArray.getColor(attr, Color.BLACK);
                    break;

                case R.styleable.CustomView1_textSize:
                    mTextSize = typedArray.getDimensionPixelSize(attr,
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,16, getResources().getDisplayMetrics()));
                    break;
            }
        }
        typedArray.recycle();

        mPaint = new Paint();
        rect = new Rect();
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

        int width,height;

        if (widthMode == MeasureSpec.EXACTLY){
            width = widthSize;

        }else {

            width = getPaddingLeft() + rect.width() + getPaddingLeft();
        }

        if (heightMode == MeasureSpec.EXACTLY){
            height = heightSize;

        }else {
            height = getPaddingTop() + rect.height() + getPaddingBottom();
        }

        setMeasuredDimension(width,height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.GREEN);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);

        mPaint.setColor(mTextColor);


    }
}
