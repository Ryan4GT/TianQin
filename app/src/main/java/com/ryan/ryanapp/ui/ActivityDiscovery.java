package com.ryan.ryanapp.ui;

import android.content.Intent;
import android.os.Bundle;

import com.ryan.ryanapp.R;

public class ActivityDiscovery extends ActivityBase {


    public static final int FRIENDS_DYNAMIC_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int openingFragmentID = intent.getIntExtra(BEING_OPENED_FRAGMENT_ID, 0);
        FragmentBase beingOpendFragment = getBeingOpendFragment(openingFragmentID);
        addContentView(R.layout.activity_discovery);
        switchFragment(beingOpendFragment, R.id.activityDiscoveryContainer, false);
    }

    private FragmentBase getBeingOpendFragment(int openingFragmentID) {
        switch (openingFragmentID) {
            case FRIENDS_DYNAMIC_ID:
                return new FragmentFriendsDynamic();
        }
        return null;
    }
}
