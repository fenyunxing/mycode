package com.example.observermodetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Observable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int val=0;
    Data mData = new Data();
   //实例化观察者类对象，重写类的方法
    private DataWatcher watcher = new DataWatcher() {

        @Override
        public void update(Observable observable, Object data) {
            super.update(observable, data);
            //观察者接受到被观察者的通知，来更新自己的数据操作。
            Data mData = (Data)data;
            Log.i("Test", "mData---->>"+mData.getDataChange());
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();




    }

    private void initview() {
        Button change_data_btn=(Button)findViewById(R.id.change_data_btn);
        change_data_btn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //观察者往被观察者中添加订阅事件。
        DataChange.getInstance().addObserver(watcher);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //观察者从被观察者队列中移除
        DataChange.getInstance().deleteObserver(watcher);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_data_btn:
                //模拟被观察者数据改变，更新数据。
                mData.setDataChange(val++);
                DataChange.getInstance().notifyDataChange(mData);
                break;
        }
    }
}
