package com.ning.demosky.view.pullrefresh;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ning.demosky.R;

/**
 * Created by yorki on 2016/6/1.
 */
public class PullView extends LinearLayout implements View.OnTouchListener {

    private View headView;
    private Paint paint;
    private RectF rectF = new RectF();

    private float recfY = 0;

    public PullView(Context context) {
        super(context);
        init(context);
    }

    public PullView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PullView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    LayoutParams layoutParams;
    ImageView imageView;
    LinearLayout linearLayout;

    private void init(Context context) {

        this.setOnTouchListener(this);

        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);

        this.setOrientation(VERTICAL);
        linearLayout = new LinearLayout(context);
        LayoutParams layoutParams_ = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,400);
        linearLayout.setLayoutParams(layoutParams_);
        linearLayout.setBackgroundColor(Color.BLUE);
        this.addView(linearLayout,0);



        imageView = new ImageView(context);
        LayoutParams layoutParamsImg = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
        layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, -1);
        layoutParamsImg.topMargin = -300;


        imageView.setLayoutParams(layoutParamsImg);
        imageView.setImageResource(R.mipmap.ic_launcher);



        addView(imageView, 1);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        rectF.set(0, 30, getWidth(), recfY);

        canvas.drawRect(rectF, paint);
    }

    float yDown = 0;
    float distance;

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                yDown = event.getRawY();

                Log.e("ACTION_DOWN", "ACTION_DOWN");

            case MotionEvent.ACTION_MOVE:

                recfY = recfY + event.getRawY();
                float distance = (yDown - event.getRawY()) / 2;

                if (distance < 400){
                    layoutParams.topMargin = -(int) distance;
                }

                Log.e("distance",distance + "");
//                imageView.setLayoutParams(layoutParams);

//                imageView.getLayoutParams() = - (int) distance;
                this.requestLayout();

                Log.e("ACTION_MOVE", "ACTION_MOVE" + event.getRawY());
                // postInvalidate();
                break;

            case MotionEvent.ACTION_UP:

                Log.e("ACTION_UP", "ACTION_UP");

                TranslateAnimation translateAnimation = new TranslateAnimation(0,0,event.getRawY() / 2,-300);
                translateAnimation.setDuration(2000);

//                linearLayout.startAnimation(translateAnimation);
//                imageView.startAnimation(translateAnimation);
//
//                View view = this.getChildAt(2);
//                view.startAnimation(translateAnimation);

                this.startAnimation(translateAnimation);

                layoutParams.topMargin = -400;
                this.setLayoutParams(layoutParams);
        }

        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
