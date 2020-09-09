package com.example.sevicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.FileDescriptor;

public class MyService extends Service {
   // MyService myService;
    DataChangeListener mdataChangeListener;
  //  private int i=0;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       // throw new UnsupportedOperationException("Not yet implemented");
        System.out.println("bind方法调用");
        return new MyBinder();
    }
  //初次创建时调用
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("服务被创建");
       // myService=new MyService();
    }
 ///执行服务任务
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("任务执行中");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    if(mdataChangeListener!=null){
                        mdataChangeListener.setData(i);
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stopSelf(); //停止服务
            }
        }).start();
        //调用接口设置数据

        return super.onStartCommand(intent, flags, startId);
    }
  //解除服务和解绑时调用
    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("结束服务");
    }

    public class MyBinder extends Binder {

        public MyService getService(){
            return MyService.this;
        }


    }

   public interface DataChangeListener{
        void setData(int obj);
   }

    public void setMdataChangeListener(DataChangeListener mdataChangeListener) {
        this.mdataChangeListener = mdataChangeListener;
    }
}
