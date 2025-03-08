package com.example.administrator.android_fan;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fan.frame.greendao.StudentDaoOpe;
import com.fan.frame.greendao.bean.Student;
import com.fan.frame.utils.Constants;
import com.fan.frame.utils.FLogger;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenDaoActivity extends Activity {

    private String TAG = Constants.TAG;

    @BindView(R.id.add)
    Button add;
    @BindView(R.id.delete)
    Button delete;
    @BindView(R.id.update)
    Button update;
    @BindView(R.id.query)
    Button query;
    @BindView(R.id.deleteAll)
    Button deleteAll;
    @BindView(R.id.querybyid)
    Button querybyid;
    @BindView(R.id.deletebyid)
    Button deletebyid;

    @BindView(R.id.resu_id)
    EditText resu_id;

    private Context mContext;
    private Student student;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.greendao_activity);
        ButterKnife.bind(this);
        mContext = this;
        initData();
    }
    private List<Student> studentList = new ArrayList<>();
    private void initData() {
        for (int i = 0; i < 10; i++) {
            student = new Student((long) i, "而我却" + i, +i);
            studentList.add(student);
        }
        FLogger.d(TAG,"数据准备完毕");

    }

    @OnClick(R.id.add)
    public void add(View view){
        StudentDaoOpe.insertData(mContext, studentList);
    }
    @OnClick(R.id.delete)
    public void delete(View view){
        Student student = new Student((long) 5, "请问" + 5, 5);
        /**
         * 根据特定的对象删除
         */
        StudentDaoOpe.deleteData(mContext, student);

    }



    @OnClick(R.id.update)
    public void update(View view){
        Student student = new Student((long) 2, "爱的", 16516);
        StudentDaoOpe.updateData(mContext, student);
    }
    @OnClick(R.id.query)
    public void query(View view){
        List<Student> students = StudentDaoOpe.queryAll(mContext);
        for (int i = 0; i < students.size(); i++) {
            FLogger.i(TAG,students.get(i).toString());
        }
    }

    @OnClick(R.id.querybyid)
    public void queryById(View view){

        List<Student> students = StudentDaoOpe.queryForId(mContext, Integer.parseInt(resu_id.getText().toString()));
        for (int i = 0; i < students.size(); i++) {
            FLogger.i(TAG,students.get(i).toString());
        }
    }

    @OnClick(R.id.deletebyid)
    public void deleteById(View view){
        /**
         * 根据主键删除
         */
        StudentDaoOpe.deleteByKeyData(mContext, Integer.parseInt(resu_id.getText().toString()));
    }

    @OnClick(R.id.deleteAll)
    public void deleteAll(View view){
        StudentDaoOpe.deleteAllData(mContext);
    }
    @OnClick(R.id.insertbyid)
    public void insertById(View view){
        int i = Integer.parseInt(resu_id.getText().toString());

        Student student = new Student((long)i,"恶趣味"+i,i);
        StudentDaoOpe.insertData(mContext,student);
    }





}
