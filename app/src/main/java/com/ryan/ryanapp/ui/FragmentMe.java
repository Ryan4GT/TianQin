package com.ryan.ryanapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ryan.ryanapp.R;
import com.ryan.ryanapp.Utils.LogUtils;
import com.ryan.ryanapp.Utils.StringUtil;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMe extends FragmentBase {

    private ImageView headImageView;
    private TextView nicknameView;
    private String headImageLocalPath;
    private TextView exposeGoodsView;
    private TextView exposedGoodsView;

    /**
     * Use this factory method to create a new instance of this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentBase.
     */
    public static FragmentMe newInstance(Map<String, String> params) {
        FragmentMe fragmentMe = new FragmentMe();
        Bundle args = new Bundle();
        Iterator<String> iterator = params.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            args.putString(key, params.get(key));
        }
        fragmentMe.setArguments(args);
        return fragmentMe;
    }

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
//        toolbar.setTitle(R.string.fragment_me_title);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return fragmentRootView = inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        headImageView = (ImageView) fragmentRootView.findViewById(R.id.headImageView);
        nicknameView = (TextView) fragmentRootView.findViewById(R.id.nicknameView);
        exposedGoodsView = (TextView) fragmentRootView.findViewById(R.id.exposedGoodsView);
        exposeGoodsView = (TextView) fragmentRootView.findViewById(R.id.exposeGoodsView);
        headImageView.setOnClickListener(this);
        exposeGoodsView.setOnClickListener(this);
        exposedGoodsView.setOnClickListener(this);
    }

    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case ActivityImageLibraryBrowser.ACTIVITY_REQUEST_CODE_IMAGE_LIBRAY_BROWSER:
                    String imageUri = data.getStringExtra(ActivityImageLibraryBrowser.ACTIVITY_RESULT_EXTRA_KEY);
                    headImageLocalPath = ActivityImageLibraryBrowser.cropImage(this, imageUri);
                    if (StringUtil.isEmpty(headImageLocalPath)) {
                        Toast.makeText(getActivity(), "您的手机暂不支裁切操作", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case ActivityImageLibraryBrowser.ACTIVITY_REQUEST_CODE_IMAGE_CROP:
                    File headImageFile = new File(headImageLocalPath);
                    if (headImageFile.exists() && headImageFile.length() > 0) {
                        LogUtils.e(TAG, " 裁切的图片………… " + headImageLocalPath);
                    }
                    break;
            }
        } else {
            switch (requestCode) {
                case ActivityImageLibraryBrowser.ACTIVITY_REQUEST_CODE_IMAGE_LIBRAY_BROWSER:
                case ActivityImageLibraryBrowser.ACTIVITY_REQUEST_CODE_IMAGE_CROP:
                    Toast.makeText(getActivity(), "图像设置失败", Toast.LENGTH_SHORT).show();
                    headImageLocalPath = null;
                    break;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.headImageView:
                ActivityImageLibraryBrowser.chooseImageFromLibrary(this);
                break;
        }
    }
}
