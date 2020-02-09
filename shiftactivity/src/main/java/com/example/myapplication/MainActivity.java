package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageButton imageButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButton1 = (ImageButton) findViewById(R.id.imagebutton1);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到另外一个activity
                /*Intent intent1 = new Intent(MainActivity.this,TwoActivity.class);
                startActivity(intent1);*/
                //打开外部网页
                Intent intent1 = new Intent(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent1);
               /* Intent intent1 = new Intent(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse("tel:10086"));
                startActivity(intent1);*/
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //给menu布局文件创建菜单，第一个参数是需要创建的菜单文件，
        // 第二个参数是在哪个菜单背景下创建，menu是当前菜单
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(MainActivity.this,"上一页",Toast.LENGTH_SHORT).show();
                break;
            case R.id.item2: Toast.makeText(MainActivity.this,"下一页",Toast.LENGTH_SHORT).show();
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
}
