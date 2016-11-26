package com.ning.demosky.view.pullrefresh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ning.demosky.R;
import com.ning.mylibrary.ViewActivity;

/**
 * Created by wy on 2016/6/1.
 *
 */
public class PullRefreshActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aitivity_pull_refresh);

        Button button  = (Button) findViewById(R.id.btn);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PullRefreshActivity.this, ViewActivity.class);
                startActivity(intent);
            }
        });
    }
}
