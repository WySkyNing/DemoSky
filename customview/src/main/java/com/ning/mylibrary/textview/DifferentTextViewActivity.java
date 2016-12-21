package com.ning.mylibrary.textview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.widget.TextView;

/**
 * Created by wy on 2016/12/16.
 * TextView 显示不同大小颜色的文字
 */

public class DifferentTextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);
        String string = "同一个TextView显示不同的颜色和大小的文字";

        SpannableStringBuilder spannableStringBuilder =
                new SpannableStringBuilder(string);
        spannableStringBuilder.setSpan(new BackgroundColorSpan(Color.RED),
                                        3,
                                        5,
                                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableStringBuilder);

        setContentView(textView);



        String newMessageInfo = "<font color='#ff00ff'><b>带色内容</b></font>"
                + "<font color='blue'><big>TextView学习显示不同颜色</big></font>";

        textView.setText(Html.fromHtml(newMessageInfo));
    }
}
