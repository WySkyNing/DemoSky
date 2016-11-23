package com.ning.mylibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;



/**
 * Created by yorki on 2016/5/27.
 *
 * 设置 xml 自定义属性,在 values 文件夹下新建 attrs 文件 并设置属性
 */
public class MyView extends BaseView{

    private Paint paint;
    private float textX = 0;
    private RectF rectF;
    private float sweepAngle = 0;
    private int lineNum = 1;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        /**解析布局文件中的自定义的样式属性, 参数2 attrs declare-styleable name 获取样式属性列表*/
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        /**参数 1 样式列表里的 属性*/
        lineNum = typedArray.getInt(R.styleable.MyView_line, 1);
        /**解析完成后释放 typedArray*/
        typedArray.recycle();

    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        paint = new Paint();
        rectF = new RectF(50, 50, 150, 100);
    }

    @Override
    protected void drawSub(Canvas canvas) {

        paint.setTextSize(30);

        for (int i = 0; i < lineNum; i++) {
            canvas.drawText("MyView",textX,30 + 30 * i,paint);
        }

        canvas.drawArc(rectF,0,sweepAngle,true,paint);
    }

    @Override
    protected void logic() {

        textX += 3;
        sweepAngle ++;

        if (textX > getWidth()){ /**当前 view 的宽度*/

            textX = 0 - paint.measureText("MyView"); /**测量文字的宽度*/
        }

        if (sweepAngle > 360){
            sweepAngle = 0;
        }


    }


}
