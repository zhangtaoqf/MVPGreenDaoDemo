package com.zt;

import android.app.Application;

import com.example.db.DaoMaster;
import com.example.db.DaoSession;

/**
 * Created by Administrator on 2016/8/5.
 */
public class MyApp extends Application {
    private static MyApp app;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        this.app = this;
        //初始化数据库
        initDB();
    }

    private void initDB() {
        //初始化数据库的操作对象
        daoSession = DaoMaster.newDevSession(this,"stus.db");
    }

    public static MyApp getApp() {
        return app;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
