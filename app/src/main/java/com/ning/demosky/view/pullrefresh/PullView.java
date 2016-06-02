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

/**
 * Created by yorki on 2016/6/1.
 */
    public class PullView extends View implements View.OnTouchListener {

    private View headView;
    private Paint paint;
    private RectF rectF   = new RectF();

    private float recfY = 0;

    public PullView(Context context) {
        super(context);
        init();
    }

    public PullView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public PullView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){

        this.setOnTouchListener(this);

        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);

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

                Log.e("ACTION_MOVE","ACTION_MOVE" + event.getRawY());
                postInvalidate();
                break;

            case MotionEvent.ACTION_UP:

                Log.e("ACTION_UP","ACTION_UP");
        }

        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
