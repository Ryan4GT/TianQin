package com.ryan.ryanapp.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ryan.ryanapp.R;

public class ActivityBase extends ActionBarActivity implements OnMenuItemClickListener, View.OnClickListener {

    protected String TAG;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        setContentView(R.layout.activity_base);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(this);
        toolbar.setNavigationOnClickListener(this);
        toolbar.setEnabled(true);
    }


    /* 设置Toolbar自定义Tittle的内容* */
    public void setToolbarMiddleTitle(String middleTitle) {
        ((TextView) findViewById(R.id.toolbarMiddleTittle)).setText(middleTitle);
    }

    /* 设置Toolbar自定义Tittle的内容* */
    public void setToolbarMiddleTitle(int middleTitleRes) {
        String middleTitle = getString(middleTitleRes);
        setToolbarMiddleTitle(middleTitle);
    }

    /**
     * 设置Toolbar中间视图
     */
    public void setCustomToolbarMiddleView(View customeToolbarMiddleView) {
        LinearLayout toolbarMiddleView = (LinearLayout) findViewById(R.id.toolbarMiddleView);
        toolbarMiddleView.removeAllViews();
        toolbarMiddleView.addView(customeToolbarMiddleView);
    }

    /**
     * 设置Toolbar是否可见
     */
    public void setToolbarVisibility(boolean visible) {
        if (visible) {
            toolbar.setVisibility(View.VISIBLE);
        } else {
            toolbar.setVisibility(View.GONE);
        }
    }

    /**设置Toolbar分割线是否可见*/
    public void setToolbarDeviderVisibility(boolean visible) {
        View toolbarDevider = findViewById(R.id.toolbarDevider);
        if (visible) {
            toolbarDevider.setVisibility(View.VISIBLE);
        } else {
            toolbarDevider.setVisibility(View.GONE);
        }
    }

    /**
     * 子类的Activity向添加显示的界面视图
     */
    public void addContentView(int layoutResID) {
        View contentView = LayoutInflater.from(this).inflate(layoutResID, null, false);
        addContentView(contentView);
    }

    public void addContentView(View contentView) {
        ViewStub contentViewStub = (ViewStub) findViewById(R.id.contentViewStub);
        LinearLayout contentViewContainer = (LinearLayout) contentViewStub.inflate().findViewById(R.id.contentViewContainer);
        contentViewContainer.removeAllViews();
        contentViewContainer.addView(contentView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
    }


    /**
     * 切换Frament
     *
     * @param addToBackStack 是否放入返回栈
     */
    protected void switchFragment(FragmentBase beingOpenedFragment, int container, boolean addToBackStack) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out);
        if (addToBackStack) {
            transaction.addToBackStack(beingOpenedFragment.getClass().getSimpleName());
        }
        transaction.replace(container, beingOpenedFragment, beingOpenedFragment.getClass().getSimpleName());
        transaction.commit();
    }

    /**
     * 移除Frament
     */
    protected void removeFragment(FragmentBase fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(fragment);
        transaction.commit();
        fragmentManager.popBackStack();
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }
}
