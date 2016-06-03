package com.changingedu.dev.databindingdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.changingedu.dev.databindingdemo.databinding.FragmentUserModelBinding;
import com.changingedu.dev.databindingdemo.model.User;

/**
 * A placeholder fragment containing a simple view.
 */
public class UserModelFragment extends Fragment {
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        
        FragmentUserModelBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_user_model, container, false);

        User user = new User();
        user.age = 23;
        user.firstName = "first name";
        user.lastName = "last name";
        user.gender = 1;
        binding.setUser(user);

        return binding.getRoot();
    }
}
