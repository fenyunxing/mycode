package com.example.gridviewoption;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) this.findViewById(R.id.gridView);

        List<Map<String, Object>> item = getData();//新建一个装要显示数据的集合
        // SimpleAdapter对象，匹配ArrayList中的元素
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, item, R.layout.gridview_item, new String[] { "itemImage", "itemName" }, new int[] { R.id.itemImage, R.id.itemName });
        gridView.setAdapter(simpleAdapter);

        // 添加点击事件
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
              //  int index = arg2 + 1;// id是从0开始的，所以需要+1
                GridView listuser = (GridView) findViewById(R.id.gridView);//获取控件
                //通过item位置获取所显示的map对象
                HashMap<String,Object> map=(HashMap<String,Object>)listuser.getItemAtPosition(arg2);
                String title= (String) map.get("itemName");//通过键值获取map对象的的内容
                Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();
            }
        });
    }
/******************************设置要显示对象数据初始化并获取*************************************/
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();

        int [] image= {R.drawable.p1, R.drawable.p2, R.drawable.p3,
                R.drawable.p4, R.drawable.p5, R.drawable.p6,
                R.drawable.p7, R.drawable.p8, R.drawable.p9};
       String [] fruitname= {"苹果", "橘子", "香蕉",
                "菠萝", "西瓜", "芒果",
                "葡萄", "哈密瓜", "火龙果"};
        for (int i = 0; i < image.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("itemImage", image[i]);
            item.put("itemName", fruitname[i]);
            items.add(item);
        }
        return items;

    }

}
