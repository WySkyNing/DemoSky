package com.ning.demosky.view.view.view2;

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

import com.ning.demosky.R;

/**
 * Created by wy on 2016/10/11.
 *
 */

/**
 * set：属性值的集合
 * <p>
 * 　　　　attrs：我们要获取的属性的资源ID的一个数组，
 * 如同ContextProvider中请求数据库时的Projection数组，
 * 就是从一堆属性中我们希望查询什么属性的值
 * <p>
 * 　　　　defStyleAttr：这个是当前Theme中的一个attribute，
 * 是指向style的一个引用，当在layout xml中和style中都没有为View指定属性时，
 * 会从Theme中这个attribute指向的Style中查找相应的属性值，这就是defStyle的意思，
 * 如果没有指定属性值，就用这个值，所以是默认值，但这个attribute要在Theme中指定，
 * 且是指向一个Style的引用，如果这个参数传入0表示不向Theme中搜索默认值
 * <p>
 * 　　　　defStyleRes：这个也是指向一个Style的资源ID，
 * 但是仅在defStyleAttr为0或defStyleAttr不为0但Theme中没有为defStyleAttr属性赋值时起作用
 * <p>
 * 　　链接中对这个函数说明勉强过得去，这里简要概括一下。
 * 对于一个属性可以在多个地方指定它的值，如XML直接定义，style，Theme，
 * 而这些位置定义的值有一个优先级，按优先级从高到低依次是：
 * <p>
 * 直接在XML中定义>style定义>由defStyleAttr和defStyleRes指定的默认值>直接在Theme中指定的值
 */

public class CustomView1 extends View {

    private String sText; // 文本

    private int iTextColor; // 文本颜色

    private int iTextSize; // 文本大小

    //绘制文本的背景
    private Rect rect;
    private Paint paint;


    public CustomView1(Context context) {
        /**
         * 调用本类另外的构造方法
         * */
        this(context, null);
    }

    public CustomView1(Context context, AttributeSet attrs) {
        /**
         * 调用本类另外的构造方法
         * */
        this(context, attrs, 0);
    }


    public CustomView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        /**
         * 获取自定以属性值
         * */
        TypedArray typedArray = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.CustomView1, defStyleAttr, 0);

        int count = typedArray.getIndexCount();

        for (int i = 0; i < count; i++) {

            int attr = typedArray.getIndex(i);

            switch (attr) {
                case R.styleable.CustomView1_text_:
                    sText = typedArray.getString(attr);
                    break;

                case R.styleable.CustomView1_textColor:
                    iTextColor = typedArray.getColor(attr, Color.BLACK);
                    break;

                /**
                 * TypedValue.applyDimension()
                 * 把Android系统中的非标准度量尺寸转变为标准度量尺寸 (Android系统中的标准尺寸是px, 即像素)
                 * TypedValue.COMPLEX_UNIT_SP 还有 DIP IN PT 等
                 * 16 要转换的值
                 * getResources().getDisplayMetrics())  资源的当前显示指标（屏幕当前标准？）
                 * */
                case R.styleable.CustomView1_textSize:
                    iTextSize = typedArray.getDimensionPixelSize(attr,
                            (int) TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
            }
        }
        typedArray.recycle();

        paint = new Paint();
        rect = new Rect();
       // Circle circle = new Circle();
        paint.setTextSize(iTextSize);
        /**
         * 获得包裹文字的最小矩形
         * */
        paint.getTextBounds(sText, 0, sText.length(), rect);
    }

    /**
     * EXACTLY：父元素决定子元素的确切大小，子元素将被限定在给定的边界里而忽略它本身大小(定值 || MATCH_PARENT)
     * MATCH_PARENT（也是定值），意味着这个视图需要和它父视图一样大的尺寸（减去padding）
     * <p>
     * AT_MOST：子元素至多达到指定大小的值，一般为WARP_CONTENT
     * WRAP_CONTENT，意味着这个视图需要足够装下内容的尺寸（加上padding）
     * <p>
     * UNSPECIFIED：父元素不对子元素施加任何束缚，子元素可以得到任意想要的大小
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        /***/
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        Log.e("wy_1",widthSize + "  " + heightSize);

        int width;
        int height;


        switch (widthMode){
            case MeasureSpec.EXACTLY:
                Log.e("wy_","EXACTLY");
                break;
            case MeasureSpec.AT_MOST:
                Log.e("wy_","AT_MOST");
                break;
            case MeasureSpec.UNSPECIFIED:
                Log.e("wy_","UNSPECIFIED");
                break;
        }

        if (widthMode == MeasureSpec.EXACTLY){

            width = widthSize;

        }else {

            float textWidth = rect.width();

            width = (int) (getPaddingLeft() + textWidth + 5+ getPaddingRight());

//            float density = getResources().getDisplayMetrics().density;
//            float w = getPaddingLeft() + textWidth + getPaddingRight();
//            width = DisplayUtil.dip2px(w,density);

            Log.e("wy_",textWidth + "  s  " + width + "  " +getPaddingLeft() + "  " + getPaddingRight());
//            Log.e("wy_1",w + "  " + density);
        }

        if (heightMode == MeasureSpec.EXACTLY){

            height = heightSize;

        }else {

            float textHeight = rect.height();
            height = (int)(getPaddingTop() + textHeight + getPaddingBottom());
        }

        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.GREEN);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);

        paint.setColor(iTextColor);

        //目的 上下居中
//        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
//        int baseline = (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        /**
         * 参数 2 ，3 指文字在(当前 view )的位置 y 为文字底部线的位置
         * 类似于 padding
         * */
        canvas.drawText(sText, getWidth() / 2 - rect.width() / 2, getHeight() / 2 + rect.height() / 2, paint);
        //canvas.drawText(sText, 0,10, paint);
        //canvas.drawText(sText, getWidth() / 2 - rect.width() / 2, baseline, paint);

    }

}
