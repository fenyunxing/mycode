package com.example.slidebotton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
  ImageView slideBottn_iv;
  boolean clickFlag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slideBottn_iv=findViewById(R.id.slidebotton_iv);
        slideBottn_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickFlag){
                 slideBottn_iv.setImageResource(R.drawable.ic_slidebotton_close);
                 clickFlag=false;
                }else {
                    slideBottn_iv.setImageResource(R.drawable.ic_slidebotton_open);
                    clickFlag=true;
                }
            }
        });
    }
}
