package com.example.observermodetest;
import java.util.Observable;
import java.util.Observer;

/**
 * TODO<请描述这个类是干什么的>
 *
 * @author xjp
 * @data: 2014年12月26日 上午9:29:56
 * @version: V1.0
 */
public abstract class DataWatcher implements Observer {
    @Override
    public void update(Observable observable, Object data) {
    }

}
