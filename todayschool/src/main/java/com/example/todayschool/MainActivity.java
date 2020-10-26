package com.example.todayschool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
TextView mDay_tv,mTime_tv,mDowncount_tv;
int downcount=60;
Timer timer = new Timer();
Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
    }

    private void initview() {
       mDay_tv=findViewById(R.id.day_tv);
       mTime_tv=findViewById(R.id.time_tv);
       mDowncount_tv=findViewById(R.id.downcount_tv);
       mDay_tv.setOnClickListener(this);
       mTime_tv.setOnClickListener(this);
        calendar = Calendar.getInstance();  //获取日期对象
        timer.schedule(task, 1000, 1000); //设置倒计时

    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {      // UI thread
                @Override
                public void run() {
                    downcount--;
                    mDowncount_tv.setText(""+downcount);//将int转为string
                    if(downcount < 1){
                        //timer.cancel();
                       downcount=60;
                    }
                }
            });
        }
    };


    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.day_tv:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //月
                                int month = calendar.get(Calendar.MONTH)+1;
                                //日
                                int day = calendar.get(Calendar.DAY_OF_MONTH);
                                Log.i("点击测试",month+"月"+day+"日")  ;
                                mDay_tv.setText(month+"月"+day+"日");
                            }
                        });
                    }
                }).start();
               break;
           case R.id.time_tv:
               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               //小时
                               int hour = calendar.get(Calendar.HOUR_OF_DAY);
                               //分钟
                               int minute = calendar.get(Calendar.MINUTE);
                               Log.i("点击测试",hour+":"+minute);
                               mTime_tv.setText(hour+":"+minute);
                           }
                       });
                   }
               }).start();

               break;
       }
    }
}
