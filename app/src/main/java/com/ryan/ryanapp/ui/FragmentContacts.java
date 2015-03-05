package com.ryan.ryanapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ryan.ryanapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentContacts extends FragmentBase {

    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return fragmentRootView = inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListView = (ListView) fragmentRootView.findViewById(R.id.rabbit_reviews_list);
        ArrayList<String> datas = new ArrayList<String>();
        for (int i = 0; i < 40; i++) {
            datas.add("Item : " + i);
        }
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, datas);
        mListView.setAdapter(stringArrayAdapter);
    }

    @Override public void onResume() {
        super.onResume();
        activityBase.setToolbarMiddleTitle(getString(R.string.contacts));
        toolbar.setNavigationIcon(null);
    }
}
