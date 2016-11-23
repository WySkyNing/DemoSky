package com.ning.demosky.view.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.ning.demosky.R;

/**
 * Created by wy on 2016/9/21.
 *
 */
public class DbActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);

        final MyDataBaseHelper dataBaseHelper = new MyDataBaseHelper(this, "BookStore.db", null, 2);

        Button btnCreate = (Button) findViewById(R.id.btn_create);
        Button btnAdd = (Button) findViewById(R.id.btn_add);
        Button btnDelete = (Button) findViewById(R.id.btn_delete);
        Button btnUp = (Button) findViewById(R.id.btn_up);
        Button btnQuery = (Button) findViewById(R.id.btn_query);

        /**创建*/
        assert btnCreate != null;
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dataBaseHelper.getWritableDatabase();
            }
        });

        /**添加*/
        assert btnAdd != null;
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                // 开始组装第一条数据
                values.put("name", "第一行代码");
                values.put("author", "郭林");
                values.put("pages", 100);
                values.put("price", 30.96);

                /**
                 * 参数1：表名
                 * 参数2：用于在未指定添加数据的情况下给某些可为空的列自动赋值NULL，
                 *        就是当 values为 null 或者 values.size() 为 0 的时候，可以
                 *        添加一个列的名字，然后就会给这个列的值赋值为 null.
                 * 参数3：没啥说的，以键值对存数据的一个对象
                 * */
                sqLiteDatabase.insert("book", null, values);


                /** 向 user 表中插入数据 */
                ContentValues _values = new ContentValues();
                _values.put("name","wy");
                _values.put("age",16);

                sqLiteDatabase.insert("user",null,_values);
            }
        });

        /**删除*/
        assert btnDelete != null;
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
                /**pages > ?  删除pages是大于 100 的书 */

                /** 所有符合条件的都会生效 */
                db.delete("book", "pages > ?", new String[] {"90"});

            }
        });

        /**更新*/
        assert btnUp != null;
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("author","wlq");

                /**
                 * 第三、第四个参数来指定具
                 * 体更新哪几行。第三个参数对应的是SQL 语句的where 部分，表示去更新所有name 等于?
                 * 的行，而?是一个占位符，可以通过第四个参数提供的一个字符串数组为第三个参数中的每
                 * 个占位符指定相应的内容
                 *
                 *  所有符合条件的都会生效
                 * */
                db.update("book",contentValues,"name = ?",new String[]{"MyBook"});
            }
        });

        /**查询*/
        assert btnQuery != null;
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

                Cursor cursor = db.query("book", null, null, null, null, null, null);
               // Cursor cursor = db.query("book",new String[]{"name","pages"},"author = ?",new String[]{"wy"},null,null,null);

                if (cursor.moveToFirst()) {

                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));

                        Log.e("MainActivity", "book name is " + name);
                        Log.e("MainActivity", "book author is " + author);
                        Log.e("MainActivity", "book pages is " + pages);
                        Log.e("MainActivity", "book price is " + price);

                    } while (cursor.moveToNext());
                    cursor.close();
                }

                Cursor _cursor = db.query("user", null, null, null, null, null, null);
                if (_cursor.moveToFirst()){

                    while (_cursor.moveToNext()){

                        String userName = _cursor.getString(_cursor.getColumnIndex("name"));
                        String userAge  = _cursor.getString(_cursor.getColumnIndex("age"));

                        Log.e("MainActivity","user name " + userName);
                        Log.e("MainActivity","user age " + userAge);
                    }
                    _cursor.close();
                }
            }
        });

    }


}
