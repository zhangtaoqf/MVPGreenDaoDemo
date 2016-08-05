package com.zt.view;

import com.example.db.Student;

import java.util.List;

/**
 * Created by Administrator on 2016/8/5.
 */
public interface MainView extends BaseView {
    void showProgress();
    void hideProgress();
    void clearListView();
    void showNewData(List<Student> students);
    void showEditDialog(Student student);
    void showMessage(String msg);
    List<Student> getDatas();
}
