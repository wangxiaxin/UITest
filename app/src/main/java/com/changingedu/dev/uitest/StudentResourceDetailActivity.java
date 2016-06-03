package com.changingedu.dev.uitest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.qingqing.base.view.timeline.ItemTimeLine;

/**
 * Created by Wangxiaxin on 2016/3/8.
 *
 * 生源详情
 */
public class StudentResourceDetailActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_resource_detail);


        ItemTimeLine item  = (ItemTimeLine) findViewById(R.id.item_enroll_result);
        item.setSubTitle("我了个去");
    }
}
