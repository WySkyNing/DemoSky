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
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ning.demosky.R;

/**
 * Created by yorki on 2016/6/1.
 */
    public class PullView extends LinearLayout implements View.OnTouchListener {

    private View headView;
    private Paint paint;
    private RectF rectF   = new RectF();

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
    private void init(Context context){

        this.setOnTouchListener(this);

        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);

        this.setOrientation(VERTICAL);

        imageView = new ImageView(context);
        layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
        layoutParams.topMargin = -300;
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.mipmap.ic_launcher);
        addView(imageView,0);
    }

    @Override
    protected void onDraw(Canvas canvas) {

       rectF.set(0,30,getWidth(),recfY);

        canvas.drawRect(rectF,paint);
    }

    float yDown = 0;
    float distance;
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                yDown = event.getRawY();

                Log.e("ACTION_DOWN","ACTION_DOWN");

            case MotionEvent.ACTION_MOVE:

                recfY = recfY + event.getRawY();
                float distance = (yDown - event.getRawY()) / 2;

                layoutParams.topMargin = -(int) distance;
//                imageView.setLayoutParams(layoutParams);

//                imageView.getLayoutParams() = - (int) distance;
                imageView.requestLayout();

                Log.e("ACTION_MOVE","ACTION_MOVE" + event.getRawY());
               // postInvalidate();
                break;

            case MotionEvent.ACTION_UP:

                Log.e("ACTION_UP","ACTION_UP");

                layoutParams.topMargin = -300;
                imageView.setLayoutParams(layoutParams);
        }

        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
