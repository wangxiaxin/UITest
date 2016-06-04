package com.changingedu.dev.databindingdemo.model;

import android.databinding.Bindable;
import android.databinding.Observable;

/**
 * Created by wangxiaxin on 2016/6/2.
 *
 * Demo User
 */
public class User implements Observable{
    public String firstName;
    public String lastName;
    public int gender;
    @Bindable
    public int age;

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {

    }
}
