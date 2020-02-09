package com.example.broadcastreceiverttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    IntentFilter intentFilter;
    NetworkChangeReceiver networkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /************以下四步动态注册广播并设置接受消息*******************/
        intentFilter = new IntentFilter();//创建一个广播滤波对象
        //添加要监听的广播消息
        intentFilter.addAction("network_change");
        networkChangeReceiver = new NetworkChangeReceiver();//创建一个广播接受对象
        registerReceiver(networkChangeReceiver, intentFilter);//注册广播消息和接受对象
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("network_change");
                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);//取消注册释放资源
    }

    /********************新建一个内部类继承广播接受类************************/
    public class NetworkChangeReceiver extends BroadcastReceiver {
        @Override//收到注册过的广播消息后会进入该方法执行
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"NetworkChangeReceiver收到消息",Toast.LENGTH_SHORT).show();
           /*
            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //获取所有网络连接的信息
            Network[] networks = connMgr.getAllNetworks();
            //用于存放网络连接信息
            StringBuilder sb = new StringBuilder();
            //通过循环将网络信息逐个取出来
            for (int i = 0; i < networks.length; i++) {
                //获取ConnectivityManager对象对应的NetworkInfo对象
                NetworkInfo networkInfo = connMgr.getNetworkInfo(networks[i]);
                sb.append(networkInfo.getTypeName() + " connect is " + networkInfo.isConnected()); */
            }
           // Toast.makeText(context, sb.toString(), Toast.LENGTH_SHORT).show();
        }
    }

