package com.changingedu.dev.databindingdemo;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.changingedu.dev.databindingdemo.databinding.FragmentUserModelBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    
    public MainActivityFragment() {}
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return DataBindingUtil
                .inflate(inflater, R.layout.fragment_main, container, false).getRoot();
    }
    
    @Override
    public void onStart() {
        super.onStart();
    }
}
