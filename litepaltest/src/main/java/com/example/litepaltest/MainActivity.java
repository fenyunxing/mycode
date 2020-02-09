package com.example.litepaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Book book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();//初始化控件
        book=new Book();//获取表对象,一个对象就是一条内容
    }

    private void initview() {
        Button btn1=(Button)findViewById(R.id.create_database_btn);
        Button btn2=(Button)findViewById(R.id.create_table_btn);
        Button btn3=(Button)findViewById(R.id.insert_btn);
        Button btn4=(Button)findViewById(R.id.delete_btn);
        Button btn5=(Button)findViewById(R.id.update_btn);
        Button btn6=(Button)findViewById(R.id.query_btn);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_database_btn:
                LitePal.getDatabase();//创建数据库
                Toast.makeText(MainActivity.this,"数据库创建成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.create_table_btn:
                break;
            case R.id.insert_btn:
                //添加内容
                book.setBookname("碧血剑");
                book.setAuthor("金庸");
                book.setPress("人民邮电出版社");
                book.setPrice(80.5);
                book.save();
                Toast.makeText(MainActivity.this,"插入一条数据",Toast.LENGTH_SHORT).show();
                break;
            case R.id.update_btn:
                book.setBookname("碧血剑");
                book.updateAll("price=?","48.5");
                break;
            case R.id.delete_btn:
                DataSupport.deleteAll(Book.class);//删除所有数据,可以指定条件
                break;
            case R.id.query_btn:
                List<Book> bookList=DataSupport.where("price=?","100.0").select("id").find(Book.class);
                for(Book bk:bookList){
                  int id=bk.getId();
                  Log.i("MainActivity","id="+id);
                }
                break;
            default:
                break;


        }
    }
}
