package com.example.sevicetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {
Button start_service_btn,stop_service_btn,bind_service_btn,unbind_service_btn;
ProgressBar mprogressBar;
    private boolean isbindFlag=false;
    private MyService myservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();

    }

    private void initview() {
        start_service_btn=findViewById(R.id.start_service_btn);
        stop_service_btn=findViewById(R.id.stop_service_btn);
        bind_service_btn=findViewById(R.id.bind_service_btn);
        unbind_service_btn=findViewById(R.id.unbind_service_btn);
        mprogressBar=findViewById(R.id.progressBar);
        start_service_btn.setOnClickListener(this);
        stop_service_btn.setOnClickListener(this);
        bind_service_btn.setOnClickListener(this);
        unbind_service_btn.setOnClickListener(this);

    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    mprogressBar.setProgress((Integer) msg.obj);
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.start_service_btn:
               startService(new Intent(this,MyService.class));
               break;
           case R.id.stop_service_btn:
               stopService(new Intent(this,MyService.class));
               break;
           case R.id.bind_service_btn:
               isbindFlag=true;
               getApplicationContext().bindService(new Intent(this,MyService.class),this, Service.BIND_AUTO_CREATE);
               break;
           case R.id.unbind_service_btn:
               if(isbindFlag){
                   getApplicationContext().unbindService(this);
                   isbindFlag=false;
               }

               break;
       }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        System.out.println("连接");
        MyService.MyBinder binder =(MyService.MyBinder)service;
        myservice=binder.getService();
        myservice.setMdataChangeListener(new MyService.DataChangeListener() {
            @Override
            public void setData(int obj) {
                System.out.println("数据改变回调"+obj);
                Message message=handler.obtainMessage();
                message.what=1;
                message.obj=obj;
                handler.sendMessage(message);
            }
        });
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        System.out.println("不链接");
    }
}
