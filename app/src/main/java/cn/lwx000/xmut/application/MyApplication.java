package cn.lwx000.xmut.application;

import android.app.Application;

import cn.bmob.v3.Bmob;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        todo 自己的id
        Bmob.initialize(this, "5e368f66a28f5e4af2d2b379945a438d");
    }
}
