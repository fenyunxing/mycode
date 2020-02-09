package com.example.sqlitedatabasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7;
    private int version=1;
   MySQLiteOpenHelper mySQLiteOpenHelper;
   SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();//初始化控件

    }

    public void initview() {
        btn1 = (Button) findViewById(R.id.create_database_btn);
        btn2 = (Button) findViewById(R.id.create_table_btn);
        btn3 = (Button) findViewById(R.id.insert_btn);
        btn4 = (Button) findViewById(R.id.delete_btn);
        btn5 = (Button) findViewById(R.id.update_btn);
        btn6 = (Button) findViewById(R.id.query_btn);
        btn7 = (Button) findViewById(R.id.droptable_btn);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_database_btn:
                //实例化一个 mySQLiteOpenHelper对象用来构建数据库
                mySQLiteOpenHelper=new MySQLiteOpenHelper(this,"user.db",null,1);
                db=mySQLiteOpenHelper.getWritableDatabase();//创建数据库的方法
               // Toast.makeText(MainActivity.this,"user.db数据库创建成",Toast.LENGTH_SHORT).show();
                break;
            case R.id.create_table_btn:

                break;
            case R.id.delete_btn:
                //删除_id=2的这条数据
                db.delete("person","_id=?",new String[]{"2"});
                break;
            case R.id.insert_btn:
                ContentValues insertValues=new ContentValues();//获取存放内容的对象
                //往对象里添加第一条数据
                insertValues.put("name","tom");
                insertValues.put("phone","123456789");
                insertValues.put("money",20);
                //把数据插入到数据库的person表
                db.insert("person",null,insertValues);
                //清空内容对象
                insertValues.clear();
                //往对象里放第二条数据
                insertValues.put("name","bob");
                insertValues.put("phone","1160960310");
                insertValues.put("money",50);
                //把数据插入到数据库的person表
                db.insert("person",null,insertValues);
                break;
            case R.id.query_btn:
                //查询表获得游标
                Cursor cursor = db.query ("person",null,null,null,null,null,null);
                //判断游标是否为空，并移动到开始位置
                if(cursor.moveToFirst()) {
                //从第一条开始遍历游标
                for(int i=0;i<cursor.getCount();i++){
                    cursor.move(i);//移动到索引位置
                    int id = cursor.getInt(0);//获得第i条的0列ID
                    String username=cursor.getString(1);//获得第I条的1列用户名
                    String phonenumber=cursor.getString(2); //获得第i条的2列密码
                    int money=cursor.getInt(3); //获得第i条的3列钱
                    //输出用户信息
                    Log.i("MainActivity", "_id:"+id);
                    Log.i("MainActivity", "username:"+username);
                    Log.i("MainActivity", "phonenumber:"+phonenumber);
                    Log.i("MainActivity", "money:"+money);
                }
            }

            break;
            case R.id.update_btn:
                ContentValues updateValues=new ContentValues();//获取存放内容的对象
                updateValues.put("money",20);//放要更改的数据
                //更改_id=1的这条数据的money
                db.update("person",updateValues,"_id=?",new String[]{"1"});
                break;
            case R.id.droptable_btn:
                break;
            default:
                break;
        }
    }


}
