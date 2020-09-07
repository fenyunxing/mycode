package com.example.contractlist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.List;

class MyAdapter extends BaseAdapter {
    List<Person> datalist=null;
    Context context;
    ViewHolder viewHolder;
    Pinyin4jUtils pinyin4j;
    public MyAdapter(List<Person> datalist, Context context) {
        this.datalist=datalist;
        this.context=context;
        Log.e("Myadpter测试","执行");
        Log.e("Myadpter测试", String.valueOf(datalist));
       // inflater = LayoutInflater.from(context);
         pinyin4j = new Pinyin4jUtils();
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return datalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // Person person= datalist.get(position);

      //  System.out.println(person);
        if(convertView==null){
            viewHolder =new ViewHolder();
            convertView=LayoutInflater.from(context).inflate(R.layout.listview_item_layout,null);
            viewHolder.word_tv =convertView.findViewById(R.id.word_item_tv);
            viewHolder.imageView=convertView.findViewById(R.id.list_item_imageview);
            viewHolder.name_tv=convertView.findViewById(R.id.list_item_textview);
            viewHolder.word_item_ll=convertView.findViewById(R.id.word_item_ll);
            convertView.setTag(viewHolder);
        }else {
         viewHolder= (ViewHolder) convertView.getTag();
        }
       // Log.e("getview测试",datalist.get(position).getWord());
        try {
            if(position!=0){
                String firstword1= pinyin4j.toPinYinUppercaseInitials(datalist.get(position).getName());
                String firstword2= pinyin4j.toPinYinUppercaseInitials(datalist.get(position-1).getName());
                if(firstword1.equals(firstword2)){
                    viewHolder.word_item_ll.setVisibility(View.INVISIBLE);
                } else {
                    viewHolder.word_item_ll.setVisibility(View.VISIBLE);
                    viewHolder.word_tv.setText(datalist.get(position).getWord());
                }
            }else {
                viewHolder.word_tv.setText(datalist.get(position).getWord());
            }

        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }

        viewHolder.imageView.setImageResource(datalist.get(position).getImage());
        viewHolder.name_tv.setText(datalist.get(position).getName());
        return convertView;
    }

    private class ViewHolder {
        TextView word_tv;
        ImageView imageView;
        TextView name_tv;
        LinearLayout word_item_ll;
    }
}
