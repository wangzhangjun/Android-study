package com.example.myapplication;
import androidx.lifecycle.MutableLiveData;
import java.util.HashMap;
import java.util.Map;
public class LiveDataBus {
    //存放订阅者
    private Map<String, MutableLiveData<Object>> bus;
    private static LiveDataBus liveDataBus = new LiveDataBus();
    private LiveDataBus() {
        bus = new HashMap<>();
    }
    public static LiveDataBus getInstance() {
        return liveDataBus;
    }
    //注册订阅者（存入map）
    //第二个参数是为了写代码的时候，提示好一些，能够自动推断出来类型
    public synchronized <T> MutableLiveData<T> with(String key,Class<T> type,boolean sticky){
        if(!bus.containsKey(key)){
            if(sticky){
                bus.put(key, new MutableLiveData<Object>());
            }else {
                bus.put(key, new NonStickyMutableLiveData<Object>());
            }
        }
        return (MutableLiveData<T>) bus.get(key);
    }
}
