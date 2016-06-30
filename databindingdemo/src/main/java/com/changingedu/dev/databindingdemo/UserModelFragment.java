package com.changingedu.dev.databindingdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.changingedu.dev.databindingdemo.databinding.FragmentUserModelBinding;
import com.changingedu.dev.databindingdemo.model.User;
import com.changingedu.dev.databindingdemo.model.User2;

/**
 * A placeholder fragment containing a simple view.
 */
public class UserModelFragment extends Fragment {
    
    private static final String TAG = "UserModelFragment";
    
    FragmentUserModelBinding binding;
    User user;
    User2 user2;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_model,
                container, false);
        
        user = new User();
        user.age = 23;
        user.firstName = "default first name";
        user.lastName = "default last name";
        user.gender = 1;
        binding.setUser(user);

        user2 = new User2();
        binding.setUser2(user2);

        return binding.getRoot();
    }
    
    @Override
    public void onStart() {
        super.onStart();
        
        binding.getRoot().postDelayed(new Runnable() {
            @Override
            public void run() {
                user.setAge(50);
                user2.setAge(60);
            }
        }, 2000);
        
    }
}
