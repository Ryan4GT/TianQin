package com.ryan.ryanapp.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.ryan.ryanapp.R;
import com.ryan.ryanapp.ui.customeview.ParallaxScollListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFriendsDynamic extends FragmentBase {

    private ParallaxScollListView mListView;
    private ImageView mImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return fragmentRootView = inflater.inflate(R.layout.fragment_friends_dynamic, container, false);
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListView = (ParallaxScollListView) fragmentRootView.findViewById(R.id.layout_listview);
        View header = LayoutInflater.from(activityBase).inflate(R.layout.parallax_sroll_listview_header, null);
        mImageView = (ImageView) header.findViewById(R.id.layout_header_image);

        mListView.setParallaxImageView(mImageView);
        mListView.addHeaderView(header);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activityBase, android.R.layout.simple_expandable_list_item_1, new String[]{"First Item", "Second Item", "Third Item", "Fifth Item", "Sixth Item", "Seventh Item", "Eighth Item", "Ninth Item", "Tenth Item", "....."});
        mListView.setAdapter(adapter);
    }

    @Override public void onResume() {
        super.onResume();
        activityBase.setToolbarMiddleTitle(getString(R.string.friendsDynamic));
    }
}
