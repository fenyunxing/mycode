package com.example.sqlitedatabasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "user.db";
    private static final int VERSION = 1;
    private Context mcontext;

    public MySQLiteOpenHelper(Context context, String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mcontext=context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建person表
        db.execSQL("create table person (_id integer primary key autoincrement, " +
                "name char(10), phone char(20), money integer(20))");
        Toast.makeText(mcontext,"person表创建成功",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
