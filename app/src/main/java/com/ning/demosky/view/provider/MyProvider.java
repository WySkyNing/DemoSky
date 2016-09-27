package com.ning.demosky.view.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ning.demosky.view.base.MyApplication;
import com.ning.demosky.view.db.MyDataBaseHelper;

/**
 * Created by wy on 2016/9/26.
 *
 */
public class MyProvider extends ContentProvider {

    public static final int TABLE1_DIR = 0;
    public static final int TABLE1_ITEM = 1;
    public static final int TABLE2_DIR = 2;
    public static final int TABLE2_ITEM = 3;
    private static UriMatcher uriMatcher;

    public static final String AUTHORITY = "com.ning.demosky";
    private MyDataBaseHelper dbHelper;


    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.ning.demosky", "table1", TABLE1_DIR);
        uriMatcher.addURI("com.ning.demosky ", "table1/#", TABLE1_ITEM);
        uriMatcher.addURI("com.ning.demosky ", "table2", TABLE2_DIR);
        uriMatcher.addURI("com.ning.demosky ", "table2/#", TABLE2_ITEM);
    }


    /**
     * 初始化内容提供器的时候调用。通常会在这里完成对数据库的创建和升级等操作，
     * 返回true 表示内容提供器初始化成功，返回false 则表示失败。注意，只有当存在
     * ContentResolver 尝试访问我们程序中的数据时，内容提供器才会被初始化
     */
    @Override
    public boolean onCreate() {
        dbHelper = new MyDataBaseHelper(MyApplication.appContext,"BookStore.db",null,2);
        return true;
    }

    /**
     * 向内容提供器中添加一条数据。使用uri 参数来确定要添加到的表，待添加的数据
     * 保存在values 参数中。添加完成后，返回一个用于表示这条新记录的URI。
     */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        return null;
    }

    /**
     * 从内容提供器中删除数据。使用uri 参数来确定删除哪一张表中的数据，selection
     * 和selectionArgs 参数用于约束删除哪些行，被删除的行数将作为返回值返回。
     */
    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    /**
     * 更新内容提供器中已有的数据。使用uri 参数来确定更新哪一张表中的数据，新数
     * 据保存在values 参数中，selection 和selectionArgs 参数用于约束更新哪些行，受影响的
     * 行数将作为返回值返回。
     */
    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    /**
     * 从内容提供器中查询数据。使用uri 参数来确定查询哪张表，projection 参数用于确
     * 定查询哪些列，selection 和selectionArgs 参数用于约束查询哪些行，sortOrder 参数用于
     * 对结果进行排序，查询的结果存放在Cursor 对象中返回
     */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Cursor cursor = null;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
            // 查询table1表中的所有数据
                break;
            case TABLE1_ITEM:
            // 查询table1表中的单条数据
                break;
            case TABLE2_DIR:
            // 查询table2表中的所有数据
                break;
            case TABLE2_ITEM:
            // 查询table2表中的单条数据
                break;
            default:
                break;
        }

        return cursor;
    }

    /**
     * 根据传入的内容URI 来返回相应的MIME 类型。
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.app.provider. table1";
            case TABLE1_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.app.provider. table1";
            case TABLE2_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.app.provider. table2";
            case TABLE2_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.app.provider. table2";
            default:
                return null;

        }
    }


}
