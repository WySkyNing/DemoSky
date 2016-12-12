package com.ning.mylibrary.view3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wy on 2016/12/12.
 */

public class FiveView extends View {

    public FiveView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public FiveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public FiveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
    }

    @SuppressLint("NewApi")
    public FiveView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        // TODO Auto-generated constructor stub
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int r = width / 2;

        float outR = getWidth() / 2 / 10;
        float inR = outR * sin(18) / sin(180 - 36 - 18);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);


        canvas.translate(r / 5, r);
        canvas.rotate(-18);
        Path path = getCompletePath(outR, inR);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);
        path = getHalfPath(outR, inR);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, paint);


        canvas.rotate(18);
        canvas.translate(r / 5 * 2 + 15, 0);
        canvas.rotate(-18);
        path = getCompletePath(outR, inR);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, paint);


        canvas.rotate(18);
        canvas.translate(r / 5 * 2 + 15, 0);
        canvas.rotate(-18);
        path = getCompletePath(outR, inR);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);


        canvas.rotate(18);
        canvas.translate(r / 5 * 2 + 15, 0);
        canvas.rotate(-18);
        path = getHalfPath(outR, inR);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);


        canvas.rotate(18);
        canvas.translate(r / 5 * 2 + 15, 0);
        canvas.rotate(-18);
        path = getHalfPath(outR, inR);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, paint);

    }

    private Path getHalfPath(float outR, float inR) {
        Path path;
        path = new Path();

        path.moveTo(outR * cos(72 * 4), outR * sin(72 * 4));

        path.lineTo(inR * cos(72 * 1 + 36), inR * sin(72 * 1 + 36));
        path.lineTo(outR * cos(72 * 2), outR * sin(72 * 2));
        path.lineTo(inR * cos(72 * 2 + 36), inR * sin(72 * 2 + 36));
        path.lineTo(outR * cos(72 * 3), outR * sin(72 * 3));
        path.lineTo(inR * cos(72 * 3 + 36), inR * sin(72 * 3 + 36));

        path.close();
        return path;
    }

    private Path getCompletePath(float outR, float inR) {
        Path path = new Path();

        path.moveTo(outR * cos(72 * 0), outR * sin(72 * 0));

        path.moveTo(outR * cos(72 * 0), outR * sin(72 * 0));
        path.lineTo(inR * cos(72 * 0 + 36), inR * sin(72 * 0 + 36));
        path.lineTo(outR * cos(72 * 1), outR * sin(72 * 1));
        path.lineTo(inR * cos(72 * 1 + 36), inR * sin(72 * 1 + 36));
        path.lineTo(outR * cos(72 * 2), outR * sin(72 * 2));
        path.lineTo(inR * cos(72 * 2 + 36), inR * sin(72 * 2 + 36));
        path.lineTo(outR * cos(72 * 3), outR * sin(72 * 3));
        path.lineTo(inR * cos(72 * 3 + 36), inR * sin(72 * 3 + 36));
        path.lineTo(outR * cos(72 * 4), outR * sin(72 * 4));
        path.lineTo(inR * cos(72 * 4 + 36), inR * sin(72 * 4 + 36));
        path.close();
        return path;
    }


    float cos(int num) {
        return (float) Math.cos(num * Math.PI / 180);
    }

    float sin(int num) {
        return (float) Math.sin(num * Math.PI / 180);
    }
}
