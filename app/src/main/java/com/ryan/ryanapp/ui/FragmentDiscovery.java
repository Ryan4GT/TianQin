package com.ryan.ryanapp.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ryan.ryanapp.R;

public class FragmentDiscovery extends FragmentBase {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return fragmentRootView = inflater.inflate(R.layout.fragment_discovery, container, false);
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View friendsDynamicView = fragmentRootView.findViewById(R.id.friendsDynamicView);
        View newActivityView = fragmentRootView.findViewById(R.id.newActivityView);
        View circleView = fragmentRootView.findViewById(R.id.circleView);
        friendsDynamicView.setOnClickListener(this);
        newActivityView.setOnClickListener(this);
        circleView.setOnClickListener(this);
    }

    @Override public void onResume() {
        super.onResume();
        toolbar.setNavigationIcon(null);
        activityBase.setToolbarMiddleTitle(R.string.discovery);
    }

    @Override public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.friendsDynamicView:
                Intent intent = new Intent(activityBase, ActivityDiscovery.class);
                intent.putExtra(ActivityBase.BEING_OPENED_FRAGMENT_ID, ActivityDiscovery.FRIENDS_DYNAMIC_ID);
                startActivity(intent);
                break;
            case R.id.newActivityView:

                break;
            case R.id.circleView:

                break;
        }
    }
}
