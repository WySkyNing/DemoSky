package com.ning.demosky.view.animation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ning.demosky.R;

/**
 * Created by yorki on 2016/6/7.
 */
public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private AnimationDrawable animationDrawable;

    private TextView translation; /*平移*/
    private TextView rotate;      /*选择*/
    private TextView scale;       /*缩放*/
    private TextView alpha;       /*透明度*/
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        /**帧动画*/
        ImageView imageView = (ImageView) this.findViewById(R.id.animation_activity_image_view);

        assert imageView != null : "-----------------imageView == null";
        imageView.setBackgroundResource(R.drawable.drawable_animation);

        animationDrawable = (AnimationDrawable) imageView.getBackground();


        /**补间动画 ,没有移动控件的真实位置*/
        translation = (TextView) this.findViewById(R.id.translation);
        rotate = (TextView) this.findViewById(R.id.rotate);
        scale = (TextView) this.findViewById(R.id.scale);
        alpha = (TextView) this.findViewById(R.id.alpha);
        button = (Button) this.findViewById(R.id.fire);

        assert button != null : "button == null";
        button.setOnClickListener(this);
        translation.setOnClickListener(this);
        rotate.setOnClickListener(this);
        scale.setOnClickListener(this);
        alpha.setOnClickListener(this);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN){
            animationDrawable.stop();
            return true;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            animationDrawable.start();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.translation:

                TranslateAnimation translateAnimation = new TranslateAnimation(0, 400, 0, 0);//平移
                translateAnimation.setDuration(2000);
                translation.startAnimation(translateAnimation);

                break;

            case R.id.rotate:

                RotateAnimation rotateAnimation = new RotateAnimation(0, 70);//顺时针旋转70度
                rotateAnimation.setDuration(2000);
                rotate.startAnimation(rotateAnimation);

                break;

            case R.id.scale:

                ScaleAnimation scaleAnimation = new ScaleAnimation(0, 5, 0, 5);//横向放大5倍，纵向放大5倍
                scaleAnimation.setDuration(2000);
                scale.startAnimation(scaleAnimation);

                break;

            case R.id.alpha:

                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);//从全不透明变为全透
                alphaAnimation.setDuration(2000);
                alpha.startAnimation(alphaAnimation);
                break;
        }

    }
}
