package com.example.viewpagertest;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private View view1, view2, view3,view4;  //创建三个view用来装三个布局
    private ViewPager mViewPager;  //对应的viewPager
    private List<View> mViewList;//view数组
    private ImageView mMessage_iv, mUser_iv, mMore_iv,mFind_iv;
    private LinearLayout mlayout1,mlayout2,mlayout3,mlayout4;
     private int mPositon=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mMessage_iv = findViewById(R.id.message_iv);
        mUser_iv = findViewById(R.id.user_iv);
        mMore_iv = findViewById(R.id.more_iv);
        mFind_iv = findViewById(R.id.find_iv);
        mlayout1=findViewById(R.id.layout1);
        mlayout2=findViewById(R.id.layout2);
        mlayout3=findViewById(R.id.layout3);
        mlayout4=findViewById(R.id.layout4);
        mMessage_iv.setOnClickListener(this);
        mUser_iv.setOnClickListener(this);
        mFind_iv.setOnClickListener(this);
        mMore_iv.setOnClickListener(this);
        LayoutInflater inflater = getLayoutInflater(); //获取一个布局加载器
        //将布局解析成view
        view1 = inflater.inflate(R.layout.layout1, null);
        view2 = inflater.inflate(R.layout.layout2, null);
        view3 = inflater.inflate(R.layout.layout3, null);
        view4 = inflater.inflate(R.layout.layout4, null);
        //创建一个控件集合用来装view
        mViewList = new ArrayList<View>();
        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);
        mViewList.add(view4);

        //创建一个viewpager适配器

        mViewPager.setAdapter(pagerAdapter); //设置适配器


        //设置一个页面改变监听
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            private int position;
            private int oldPositon;
            private int msate;

            /***
             * pragrm:  positionoffset  1>>0 左>>右  0>>1 右>>左
             *
             * ***/
            @Override  //该方法可以判断滑动的比例
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                this.position = position;

            }

            @Override
            public void onPageSelected(int position) { //滑动快结束时调用
                super.onPageSelected(position);
                Message message1= handler.obtainMessage();
               if(view1==mViewList.get(position)){
                   Log.i("滑动标志","当前在第一页");
                   message1.what=1;
                   handler.sendMessage(message1);

               } else if(view2==mViewList.get(position)){
                  Log.i("滑动标志","当前在第2页");
                   message1.what=2;
                   handler.sendMessage(message1);

               } else if(view3==mViewList.get(position)){
                   Log.i("滑动标志","当前在第3页");
                   message1.what=3;
                   handler.sendMessage(message1);

               }else if(view4==mViewList.get(position)){
                   Log.i("滑动标志","当前在第3页");
                   message1.what=4;
                   handler.sendMessage(message1);

               }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                /** sate==1 手指按下
                 *  sate==2 手指松开
                 *  sate==0 空状态
                 * **/
                   this.msate=state;
            }
        });


    }
    PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mViewList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            // TODO Auto-generated method stub
            container.removeView(mViewList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // TODO Auto-generated method stub
            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }


    };


     Handler handler= new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Log.i("消息响应测试","更改消息图片");
                    mMessage_iv.setImageResource(R.drawable.ic_massage_select);
                    mUser_iv.setImageResource(R.drawable.ic_user);
                    mMore_iv.setImageResource(R.drawable.ic_more);
                    mFind_iv.setImageResource(R.drawable.ic_find);
                    break;
                case 2:
                    Log.i("消息响应测试","更改用户图片");
                    mMessage_iv.setImageResource(R.drawable.ic_massage);
                    mUser_iv.setImageResource(R.drawable.ic_user_select);
                    mMore_iv.setImageResource(R.drawable.ic_more);
                    mFind_iv.setImageResource(R.drawable.ic_find);
                    break;
                case 3:
                    Log.i("消息响应测试","更改更多图片");
                    mMessage_iv.setImageResource(R.drawable.ic_massage);
                    mUser_iv.setImageResource(R.drawable.ic_user);
                    mMore_iv.setImageResource(R.drawable.ic_more);
                    mFind_iv.setImageResource(R.drawable.ic_find_select);
                    break;
                case 4:
                    Log.i("消息响应测试","更改更多图片");
                    mMessage_iv.setImageResource(R.drawable.ic_massage);
                    mUser_iv.setImageResource(R.drawable.ic_user);
                    mMore_iv.setImageResource(R.drawable.ic_more_select);
                    mFind_iv.setImageResource(R.drawable.ic_find);
                    break;

            }
        }
    };

    @Override
    public void onClick(View v) {
        Message message2= handler.obtainMessage();
        switch (v.getId()){
            case R.id.message_iv:
                message2.what=1;
                handler.sendMessage(message2);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.user_iv:
                message2.what=2;
                handler.sendMessage(message2);
                mViewPager.setCurrentItem(1);

                break;
            case R.id.find_iv:
                message2.what=3;
                handler.sendMessage(message2);
                mViewPager.setCurrentItem(2);

                break;
            case R.id.more_iv:
                message2.what=4;
                handler.sendMessage(message2);  mViewPager.setCurrentItem(3);

                break;
        }
    }
}
