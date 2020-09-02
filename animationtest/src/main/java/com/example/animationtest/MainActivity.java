package com.example.animationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button rotateButton = null;
    private Button scaleButton = null;
    private Button alphaButton = null;
    private Button translateButton = null;
    private ImageView image = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rotateButton = (Button)findViewById(R.id.rotateButton);

        scaleButton = (Button)findViewById(R.id.scaleButton);

        alphaButton = (Button)findViewById(R.id.alphaButton);

        translateButton = (Button)findViewById(R.id.translateButton);

        image = (ImageView)findViewById(R.id.image);


        rotateButton.setOnClickListener(new RotateButtonListener());
        scaleButton.setOnClickListener(new ScaleButtonListener());
        alphaButton.setOnClickListener(new AlphaButtonListener());
        translateButton.setOnClickListener(new TranslateButtonListener());

    }

    class AlphaButtonListener implements View.OnClickListener {

        public void onClick(View v) {

            //创建一个AnimationSet对象，参数为Boolean型，
            //true表示使用Animation的interpolator，false则是使用自己的
            AnimationSet animationSet = new AnimationSet(true);
            //创建一个AlphaAnimation对象，参数从完全的透明度，到完全的不透明
            AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
            //设置动画执行的时间
            alphaAnimation.setDuration(500);
            //将alphaAnimation对象添加到AnimationSet当中
            animationSet.addAnimation(alphaAnimation);
            //使用ImageView的startAnimation方法执行动画
            image.startAnimation(animationSet);

        }

    }

    class RotateButtonListener implements View.OnClickListener {

        public void onClick(View v) {

//            AnimationSet animationSet = new AnimationSet(true);
//            //参数1：从哪个旋转角度开始
//            //参数2：转到什么角度
//            //后4个参数用于设置围绕着旋转的圆的圆心在哪里
//            //参数3：确定x轴坐标的类型，有ABSOLUT绝对坐标、RELATIVE_TO_SELF相对于自身坐标、RELATIVE_TO_PARENT相对于父控件的坐标
//            //参数4：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴
//            //参数5：确定y轴坐标的类型
//            //参数6：y轴的值，0.5f表明是以自身这个控件的一半长度为x轴
//            RotateAnimation rotateAnimation = new RotateAnimation(0, 180,
//
//                    Animation.RELATIVE_TO_SELF,0.5f,
//
//                    Animation.RELATIVE_TO_SELF,0.5f);
//
//            rotateAnimation.setDuration(1000);
//            rotateAnimation.setFillEnabled(false); //关闭默认的before 属性
//            rotateAnimation.setFillAfter(true); //设置停留在动画结束时
//            animationSet.addAnimation(rotateAnimation);
//
//            image.startAnimation(animationSet);

            /***************属性动画******************/
            ObjectAnimator.ofFloat(image, "scaleX", 1f, 0f, 1f)
                    .setDuration(1000)
                    .start();
        }

    }

    class ScaleButtonListener implements View.OnClickListener {

        public void onClick(View v) {

            AnimationSet animationSet = new AnimationSet(true);
            //参数1：x轴的初始值
            //参数2：x轴收缩后的值
            //参数3：y轴的初始值
            //参数4：y轴收缩后的值
            //参数5：确定x轴坐标的类型
            //参数6：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴
            //参数7：确定y轴坐标的类型
            //参数8：y轴的值，0.5f表明是以自身这个控件的一半长度为x轴

            ScaleAnimation scaleAnimation = new ScaleAnimation(
                    0, 0.5f,0,0.5f,
                    Animation.RELATIVE_TO_SELF,0.5f,
                    Animation.RELATIVE_TO_SELF,0.5f);

            scaleAnimation.setDuration(1000);

            animationSet.addAnimation(scaleAnimation);

            image.startAnimation(animationSet);

        }

    }

    class TranslateButtonListener implements View.OnClickListener {

        public void onClick(View v) {

            AnimationSet animationSet = new AnimationSet(true);
            //参数1～2：x轴的开始位置
            //参数3～4：y轴的开始位
            //参数5～6：x轴的结束位置
            //参数7～8：x轴的结束位置
            TranslateAnimation translateAnimation = new TranslateAnimation(

                            Animation.RELATIVE_TO_SELF,0f,

                            Animation.RELATIVE_TO_SELF,0.5f,

                            Animation.RELATIVE_TO_SELF,0f,

                            Animation.RELATIVE_TO_SELF,0.5f);

            translateAnimation.setDuration(1000);

            animationSet.addAnimation(translateAnimation);

            image.startAnimation(animationSet);

        }
    }
}