package com.ryan.ryanapp.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ryan.ryanapp.R;


public class FragmentMyAccountInfo extends FragmentBase {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_account_info, container, false);
    }

    @Override public void onResume() {
        super.onResume();
        activityBase.setToolbarMiddleTitle("个人信息");
    }
}
