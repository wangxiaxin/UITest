package com.changingedu.dev.databindingdemo.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.Observable;

import com.changingedu.dev.databindingdemo.BR;

/**
 * Created by wangxiaxin on 2016/6/2.
 *
 * Demo User
 */
public class User extends BaseObservable {
    public String firstName;
    @Bindable
    public String lastName;
    public int gender;
    @Bindable
    public int age;

    public void setAge(int age){
        this.age = age;
        notifyPropertyChanged(BR.age);
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }

    public String getLastName(){
        return lastName;
    }
}
