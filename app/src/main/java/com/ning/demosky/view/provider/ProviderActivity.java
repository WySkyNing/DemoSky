package com.ning.demosky.view.provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ning.demosky.R;

/**
 * Created by wy on 2016/9/27.
 *
 */
public class ProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);


        /**
         *  "content://com.ning.demosky.provider/book" 完整的叫做内容 uri
         *   com.ning.demosky.provider 这个叫做权限，一般是包名 + provider
         *   爱叫什么叫什么 ，aaaaa 也可以
         * */
        final Uri uri = Uri.parse("content://com.ning.demosky.provider/book");

        Button btnAdd = (Button) findViewById(R.id.btn_add);
        Button btnDelete = (Button) findViewById(R.id.btn_delete);
        Button btnUp = (Button) findViewById(R.id.btn_up);
        Button btnQuery = (Button) findViewById(R.id.btn_query);

        assert btnQuery != null;
        btnQuery.setText("查询（长按查本地联系人）");


        btnQuery.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                // 查询联系人数据
                Cursor cursor = getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null, null, null, null);

                assert cursor != null;
                while (cursor.moveToNext()) {
                    // 获取联系人姓名
                    String displayName = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    //获取联系人手机号
                    String number = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER));

                    Log.e("ProviderActivity","__displayName" + displayName);
                    Log.e("ProviderActivity","__number" + number);
                }
                cursor.close();
                return false;
            }
        });

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Cursor cursor = ProviderActivity.this.getContentResolver().query(uri,null,"id = ?",new String[]{"1"},null,null);

                Cursor cursor = ProviderActivity.this.getContentResolver().query(uri,null,null,null,null,null);

                if (cursor != null){
                    while (cursor.moveToNext()){

                        String bookName = cursor.getString(cursor.getColumnIndex("name"));
                        String bookAuthor = cursor.getString(cursor.getColumnIndex("author"));
                        String bookPrice = cursor.getString(cursor.getColumnIndex("price"));
                        String bookPages = cursor.getString(cursor.getColumnIndex("pages"));

                        Log.e("ProviderActivity","bookName is " + bookName);
                        Log.e("ProviderActivity","bookAuthor is " + bookAuthor);
                        Log.e("ProviderActivity","bookPrice is " + bookPrice);
                        Log.e("ProviderActivity","bookPages is " + bookPages);
                    }
                    cursor.close();
                }

            }
        });

        assert btnAdd != null;
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues values = new ContentValues();
                values.put("name","第二行");
                values.put("author","母鸡啊");
                values.put("price",10);
                values.put("pages",1000);
                getContentResolver().insert(uri,values);
            }
        });

        assert btnDelete != null;
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getContentResolver().delete(uri,"name = ?",new String[]{"第二行"});
            }
        });

        assert btnUp != null;
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues contentValues = new ContentValues();
                contentValues.put("name","第三行");
                getContentResolver().update(uri,contentValues,"price = ?" ,new String[]{"10"});
            }
        });
    }
}

