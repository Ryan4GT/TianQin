package com.ryan.ryanapp.ui;

import android.content.Intent;
import android.os.Bundle;

import com.ryan.ryanapp.R;

public class ActivityMyAccount extends ActivityBase {


    public static final int MY_ACCOUNT_INFO_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int openingFragmentID = intent.getIntExtra(BEING_OPENED_FRAGMENT_ID, 0);
        FragmentBase beingOpendFragment = getBeingOpendFragment(openingFragmentID);
        addContentView(R.layout.activity_my_account);
        switchFragment(beingOpendFragment, R.id.activityMyAccountInfoContainer, false);
    }

    private FragmentBase getBeingOpendFragment(int openingFragmentID) {
        switch (openingFragmentID) {
            case MY_ACCOUNT_INFO_ID:
                return new FragmentMyAccountInfo();
        }
        return null;
    }


}
