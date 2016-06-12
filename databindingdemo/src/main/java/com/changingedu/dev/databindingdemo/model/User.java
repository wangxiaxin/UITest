package com.changingedu.dev.databindingdemo.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;

import com.changingedu.dev.databindingdemo.BR;

/**
 * Created by wangxiaxin on 2016/6/2.
 *
 * Demo User
 */
public class User extends BaseObservable {
    public String firstName;
    public String lastName;
    public int gender;
    @Bindable
    public int age;

    public void setAge(int age){
        this.age = age;
        notifyPropertyChanged(BR.age);
    }
}
