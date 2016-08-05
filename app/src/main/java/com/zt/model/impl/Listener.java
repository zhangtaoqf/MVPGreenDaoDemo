package com.zt.model.impl;

import com.example.db.Student;

import java.util.List;

/**
 * Created by Administrator on 2016/8/5.
 */
public interface Listener
{
    void response(List<Student> datas);
}
