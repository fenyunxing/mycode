package com.example.viewoption;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public  class LinearLayoutActivity extends AppCompatActivity implements View.OnClickListener {
    ProgressBar progressBar;
    Button button,button1,button2,button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout);
        Initview();
    }
    /************************初始化控件函数******************************************/
    public  void Initview(){
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        button = (Button) findViewById(R.id.add_button);
        button1 = (Button) findViewById(R.id.sub_button);
        button2 = (Button) findViewById(R.id.dialog_button1);
        button3 = (Button) findViewById(R.id.dialog_button2);
        button.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_button:
                int progress = progressBar.getProgress();
                progress += 10;
                progressBar.setProgress(progress);
                break;
            case R.id.sub_button:
                int progress1 = progressBar.getProgress();
                progress1 -= 10;
                progressBar.setProgress(progress1);
                break;
            case R.id.dialog_button1:
                //在本界面创建一个对话框对象
                AlertDialog.Builder dialog=new AlertDialog.Builder(LinearLayoutActivity.this);
                dialog.setTitle("提示对话框");
                dialog.setMessage("您确定要执行当前操作吗");
                dialog.setCancelable(false);
                //设置确定按钮并设置监听
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(LinearLayoutActivity.this,"已执行当前操作",Toast.LENGTH_SHORT).show();
                    }
                });
                //设置取消按钮并设置监听
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(LinearLayoutActivity.this,"已取消当前操作",Toast.LENGTH_SHORT).show();
                    }
                });
                //展示对话框
                dialog.show();
            case R.id.dialog_button2:
                ProgressDialog progressDialog=new ProgressDialog(LinearLayoutActivity.this);
                progressDialog.setTitle("等待进程对话框");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();
            default:
                break;
        }
    }
}
