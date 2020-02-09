package com.example.sharedpreferencetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et_account;
    private EditText et_password;
    private ImageButton btn_login;
    private CheckBox checkBox1;
    private CheckBox checkBox2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取控件
        et_account = (EditText) findViewById(R.id.edittext_account);
        et_password = (EditText) findViewById(R.id.edittext_password);
        btn_login = (ImageButton) findViewById(R.id.loginbutton);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        //登录点击监听
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = et_account.getText().toString();
                String password = et_password.getText().toString();
                if (account.isEmpty() && password.isEmpty()) {//判断输入是否为空
                    Toast.makeText(MainActivity.this, "请输入完整信息", Toast.LENGTH_SHORT).show();
                } else if (checkBox1.isChecked()) {//判断是否记住密码
                    //是则保存数据
                    save("zhanghao", account);//保存账号
                    save("mima", password);//保存密码

                    if (account.equals("7758521") && password.equals("1234567")) {
                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "信息错误", Toast.LENGTH_SHORT).show();
                    }
                    //Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                } else if (!checkBox1.isChecked() && !checkBox2.isChecked()) {
                    if (account.equals("7758521") && password.equals("1234567")) {
                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "信息错误", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //勾选框监听
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                save("checkbox1", isChecked);//存checkbox1的布尔值
            }
        });

        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                save("checkbox2", isChecked);//存checkbox2的布尔值
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        readdata();//读取保存的用户信息并显示在编辑框
        String account = et_account.getText().toString();
        String password = et_password.getText().toString();
        if (account.equals("7758521") && password.equals("1234567") && checkBox2.isChecked() && checkBox1.isChecked()) {
            Toast.makeText(MainActivity.this, "自动登录成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!checkBox1.isChecked()) {
            remove(); //清楚保存的数据
        }
    }

    /*********************存数据函数**********************?*/
    private void save(String key, String data) {
        SharedPreferences sharedPreferences = getSharedPreferences("logininfo", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, data);
        editor.commit();
    }

    /*********************存数据函数**********************?*/
    private void save(String key, boolean checkbox_value) {
        SharedPreferences sharedPreferences = getSharedPreferences("logininfo", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, checkbox_value);
        editor.commit();
    }

    /***********************读数据函数***************************/
    private void readdata() {
        SharedPreferences sharedPreferences = getSharedPreferences("logininfo", 0);
        et_account.setText(sharedPreferences.getString("zhanghao", ""));
        et_password.setText(sharedPreferences.getString("mima", ""));
        checkBox1.setChecked(sharedPreferences.getBoolean("checkbox1", false));
        checkBox2.setChecked(sharedPreferences.getBoolean("checkbox2", false));
    }

    /**********清除数据***********/
    private void remove() {
        SharedPreferences sharedPreferences = getSharedPreferences("logininfo", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

}





