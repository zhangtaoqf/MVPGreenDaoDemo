package com.zt.presenter;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.db.Student;
import com.zt.model.impl.Listener;

/**
 * Created by Administrator on 2016/8/5.
 */
public interface MainPresenter extends BasePresenter {
    void loadData();
    void setListener(Listener listener);
    Listener getListener();
    //optionMenu的创建
    void onOptionMenuCreate(Menu menu);
    //item的点击事件
    void optionMenuItemCick(MenuItem menuItem, Activity activity);

    void saveStudent(Student student);

    //optionMenu的创建
    void onContextMenuCreate(Menu menu,int position);
    //item的点击事件
    void contextMenuItemCick(MenuItem menuItem);


}
