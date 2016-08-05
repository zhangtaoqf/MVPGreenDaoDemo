package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;

/**
 *
 * 1.给这个工程module添加生成dao,entity,sesstion,master类的依赖greendao-generator.jar包
 *          compile 'org.greenrobot:greendao-generator:3.1.0'（直接添加在当前module的gradle文件中的dependencies节点中）
 * 2.创建一个模式
 *      一个模式对应一个数据库
 *      一个数据库下对应多个表
 */
public class DaoGeneratorExample {
    public static void main(String[] args) throws Exception {
        /**
         * 参数一：数据库版本
         * 参数二：要存放的包
         */
        Schema stus = new Schema(1, "com.example.db");

        //添加一个表
        Entity student = stus.addEntity("Student");

        //给这个表添加响应的属性
        //id属性
        Property.PropertyBuilder propertyBuilder = student.addIdProperty();
        Property.PropertyBuilder autoincrement = propertyBuilder.autoincrement();

        //name属性
        student.addStringProperty("name");

        //身高,身高不为null
        Property.PropertyBuilder height = student.addFloatProperty("height");
        height.notNull();

        //自动生成
        //注意：目录一定是 '/' 不是  '\'
        new DaoGenerator().generateAll(stus,"E:/demo_shangke/GreenDaoDemo3/app/src/main/java");
    }
}
