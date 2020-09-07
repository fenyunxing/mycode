package com.example.contractlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.List;

public class MainActivity<mhandler> extends AppCompatActivity {

    RightNavigationBar rightNavigationBar;
    TextView word_tv;
    ListView listView;
    LinearLayout word_item_ll;
    List<Person> datalist = new ArrayList<Person>();
    String[] indexword = {
            "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X",
            "Y", "Z"};
    String[] name = {"安安", "安安", "安安", "贝贝", "贝贝", "成龙",
            "成龙", "杜飞", "杜飞","福贵","福贵","福贵",
            "关羽","关羽","关羽","黄帝","黄帝","黄帝","蒋介石","蒋介石","蒋介石",
            "凯子","凯子","凯子","李旦","李旦","李旦","马华腾","马华腾","马华腾",
            "牛耿", "牛耿", "牛耿","彭德华","彭德华","彭德华","钱八","钱八","钱八"
    };
    String first5,lastword=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }


    private void initView() {
        Pinyin4jUtils pinyin4j = new Pinyin4jUtils();

        for (int i = 0; i < name.length; i++) {
            try {
                first5 = pinyin4j.toPinYinUppercaseInitials(name[i]);
                //System.out.println(first5);
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                badHanyuPinyinOutputFormatCombination.printStackTrace();
            }
            datalist.add(new Person(first5, name[i], R.drawable.expression));
        }


        rightNavigationBar = findViewById(R.id.right_navigation);
        listView = findViewById(R.id.listView_lv);
        word_tv = findViewById(R.id.word_tv);
        word_tv.setTextSize(30);
        rightNavigationBar.setListener(new RightNavigationBar.MyClickListener() {
            @Override
            public void setMoveDiaplayData(final int position, final String data) {
                Log.e("移动接口回调", data);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("发送移动数据", String.valueOf(position) + "  " + data);
                        Message message = mhandler.obtainMessage();
                        message.what = 1;
                        message.obj = data;
                        mhandler.sendMessage(message);

                    }
                }).start();
            }

            @Override
            public void setClickDiaplayData(final int position, final String data) {
                Log.e("点击接口回调", data);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("发送点击数据", String.valueOf(position) + "  " + data);
                        Message message = mhandler.obtainMessage();
                        message.what = 2;
                        message.obj = data;
                        mhandler.sendMessage(message);

                    }
                }).start();
            }
        });
        listView.setAdapter(new MyAdapter(datalist, MainActivity.this));

    }

    Handler mhandler = new Handler() {
        @Override
        public void handleMessage(@NonNull final Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Log.e("接受string消息", (String) msg.obj);
                    word_tv.setVisibility(View.VISIBLE); //可见
                    word_tv.setText((CharSequence) msg.obj);
                    word_tv.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            word_tv.setVisibility(View.INVISIBLE);  //不可见
                        }
                    }, 1500);
                    break;
                case 2:
                    Log.e("点击回调接收", String.valueOf(msg.obj));
                    Pinyin4jUtils pinyin4jUtils=new Pinyin4jUtils();
                    for(int k=0;k<name.length;k++){
                        try {
                            String fastword=pinyin4jUtils.toPinYinUppercaseInitials(name[k]);
                            if(fastword.equals(msg.obj)){
                                listView.setSelection(k);
                                break;
                            }
                        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                            badHanyuPinyinOutputFormatCombination.printStackTrace();
                        }
                    }
                  //
                    break;

            }
        }
    };


}
