package com.example.recyclerviewoption;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit> mfruitlist;
    public FruitAdapter(List<Fruit> fruitList) {
        mfruitlist = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //获取整个item布局，并生成一个view来装它
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);//将item布局的view传给ViewHolder处理
        //设置整个item控件点击监听
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"item点击",Toast.LENGTH_SHORT).show();
            }
        });
        //设置item控件的子控件图片控件的监听
        holder.fruitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"图片点击",Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView fruitname;
        View iv;

        public ViewHolder( View itemView) {
            super(itemView);
            iv=itemView;
            //通过传进来的item布局view来获取子view
            fruitImage = (ImageView) itemView.findViewById(R.id.fruit_image);
            fruitname = (TextView) itemView.findViewById(R.id.fruit_name);
        }
    }

    @Override//设置item内容
    public void onBindViewHolder(FruitAdapter.ViewHolder holder, int position) {
        Fruit fruit = mfruitlist.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitname.setText(fruit.getName());

    }

    @Override
    public int getItemCount() {
        return mfruitlist.size();
    }




}
