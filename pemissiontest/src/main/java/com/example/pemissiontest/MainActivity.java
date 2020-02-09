package com.example.pemissiontest;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> contactlist = new ArrayList<>();//新建一个集合用来装联系人数据
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button callButton = (Button) findViewById(R.id.call_btn);
        Button accessContact = (Button) findViewById(R.id.access_contacts_btn);
        ListView contactlistview = (ListView) findViewById(R.id.contacts_lv);
        //设置列表适配器
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, contactlist);
        contactlistview.setAdapter(adapter);
        //设置拨打电话监听
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //校验权限
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    //没有权限则申请权限
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    //有权限则拨打电话
                    call();
                }
            }
        });
        //设置访问联系人监听
        accessContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accesscontacts();
            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                } else {
                    Toast.makeText(MainActivity.this, "权限没许可", Toast.LENGTH_SHORT).show();

                }
                break;

            default:
                break;
        }
    }

    //打电话函数
    public void call() {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //访问联系人函数
    private void accesscontacts() {
        Cursor cursor = null;
        try {   //获取全部联系人数据表
            // cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null,null);
            //获取指定联系人
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, android.provider.ContactsContract.Contacts.DISPLAY_NAME+"=?", new String[]{"包包"}, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {//遍历指针
                    //获取联系人名字
                    String contactname = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    //获取联系人电话
                    String contactnumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    //把联系人信息放入集合
                    contactlist.add(contactname + "\n" + contactnumber);
                }
                adapter.notifyDataSetChanged();//更新适配器列表
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
