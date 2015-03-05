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

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMe extends FragmentBase {

    private ImageView headImageView;
    private TextView nicknameView;
    private String headImageLocalPath;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return fragmentRootView = inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View meView = fragmentRootView.findViewById(R.id.meView);
        View purseView = fragmentRootView.findViewById(R.id.purseView);
        View orderView = fragmentRootView.findViewById(R.id.orderView);
        View photoAlbumView = fragmentRootView.findViewById(R.id.photoAlbumView);
        View addressView = fragmentRootView.findViewById(R.id.addressView);
        View couponView = fragmentRootView.findViewById(R.id.couponView);
        View freeTicketsView = fragmentRootView.findViewById(R.id.freeTicketsView);
        View sellerToolsView = fragmentRootView.findViewById(R.id.sellerToolsView);
        meView.setOnClickListener(this);
        purseView.setOnClickListener(this);
        orderView.setOnClickListener(this);
        photoAlbumView.setOnClickListener(this);
        addressView.setOnClickListener(this);
        couponView.setOnClickListener(this);
        freeTicketsView.setOnClickListener(this);
        sellerToolsView.setOnClickListener(this);
        headImageView = (ImageView) fragmentRootView.findViewById(R.id.headImageView);
        nicknameView = (TextView) fragmentRootView.findViewById(R.id.nicknameView);
        headImageView.setOnClickListener(this);
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


    @Override public void onResume() {
        super.onResume();
        toolbar.setNavigationIcon(null);
        activityBase.setToolbarMiddleTitle("我的");
    }

    @Override public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.headImageView:
                ActivityImageLibraryBrowser.chooseImageFromLibrary(this);
                break;
            case R.id.meView:
                Intent intent = new Intent(activityBase, ActivityMyAccount.class);
                intent.putExtra(ActivityBase.BEING_OPENED_FRAGMENT_ID, ActivityMyAccount.MY_ACCOUNT_INFO_ID);
                startActivity(intent);
                break;
        }
    }
}
