package com.changingedu.dev.databindingdemo;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.changingedu.dev.databindingdemo.databinding.FragmentUserModelBinding;
import com.changingedu.dev.databindingdemo.model.User;

import java.util.logging.Logger;

/**
 * A placeholder fragment containing a simple view.
 */
public class UserModelFragment extends Fragment {

    private static final String TAG = "UserModelFragment";


    FragmentUserModelBinding binding;
    User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_user_model, container, false);

        user = new User();
        user.age = 23;
        user.firstName = "first name";
        user.lastName = "last name";
        user.gender = 1;
        binding.setUser(user);

        binding.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                Log.d(TAG, "onPropertyChanged--" + observable + "--" + i);
            }
        });

        return binding.getRoot();
    }


    @Override
    public void onStart() {
        super.onStart();


        binding.getRoot().postDelayed(new Runnable() {
            @Override
            public void run() {
                user.age = 50;
                binding.notifyChange();
            }
        }, 2000);

    }
}
