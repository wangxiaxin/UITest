package com.changingedu.dev.databindingdemo;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.changingedu.dev.databindingdemo.databinding.FragmentMainBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private FragmentMainBinding mBinding;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_main, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

        mBinding.tvDemo.setText("alsdjflaksjf");
Log.i("dd","asdfasdf  " + mBinding.tvDemo.getVisibility() + mBinding.tvDemo.getLeft() + " -" + mBinding.tvDemo.getTop());
    }
}
