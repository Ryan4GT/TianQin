package com.ryan.ryanapp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ryan.ryanapp.R;
import com.ryan.ryanapp.Utils.UnitFormatter;
import com.ryan.ryanapp.ui.customeview.FlowLayout;
import com.ryan.ryanapp.ui.customeview.PagerSlidingTabStrip;
import com.ryan.ryanapp.ui.customeview.PullToRefreshRecyclerView;

import java.util.List;

public class FragmentMain extends FragmentBase {

    private ViewPager mainViewPager;
    private List<String> datas;

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return fragmentRootView = inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainViewPager = (ViewPager) fragmentRootView.findViewById(R.id.mainViewPager);

    }

    @Override public void onResume() {
        super.onResume();
        toolbar.setTitle("");
        toolbar.setNavigationIcon(null);
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.activity_main_menu);
        activityBase.setToolbarMiddleTittleVisibility(View.GONE);
        activityBase.setCustomToolbarMiddleViewVisibility(View.VISIBLE);
        PagerSlidingTabStrip pagerSlidingTabStrip = new PagerSlidingTabStrip(activityBase);
        pagerSlidingTabStrip.setIndicatorColor(getResources().getColor(R.color.transparent));
        pagerSlidingTabStrip.setDividerColor(getResources().getColor(R.color.transparent));
        pagerSlidingTabStrip.setTextColor(getResources().getColor(R.color.white));
        pagerSlidingTabStrip.setTextSize((int) getResources().getDimension(R.dimen.abc_text_size_medium_material));
        pagerSlidingTabStrip.setUnderlineHeight(0);
        pagerSlidingTabStrip.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);
        pagerSlidingTabStrip.setTabPaddingLeftRight(UnitFormatter.getDPUnit(20));
        pagerSlidingTabStrip.setSelectedTabBackgroud(R.drawable.ic_launcher);
        activityBase.setCustomToolbarMiddleView(pagerSlidingTabStrip);
        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter();
        mainViewPager.setAdapter(mainPagerAdapter);
        pagerSlidingTabStrip.setViewPager(mainViewPager);
        MainOnPageChangeListener mainOnPageChangeListener = new MainOnPageChangeListener();
        pagerSlidingTabStrip.setOnPageChangeListener(mainOnPageChangeListener);
    }

    class MainPagerAdapter extends PagerAdapter {

        @Override public int getCount() {
            return 5;
        }

        @Override public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override public Object instantiateItem(ViewGroup container, int position) {
            PullToRefreshRecyclerView<String> pullToRefreshRecyclerView = new PullToRefreshRecyclerView<String>(activityBase) {
                @Override public void onBindBaseViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
                    MainItemViewHolder mainItemViewHolder = ((MainItemViewHolder) viewHolder);
                    mainItemViewHolder.nicknameView.setText(datas.get(position));
                }

                @Override public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup viewGroup, int itemType) {
                    View itemView = LayoutInflater.from(activityBase).inflate(R.layout.fragment_main_item, null, false);
                    return new MainItemViewHolder(itemView);
                }

                @Override public int getBaseItemViewType(int position) {
                    return 0;
                }

                @Override public void onLoadMore() {

                }

                @Override public void onRefreshing() {

                }
            };
            datas = pullToRefreshRecyclerView.getDatas();
            for (int i = 0; i < 20; i++) {
                datas.add("Item " + i);
            }
            pullToRefreshRecyclerView.notifyDataSetChanged();
            container.addView(pullToRefreshRecyclerView);
            return pullToRefreshRecyclerView;
        }

        @Override public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.main_nearby);
                case 1:
                    return getString(R.string.main_female);
                case 2:
                    return getString(R.string.main_male);
                case 3:
                    return getString(R.string.main_hostel);
                case 4:
                    return getString(R.string.main_food);
            }
            return super.getPageTitle(position);
        }
    }

    class MainOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override public void onPageSelected(int position) {

        }

        @Override public void onPageScrollStateChanged(int state) {

        }
    }

    class MainItemViewHolder extends RecyclerView.ViewHolder {
        ImageView headImageView;
        TextView nicknameView;
        TextView distanceAndLoginTimeView;
        org.apmem.tools.layouts.FlowLayout userTagsView2;
        FlowLayout userTagsView;
        TextView introductionView;

        public MainItemViewHolder(View itemView) {
            super(itemView);
            initItemView(itemView);
        }

        private void initItemView(View itemView) {
            headImageView = (ImageView) itemView.findViewById(R.id.headImageView);
            nicknameView = (TextView) itemView.findViewById(R.id.nicknameView);
            distanceAndLoginTimeView = (TextView) itemView.findViewById(R.id.distanceAndLoginTimeView);
            userTagsView = (FlowLayout) itemView.findViewById(R.id.userTagsView);
            userTagsView2 = (org.apmem.tools.layouts.FlowLayout) itemView.findViewById(R.id.userTagsView2);
            for (int i = 0; i < 10; i++) {
                TextView textView = new TextView(activityBase);
                textView.setText("  TAG " + i);
                userTagsView2.addView(textView);
            }
            introductionView = (TextView) itemView.findViewById(R.id.introdutionView);
        }


    }


}
