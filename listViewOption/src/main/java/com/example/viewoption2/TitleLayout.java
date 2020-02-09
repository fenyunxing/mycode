package com.example.viewoption2;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class TitleLayout extends LinearLayout  {
    public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title_layout,this);
        ImageButton button1=(ImageButton) findViewById(R.id.back_button);
        ImageButton button2=(ImageButton) findViewById(R.id.edit_button);
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)getContext()).finish();//结束当前界面
            }
        });

        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
             // Toast.makeText((Activity)getContext(),"菜单",Toast.LENGTH_SHORT).show();
                //在本界面创建一个对话框对象
                AlertDialog.Builder dialog=new AlertDialog.Builder((Activity)getContext());
                dialog.setTitle("提示对话框");
                dialog.setMessage("您确定要执行当前操作吗");
                dialog.setCancelable(false);
                //设置确定按钮并设置监听
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText((Activity)getContext(),"已执行当前操作",Toast.LENGTH_SHORT).show();
                    }
                });
                //设置取消按钮并设置监听
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText((Activity)getContext(),"已取消当前操作",Toast.LENGTH_SHORT).show();
                    }
                });
                //展示对话框
                dialog.show();
            }
        });
    }
}
