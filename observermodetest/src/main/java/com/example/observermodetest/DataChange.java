package com.example.observermodetest;

import java.util.Observable;

/**
 * TODO<请描述这个类是干什么的>
 *
 * @author xjp
 * @data: 2014年12月26日 上午9:30:21
 * @version: V1.0
 */
public class DataChange extends Observable {

    private static DataChange instance = null;

    public static DataChange getInstance() {
        if (null == instance) {
            instance = new DataChange();
        }
        return instance;
    }

    public void notifyDataChange(Data data) {
        //被观察者怎么通知观察者数据有改变了呢？？这里的两个方法是关键。
        setChanged();
        notifyObservers(data);
    }

}

