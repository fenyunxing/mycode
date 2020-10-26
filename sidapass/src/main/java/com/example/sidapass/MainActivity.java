package com.example.sidapass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
TextView textView,textView2;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
    }

    private void initview() {
        textView=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
        textView.setOnClickListener(this);
        textView2.setOnClickListener(this);
        calendar = Calendar.getInstance();  //获取日期对象
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.textView:
              System.out.println("点击1");
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
                              int hour =calendar.get(Calendar.HOUR_OF_DAY);
                              int min =calendar.get(Calendar.MINUTE);
                              int sec =calendar.get(Calendar.SECOND);

                              textView.setText("-"+month+"-"+day+" "+hour+":"+min+":"+sec);
                          }
                      });

                  }
              }).start();
              break;
          case R.id.textView2:
              System.out.println("点击2");
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
                              textView2.setText(""+month+"-"+day);
                          }
                      });
                  }
              }).start();
              break;
      }
    }
}
