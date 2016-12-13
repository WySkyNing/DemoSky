package com.ning.mylibrary.view3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by wy on 2016/12/12.
 * 五角星
 */

/**
 *
 *  假设五角星外接圆半径为1,有一个角朝上,以五角星中心为原点:
 *  外点
 *  x=Rcos(72°*k)  y=Rsin(72°*k)   k=0,1,2,3,4
 *  内点
 *  r=Rsin(18)/sin(180-36-18)
 *  x=rcos(72°*k+36°)  y=rsin(72°*k+36°)   k=0,1,2,3,4
 *
 */

public class FiveView extends View {
    float outR; //星星大小
    float inR = 0; // 星星中心点到 凹角点（现在）的距离
    int sinX = 0;
    Paint paint;

    public FiveView(Context context) {
        this(context,null);
        // TODO Auto-generated constructor stub
    }

    public FiveView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        // TODO Auto-generated constructor stub
    }

    public FiveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int r = width / 2;
        outR = getWidth() / 2 / 10;
        inR = outR * sin(18) / sin(180 - 36 - 18);


        /** 将坐标原点移至新的坐标点 */
        canvas.translate(r / 5, r);
        Log.e("wy_111",r / 5 + "");
        /** 旋转坐标系 正顺负逆 */
        //canvas.rotate(-18);
        Path path = getCompletePath(outR, inR);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);
        path = getHalfPath(outR, inR);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, paint);


        canvas.rotate(18);
        canvas.translate(r / 5 * 2 + 15, 0);
        Log.e("wy_222",r / 5 * 2 + 15 + "");
        canvas.rotate(-18);
        path = getCompletePath(outR, inR);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, paint);


        canvas.rotate(18);
        canvas.translate(r / 5 * 2 + 15, 0);
        Log.e("wy_222",r / 5 * 2 + 15 + "");
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

        sinX =  ( sinX + 1);
        if (sinX > 360){
           sinX = 0;
        }
        invalidate();
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

    /**
     * 角度转弧度 π/180×角度
     * 弧度变角度 180/π×弧度
     * */
    float sin(int num) {
        return (float) Math.sin(num * Math.PI / 180);
    }
}
