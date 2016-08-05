package com.zt.model.impl;

import android.os.Handler;

import com.example.db.Student;
import com.example.db.StudentDao;
import com.zt.MyApp;
import com.zt.model.MainModel;
import com.zt.presenter.MainPresenter;

import java.util.List;

/**
 * Created by Administrator on 2016/8/5.
 */
public class MainModelImpl implements MainModel {
    private MainPresenter mainPresenter;
    private Handler handler = new Handler();
    public MainModelImpl(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    @Override
    public void loadData(String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    //加载数据库的数据
                    StudentDao studentDao = MyApp.getApp().getDaoSession().getStudentDao();
                    //查询数据
                    final List<Student> students = studentDao.queryBuilder().list();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mainPresenter.getListener().response(students);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void sava(Student student) {
        MyApp.getApp().getDaoSession().getStudentDao().save(student);
    }

    @Override
    public List<Student> selectAll() {
        return MyApp.getApp().getDaoSession().getStudentDao().queryBuilder().list();
    }

    @Override
    public void delete(Student student) {
        MyApp.getApp().getDaoSession().getStudentDao().delete(student);
    }

    @Override
    public void deleteAll() {
        StudentDao studentDao = MyApp.getApp().getDaoSession().getStudentDao();
        studentDao.getDatabase().beginTransaction();
        studentDao.deleteAll();
        studentDao.getDatabase().setTransactionSuccessful();
        studentDao.getDatabase().endTransaction();
    }
}
