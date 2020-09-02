package com.example.recyclerviewoption;

import androidx.appcompat.app.AppCompatActivity;
//系统默认的是这两个包不是support-v7包
import androidx.recyclerview.widget.LinearLayoutManager;
//在布局文件中该控件也是有这个包不是support-v7包
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Fruit> fruitList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initdata();//初始化水果对象数据，放入集合
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        //当要设置成水平列表要加上这句代码，默认是竖直方向
       linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
       recyclerView.setLayoutManager(linearLayoutManager);
        FruitAdapter adapter=new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

    }

    private void initdata(){
        int [] image= {R.drawable.p1, R.drawable.p2, R.drawable.p3,
                R.drawable.p4, R.drawable.p5, R.drawable.p6,
                R.drawable.p7, R.drawable.p8, R.drawable.p9};
        String [] fruitname= {"苹果", "橘子", "香蕉",
                "菠萝", "西瓜", "芒果",
                "葡萄", "哈密瓜", "火龙果"};
        Fruit pinguo=new Fruit(fruitname[0],image[0]);
        fruitList.add(pinguo);
        Fruit juzi=new Fruit(fruitname[1],image[1]);
        fruitList.add(juzi);
        Fruit xiangjiao=new Fruit(fruitname[2],image[2]);
        fruitList.add(xiangjiao);
        Fruit boluo=new Fruit(fruitname[3],image[3]);
        fruitList.add(boluo);
        Fruit xigua=new Fruit(fruitname[4],image[4]);
        fruitList.add(xigua);
        Fruit mangguo=new Fruit(fruitname[5],image[5]);
        fruitList.add(mangguo);
        Fruit putao=new Fruit(fruitname[6],image[6]);
        fruitList.add(putao);
        Fruit hamigua=new Fruit(fruitname[7],image[7]);
        fruitList.add(hamigua);
        Fruit huolongguo=new Fruit(fruitname[8],image[8]);
        fruitList.add(huolongguo);

    }
}
