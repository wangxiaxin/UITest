package com.changingedu.dev.databindingdemo.model;

import com.changingedu.dev.databindingdemo.BR;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableInt;

/**
 * Created by wangxiaxin on 2016/6/2.
 *
 * Demo User
 */
public class User2 {
    public String firstName;
    public String lastName;
    public int gender;
    public ObservableInt age = new ObservableInt();;

    public void setAge(int age){
        this.age.set(age);
    }
}
