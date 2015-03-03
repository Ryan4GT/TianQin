package com.ryan.ryanapp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ryan.ryanapp.R;

import java.util.Iterator;
import java.util.Map;

public class FragmentMain extends FragmentBase {

    /**
     * Use this factory method to create a new instance of this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentBase.
     */
    public static FragmentMain newInstance(Map<String, String> params) {
        FragmentMain fragmentMain = new FragmentMain();
        Bundle args = new Bundle();
        Iterator<String> iterator = params.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            args.putString(key, params.get(key));
        }
        fragmentMain.setArguments(args);
        return fragmentMain;
    }

    public FragmentMain() {}

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        toolbar.setTitle(R.string.fragment_main_title);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return fragmentRootView = inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
