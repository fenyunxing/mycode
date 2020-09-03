package com.example.downpopwindows;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    RecyclerView listView;
    MyAdapter myAdapter;
    PopupWindow popupWindow;
    EditText et_input;
    ImageView downarrow_iv;
    LinearLayout input_ll;
    List<Item> datalist=new ArrayList<>();
    String [] title={"第一行","第2行","第3行","第4行","第5行","第6行"};
    int [] data={R.drawable.ic_delete,R.drawable.ic_delete,R.drawable.ic_delete,
            R.drawable.ic_delete,R.drawable.ic_delete,R.drawable.ic_delete};
    int mwidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_input = findViewById(R.id.input_et);
        input_ll=(LinearLayout)findViewById(R.id.input_ll);
        downarrow_iv=findViewById(R.id.downarrow_iv);
        //下拉控件点击事件
        downarrow_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.setWidth(et_input.getWidth());
                popupWindow.update();
                if(!popupWindow.isShowing()&&popupWindow!=null){
                    popupWindow.showAsDropDown(et_input,0,0);
                   // Log.i("控件宽度测试",String.valueOf(et_input.getWidth()));
                }else{
                    popupWindow.dismiss();
                }
            }
        });
        //加载弹窗布局
        LayoutInflater lay = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = lay.inflate(R.layout.popupwindow_layout, null);
       // View v2=lay.inflate(R.layout.listview_item_layout,null);
        //从布局中获取recyclerview 并初始化
        listView=(RecyclerView) v.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        //当要设置成水平列表要加上这句代码，默认是竖直方向
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(linearLayoutManager);

        //加载要显示的数据
       for(int i=0;i<title.length;i++){
           datalist.add(new Item(title[i],data[i]));
       }
       //recyclerview控件设置适配器
       myAdapter=new MyAdapter(datalist);
       listView.setAdapter(myAdapter);

        //创建popupwindow对象并初始化
        popupWindow = new PopupWindow(v);
        //Log.i("控件宽度测试",String.valueOf(width));
        //popupWindow.setWidth(386);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.whitebackground));
        // 设置点击外部区域, 自动隐藏
        popupWindow.setOutsideTouchable(true); // 外部可触摸
        popupWindow.setFocusable(true); //设置可获取焦点
        // 显示在指定控件下
        popupWindow.update();

    }



    //创建一个自定义适配器给recyclerview 适配数据
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<Item> mItemList;
        int mposition;
        public MyAdapter(List<Item> itemList) {
            mItemList = itemList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //获取整个item布局，并生成一个view来装它
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item_layout, parent, false);
            final ViewHolder holder = new ViewHolder(view);//将item布局的view传给ViewHolder处理
            return holder;
        }


        @Override//设置item内容
        public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {
         //   mposition=position;
             final Item item =mItemList.get(position);
            holder.itemImageView.setImageResource(item.getImageId());
            holder.itemTextView.setText(item.getName());
            //给item子控件设置监听
            holder.itemImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),"删除",Toast.LENGTH_SHORT).show();

                    mItemList.remove(position);
                    myAdapter.notifyDataSetChanged(); //刷新数据适配器
                }
            });

            //设置整个item控件点击监听
            holder.iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    et_input.setText(item.getName());
                    popupWindow.dismiss();
                }
            });

        }

        @Override
        public int getItemCount() {
            return mItemList.size();
        }

      //创建一个viewHolder 内部类用来 装item 并进行子控件绑定
      class ViewHolder extends RecyclerView.ViewHolder {
          ImageView itemImageView;
          TextView itemTextView;
          View iv;

          public ViewHolder( View itemView) {
              super(itemView);
              iv=itemView;
              //通过传进来的item布局view来获取子view
              itemImageView = (ImageView) itemView.findViewById(R.id.list_item_iv);
              itemTextView = (TextView) itemView.findViewById(R.id.list_item_tv);
          }
      }


    }





}
