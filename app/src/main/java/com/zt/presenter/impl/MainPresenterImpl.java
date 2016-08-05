package com.zt.presenter.impl;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.db.Student;
import com.zt.model.MainModel;
import com.zt.model.impl.Listener;
import com.zt.model.impl.MainModelImpl;
import com.zt.presenter.MainPresenter;
import com.zt.view.MainView;

/**
 * Created by Administrator on 2016/8/5.
 */
public class MainPresenterImpl implements MainPresenter {
    private int curPosition;
    private MainView mainView;
    private Listener listener;
    private MainModel mainModel;
    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        //初始化Model层
        mainModel = new MainModelImpl(this);
    }

    @Override
    public void loadData() {
        mainView.showProgress();
        mainModel.loadData("http://www.baidu.com/index.html");
    }

    @Override
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public Listener getListener() {
        mainView.hideProgress();
        return listener;
    }

    @Override
    public void onOptionMenuCreate(Menu menu) {
        menu.add(0,0,0,"添加一个学生");
        menu.add(0,1,1,"查看所有的学生");
        menu.add(0,2,2,"删除所有的学生");
    }

    @Override
    public void optionMenuItemCick(MenuItem menuItem, Activity activity) {
        switch (menuItem.getItemId()) {
            case 0:
                //添加一个学生
                mainView.showEditDialog(null);
                break;
            case 1:
                //查看所有学生
                mainView.clearListView();
                mainView.showNewData(mainModel.selectAll());
                break;
            case 2:
                //删除所有的学生
                mainModel.deleteAll();
                mainView.clearListView();
                break;
        }
    }

    @Override
    public void saveStudent(Student student) {
        mainModel.sava(student);
        mainView.showMessage("操作成功");
        mainView.clearListView();
        mainView.showNewData(mainModel.selectAll());
    }

    @Override
    public void onContextMenuCreate(Menu menu, int position) {
        menu.add(0,0,0,"修改该条目学生");
        menu.add(0,1,2,"删除该条目学生");
        curPosition = position;
    }

    @Override
    public void contextMenuItemCick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 0:
                //添加一个学生
                mainView.showEditDialog(mainView.getDatas().get(curPosition));
                break;
            case 1:
                //删除该条目学生
                mainModel.delete(mainView.getDatas().get(curPosition));
                //查看所有学生
                mainView.clearListView();
                mainView.showNewData(mainModel.selectAll());
                break;
        }
    }

}
