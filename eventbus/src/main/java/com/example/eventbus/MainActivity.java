package com.example.eventbus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
TextView mTextView;
Button mfrist_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        EventBus.getDefault().register(this);
    }

    private void initview() {
        mTextView=findViewById(R.id.firstText_tv);
        mfrist_btn=findViewById(R.id.firstButton_btn);
        mfrist_btn.setOnClickListener(this);

    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MessageEvent event){
        System.out.println(event.getMsg());
         mTextView.setText(event.getMsg());
       // Toast.makeText(MainActivity.this,event.getMsg(),Toast.LENGTH_SHORT);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.firstButton_btn:
                //dosomething
                EventBus.getDefault().post(new MessageEvent("Second btn clicked"));
                //Intent intent= new Intent(MainActivity.this,Main2Activity.class);
               // startActivity(intent);
                break;
        }
    }
}
