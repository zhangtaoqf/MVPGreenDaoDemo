package com.zt.view.impl;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.db.Student;
import com.zt.R;
import com.zt.model.impl.Listener;
import com.zt.presenter.MainPresenter;
import com.zt.presenter.impl.MainPresenterImpl;
import com.zt.view.MainView;
import com.zt.view.adapter.StudentAdapter;

import java.util.List;

/**
 *
 *
 * GreenDao的使用步骤
 * 第一步：生成DAO和实体类和Setion操作类和Master管理类
 *      1.创建一个java的library来生成这些文件(详细生成信息在这个Module中已经给出)
 * 第二步：
 *      2.生成好了目录就在我们当前操作的这个module中,发现出错了。直接给当前的module添加greendao.jar包依赖
 *       compile 'org.greenrobot:greendao:3.1.0'   (添加在app module中的gradle的dependencies的节点下同步就OK)
 * 第三步：
 *      3.就可以直接使用数据库了
 *
 *
 *  本案例是使用 MVP + GREENDAO 来写的
 */
public class MainActivity extends AppCompatActivity implements MainView, Listener {

    private ListView listView;
    private ProgressBar progressBar;
    private MainPresenter mainPresenter;
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = ((ListView) findViewById(R.id.listViewId));
        progressBar = ((ProgressBar) findViewById(R.id.progressBarId));
        adapter = new StudentAdapter(this);
        listView.setAdapter(adapter);
        //注册上下文菜单
        registerForContextMenu(listView);
        //初始化Presenter层
        mainPresenter = new MainPresenterImpl(this);
        mainPresenter.setListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mainPresenter.onOptionMenuCreate(menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo menuInfo1 = (AdapterView.AdapterContextMenuInfo) menuInfo;
        mainPresenter.onContextMenuCreate(menu,menuInfo1.position);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mainPresenter.optionMenuItemCick(item,this);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //加载数据
        mainPresenter.loadData();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        listView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void clearListView() {
        adapter.clear();
    }

    @Override
    public void showNewData(List<Student> students) {
        adapter.addAll(students);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        mainPresenter.contextMenuItemCick(item);
        return super.onContextItemSelected(item);
    }

    @Override
    public void showEditDialog(final Student stud) {
        View dialog_view = getLayoutInflater().inflate(R.layout.dialog_edit, null);
        final EditText nameEt = (EditText) dialog_view.findViewById(R.id.activity_main_nameEtId);
        final EditText heightEt = (EditText) dialog_view.findViewById(R.id.activity_main_heightEtId);
        if(stud != null)
        {
            nameEt.setText(stud.getName());
            heightEt.setText(stud.getHeight()+"");
        }

        new AlertDialog.Builder(this)
                .setTitle("编辑学生的信息")
                .setView(dialog_view)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showMessage("已经取消添加");
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = nameEt.getText().toString().trim();
                        float height = Float.parseFloat(heightEt.getText().toString().trim());
                        if(stud!=null)
                        {
                            stud.setName(name);
                            stud.setHeight(height);
                            mainPresenter.saveStudent(stud);
                        }else
                        {
                            Student student = new Student();
                            student.setName(name);
                            student.setHeight(height);
                        }

                    }
                }).create().show();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(MainActivity.this, ""+msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public List<Student> getDatas() {
        return adapter.getDatas();
    }


    @Override
    public void response(List<Student> datas) {
        //加载listView的数据
        adapter.addAll(datas);
    }


}
