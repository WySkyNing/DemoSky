package com.ning.demosky.view.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by wy on 2016/9/21.
 *
 */
public class MyDataBaseHelper extends SQLiteOpenHelper {

    /**
     * primary key 将 id 设为主键
     * <p/>
     * autoincrement 表示 id 列是自增长的
     * <p/>
     * real 表示浮点型
     * <p/>
     * blob 表示二进制类型
     */
    private final String CREATE_BOOK = "create table book ("
            + "id integer primary key autoincrement, "
            + "author text, "
            + "price real, "
            + "pages integer, "
            + "name text)";

    public  final String CREATE_CATEGORY = "create table Category ("
            + "id integer primary key autoincrement, "
            + "category_name text, "
            + "category_code integer)";

    private Context context;

    /**
     * @param context context
     * @param name    数据名
     * @param factory 允许在查询数据的时候返回一个自定义的 Cursor，一般传 null
     * @param version 数据库版本号
     */
    public MyDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    /**创建*/
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
        Toast.makeText(context, "数据库创建成功", Toast.LENGTH_SHORT).show();
    }

    /**升级*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop table if exists Book");
//        db.execSQL("drop table if exists Category");
//        onCreate(db);

        switch (newVersion){

            case 3:

                db.execSQL(CREATE_TEMP_BOOK);

                db.execSQL(CREATE_NEW_BOOK);

                db.execSQL(INSERT_DATA);

                db.execSQL(DROP_TEMP_BOOK);


                break;
        }

    }

    private final String CREATE_TEMP_BOOK = "alter table book rename to _temp_book";

    //private String INSERT_DATA = "insert into book select * from _temp_book";
    private final String INSERT_DATA = "insert into book select id,author,pages,name from _temp_book";

    private final String DROP_TEMP_BOOK = "drop table _temp_book";

    private final String CREATE_NEW_BOOK = "create table book ("
            + "id integer primary key autoincrement, "
            + "author text, "

            + "pages integer, "
            + "name text)";
}
