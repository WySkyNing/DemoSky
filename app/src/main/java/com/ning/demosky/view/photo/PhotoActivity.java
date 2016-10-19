package com.ning.demosky.view.photo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.ning.demosky.R;

/**
 * Created by wy on 2016/10/8.
 */
public class PhotoActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_photo);

        Intent intent = new Intent(PhotoActivity.this, SelectPhotoActivity.class);
        startActivityForResult(intent,10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ImageView imageView = (ImageView) findViewById(R.id.img_view);

        if (null != data){

            Bundle bundle = data.getBundleExtra("EntityBundle");
            SelectPhotoAdapter.SelectPhotoEntity entity =
                    (SelectPhotoAdapter.SelectPhotoEntity) bundle.getSerializable("SelectPhotoEntity");

            Bitmap bitmap = BitmapFactory.decodeFile(entity.url);

            imageView.setImageBitmap(bitmap);
        }
    }
}
