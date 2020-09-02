package com.example.viewoption2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {

    private ListView lv;
    private int[] image = {R.drawable.p1, R.drawable.p2, R.drawable.p3,
            R.drawable.p4, R.drawable.p5, R.drawable.p6,
            R.drawable.p7, R.drawable.p8, R.drawable.p9};
    private String[] fruitname = {"苹果", "橘子", "香蕉",
            "菠萝", "西瓜", "芒果",
            "葡萄", "哈密瓜", "火龙果"};

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listview);
        /*定义一个动态数组*/
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        /*在数组中存放数据*/
        for (int i = 0; i < 9; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", image[i]);//加入图片
            map.put("ItemTitle", "第" + (i + 1) + "行");
            map.put("ItemText", fruitname[i]);
            listItem.add(map);
        }

        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this, listItem,//需要绑定的数据
                R.layout.list_item_layout,//每一行的布局
                //动态数组中的数据源的键对应到定义布局的View中
                new String[]{"ItemTitle", "ItemImage", "ItemText"},
                new int[]{R.id.ItemTitle, R.id.ItemImage, R.id.ItemText}
        );

        lv.setAdapter(mSimpleAdapter);//为ListView绑定适配器

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // setTitle("你点击了第"+arg2+"行");//设置标题栏显示点击的行
            }
        });
    }
}