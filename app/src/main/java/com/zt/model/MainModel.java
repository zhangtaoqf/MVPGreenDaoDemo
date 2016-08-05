package com.zt.model;

import com.example.db.Student;

import java.util.List;

/**
 * Created by Administrator on 2016/8/5.
 */
public interface MainModel extends BaseModel {
    void loadData(String url);
    void sava(Student student);
    List<Student> selectAll();
    void delete(Student student);
    void deleteAll();
}
