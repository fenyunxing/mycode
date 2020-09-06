package com.example.contractlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity<mhandler> extends AppCompatActivity  {

RightNavigationBar rightNavigationBar;
TextView word_tv;
    String[] indexword = {
            "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X",
            "Y", "Z"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }



    private void initView() {
        rightNavigationBar= findViewById(R.id.right_navigation);
        word_tv=findViewById(R.id.word_tv);
        word_tv.setTextSize(30);
        rightNavigationBar.setListener(new RightNavigationBar.MyClickListener() {
            @Override
            public void setDiaplayData(final int position, final String data) {
                Log.e("接口回调",data);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("发送string数据",String.valueOf(position)+"  "+ data);
                        Message message = mhandler.obtainMessage();
                        message.what=1;
                        message.obj=data;
                        mhandler.sendMessage(message);

                    }
                }).start();
            }
        });



        }

    Handler mhandler =new Handler(){
       @Override
       public void handleMessage(@NonNull Message msg) {
           super.handleMessage(msg);
           switch (msg.what){
               case 1:
                   Log.e("接受string消息", (String) msg.obj);
                   word_tv.setVisibility(View.VISIBLE); //可见
                   word_tv.setText((CharSequence) msg.obj);
                   word_tv.postDelayed(new Runnable() {
                       @Override
                       public void run() {
                           word_tv.setVisibility(View.INVISIBLE);  //不可见
                      }
                   },1500);
                   break;

           }
       }
   };


}
