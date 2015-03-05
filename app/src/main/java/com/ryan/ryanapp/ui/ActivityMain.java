package com.ryan.ryanapp.ui;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ryan.ryanapp.R;

import java.util.HashMap;

public class ActivityMain extends ActivityBase {

    public static final int MAIN_TAB = 1;
    public static final int CONTACTS_TAB = 2;
    public static final int MESSAGE_TAB = 3;
    public static final int ME_TAB = 4;
    public static final int DISCOVERY_TAB = 5;
    private int currentTab;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarMiddleTitle("MainActivity");
        toolbar.setNavigationIcon(R.drawable.ic_launcher);
        addContentView(R.layout.activity_main);
        View mainTab = findViewById(R.id.mainTab);
        View orderTab = findViewById(R.id.orderTab);
        View messageTab = findViewById(R.id.messageTab);
        View meTab = findViewById(R.id.meTab);
        View discoveryTab = findViewById(R.id.discoveryTab);
        mainTab.setOnClickListener(this);
        orderTab.setOnClickListener(this);
        messageTab.setOnClickListener(this);
        meTab.setOnClickListener(this);
        discoveryTab.setOnClickListener(this);
        setCurrentTab(MAIN_TAB);
    }


    public void setCurrentTab(int position) {
        if (position == currentTab) {
            return;
        }
        ImageView mainTabImage = (ImageView) findViewById(R.id.mainTabImage);
        TextView mainTabTitle = (TextView) findViewById(R.id.mainTabTitle);
        ImageView orderTabImage = (ImageView) findViewById(R.id.orderTabImage);
        TextView orderTabTitle = (TextView) findViewById(R.id.orderTabTitle);
        ImageView messageTabImage = (ImageView) findViewById(R.id.messageTabImage);
        TextView messageTabTitle = (TextView) findViewById(R.id.messageTabTitle);
        ImageView meTabImage = (ImageView) findViewById(R.id.meTabImage);
        TextView meTabTitle = (TextView) findViewById(R.id.meTabTitle);
        ImageView discoveryTabImage = (ImageView) findViewById(R.id.discoveryTabImage);
        TextView discoveryTabTitle = (TextView) findViewById(R.id.discoveryTabTitle);
        mainTabImage.setImageResource(R.drawable.main_normal);
        orderTabImage.setImageResource(R.drawable.order_normal);
        messageTabImage.setImageResource(R.drawable.message_normal);
        meTabImage.setImageResource(R.drawable.me_normal);
        discoveryTabImage.setImageResource(R.drawable.discovery_normal);
        discoveryTabTitle.setTextColor(getResources().getColor(R.color.black));
        mainTabTitle.setTextColor(getResources().getColor(R.color.black));
        orderTabTitle.setTextColor(getResources().getColor(R.color.black));
        messageTabTitle.setTextColor(getResources().getColor(R.color.black));
        meTabTitle.setTextColor(getResources().getColor(R.color.black));
        switch (position) {
            case MAIN_TAB:
                switchFragment(new FragmentMain(), R.id.activityMainContainer, false);
                mainTabImage.setImageResource(R.drawable.main_selected);
                mainTabTitle.setTextColor(getResources().getColor(R.color.theme_color));
                break;
            case MESSAGE_TAB:
                switchFragment(FragmentMessage.newInstance(new HashMap<String, String>()), R.id.activityMainContainer, false);
                messageTabImage.setImageResource(R.drawable.message_selected);
                messageTabTitle.setTextColor(getResources().getColor(R.color.theme_color));
                break;
            case CONTACTS_TAB:
                switchFragment(new FragmentContacts(), R.id.activityMainContainer, false);
                orderTabImage.setImageResource(R.drawable.order_selected);
                orderTabTitle.setTextColor(getResources().getColor(R.color.theme_color));
                break;
            case ME_TAB:
                switchFragment(new FragmentMe(), R.id.activityMainContainer, false);
                meTabImage.setImageResource(R.drawable.me_selected);
                meTabTitle.setTextColor(getResources().getColor(R.color.theme_color));
                break;
            case DISCOVERY_TAB:
                switchFragment(new FragmentDiscovery(), R.id.activityMainContainer, false);
                discoveryTabImage.setImageResource(R.drawable.discovery_selected);
                discoveryTabTitle.setTextColor(getResources().getColor(R.color.theme_color));
                break;
        }
        currentTab = position;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.mainTab:
                setCurrentTab(MAIN_TAB);
                break;
            case R.id.orderTab:
                setCurrentTab(CONTACTS_TAB);
                break;
            case R.id.messageTab:
                setCurrentTab(MESSAGE_TAB);
                break;
            case R.id.meTab:
                setCurrentTab(ME_TAB);
                break;
            case R.id.discoveryTab:
                setCurrentTab(DISCOVERY_TAB);
                break;
        }
    }

    @Override public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
