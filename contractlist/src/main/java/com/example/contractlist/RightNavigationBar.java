package com.example.contractlist;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class RightNavigationBar extends View {
    String[] indexword = {
            "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X",
            "Y", "Z"};
    int mHight, mWidth, ycount=-1;

    String Textcontent;
    TextView word_tv;
    MyClickListener mListener;
    Paint paint1,paint2;

    public RightNavigationBar(Context context) {
        super(context);
        Log.e("构造方法1","构造方法");
    }

    public RightNavigationBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.e("构造方法2","构造方法");
        paint1 = new Paint();
        paint1.setColor(Color.RED);
        paint1.setTextSize(20);
        paint2 = new Paint();
        paint2.setColor(Color.GREEN);
        paint2.setTextSize(20);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHight = getMeasuredHeight(); //783
        mWidth = getMeasuredWidth();  //30
        Log.i("测量高", String.valueOf(mHight));
        Log.i("测量宽", String.valueOf(mWidth));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("触摸事件", "按下");
                int y1 = (int) event.getY();
                for (int i = 0; i < 26; i++) {
                    if (y1 > i * mHight / 26 && y1 < (i + 1) * mHight / 26) { //判断是否在第一格
                        Log.d("点击测试", indexword[i]);
                        ycount = i; //记录点击的是那个格子位置
                        mListener.setDiaplayData(ycount,indexword[ycount]);
                        invalidate(); //更新
                        break;
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("触摸事件", "移动");
                int y = (int) event.getY();
                for (int i = 0; i < 26; i++) {
                    if (y > i * mHight / 26 && y < (i + 1) * mHight / 26) { //判断是否在第一格
                        Log.d("点击测试", indexword[i]);
                        Textcontent = indexword[i];
                        //visiableFlag=true;
                        if (mListener != null) {
                            mListener.setDiaplayData(i,Textcontent);
                        }

                        break;
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                Log.i("触摸事件", "松开");
                ycount=-1;
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Paint paint=new Paint();

        float baseLineY = mHight / 26 / 2 + Math.abs(paint1.ascent() + paint1.descent()) / 2; //计算文本基准线
        //画26个格子
        for (int i = 0; i < indexword.length; i++) {
            //canvas.drawRect(0,i*mHight/26,mWidth,(i+1)*mHight/26,paint);
            if(ycount==i){
                float textWidth = paint2.measureText(indexword[ycount]); //计算文本内容宽度
                //参数x 是垂直基准线位置， 参数y 是文本水平基准线位置
                canvas.drawText(indexword[ycount], (mWidth - textWidth) / 2, baseLineY + ycount * mHight / 26, paint2);
            } else {
                float textWidth = paint1.measureText(indexword[i]); //计算文本内容宽度
                //参数x 是垂直基准线位置， 参数y 是文本水平基准线位置
                canvas.drawText(indexword[i], (mWidth - textWidth) / 2, baseLineY + i * mHight / 26, paint1);
            }

        }



    }

    void setListener(MyClickListener myClickListener) {
        this.mListener = myClickListener;
        Log.e("接口测试", "调用了");
    }

    public interface MyClickListener {
        void setDiaplayData(int position,String data);

    }

}
