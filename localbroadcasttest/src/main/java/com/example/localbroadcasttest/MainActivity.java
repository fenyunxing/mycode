package com.example.localbroadcasttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private LocalBroadCastReceiver localBroadCastReceiver;
    Button button1;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取本地广播管理对象用来发送本地广播
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        //动态注册广播接收对象和监听消息
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("Local_Broadcast");
        localBroadCastReceiver = new LocalBroadCastReceiver();
        localBroadcastManager.registerReceiver(localBroadCastReceiver, intentFilter);

        //设置按钮发送广播
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("Local_Broadcast");
                localBroadcastManager.sendBroadcast(intent);//发送本地广播
                //sendBroadcast(intent);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localBroadCastReceiver);
    }

    /**************创建一个本地广播接收类***********************/
    public class LocalBroadCastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "收到本地广播", Toast.LENGTH_SHORT).show();
        }
    }
}
