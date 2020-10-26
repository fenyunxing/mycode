package com.example.eventbus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

public class Main2Activity extends AppCompatActivity {
Button scond_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initview();
    }

    private void initview() {
       scond_btn=findViewById(R.id.secondButton_btn);
       scond_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               EventBus.getDefault().post(new MessageEvent("Second btn clicked"));
               Intent intent =new Intent(Main2Activity.this,MainActivity.class);
               startActivity(intent);

           }
       });
    }
}
