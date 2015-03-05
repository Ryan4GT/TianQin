package com.ryan.ryanapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ryan.ryanapp.Utils.UnitFormatter;


/**
 * TODO: document your custom view class.
 */
public class SettingItemView extends RelativeLayout {
    private String settingItemTitle = "";
    private String settingItemCount = "";
    private int settingItemTitleColor = Color.BLACK;
    private int settingItemCountColor = Color.BLACK;
    private int settingItemTitleSize = 18;
    private int settingItemIconSize = UnitFormatter.getDPUnit(30);
    private int settingItemCountSize = 18;
    private boolean settingItemForwarArrowVisible = true;
    private boolean settingItemIconVisible = true;
    private Drawable settingItemIcon;
    private ImageView itemForwardArrowView;
    private TextView itemCountView;
    private TextView itemTitleView;

    public SettingItemView(Context context) {
        super(context);
        init(null, 0);
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }


    private void init(AttributeSet attrs, int defStyle) {

        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SettingItemView, defStyle, 0);
        settingItemTitle = a.getString(R.styleable.SettingItemView_settingItemTitle);
        settingItemTitleColor = a.getColor(R.styleable.SettingItemView_settingItemTitleColor, settingItemTitleColor);
        settingItemCount = a.getString(R.styleable.SettingItemView_settingItemCount);
        settingItemCountColor = a.getColor(R.styleable.SettingItemView_settingItemCountColor, settingItemCountColor);
        settingItemTitleSize = a.getDimensionPixelOffset(R.styleable.SettingItemView_settingItemTitleSize, settingItemTitleSize);
        settingItemIconSize = a.getDimensionPixelOffset(R.styleable.SettingItemView_settingItemIconSize, settingItemIconSize);
        settingItemIconVisible = a.getBoolean(R.styleable.SettingItemView_settingItemIconVisible, settingItemIconVisible);
        settingItemCountSize = a.getDimensionPixelOffset(R.styleable.SettingItemView_settingItemCountSize, settingItemCountSize);
        settingItemForwarArrowVisible = a.getBoolean(R.styleable.SettingItemView_settingItemForwardArrowVisible, settingItemForwarArrowVisible);
        if (a.hasValue(R.styleable.SettingItemView_settingItemIcon)) {
            settingItemIcon = a.getDrawable(R.styleable.SettingItemView_settingItemIcon);
            settingItemIcon.setCallback(this);
        }
        a.recycle();
        View settingItemView = inflate(getContext(), R.layout.setting_item_view, this);
        ImageView itemIconView = (ImageView) findViewById(R.id.itemIconView);
        if (settingItemIconVisible) {
            LayoutParams layoutParams = new LayoutParams(settingItemIconSize, settingItemIconSize);
            layoutParams.addRule(CENTER_VERTICAL);
            layoutParams.rightMargin = UnitFormatter.getDPUnit(10);
            itemIconView.setLayoutParams(layoutParams);
        } else {
            itemIconView.setVisibility(View.GONE);
        }
        //        LogUtils.i("SettingItemView", settingItemIconSize + "  " + UnitFormatter.getDPUnit(30));
        itemTitleView = (TextView) findViewById(R.id.itemTitleView);
        itemTitleView.setTextSize(settingItemTitleSize);
        itemTitleView.setText(settingItemTitle);
        itemTitleView.setTextColor(settingItemTitleColor);
        itemCountView = (TextView) findViewById(R.id.itemCountView);
        itemCountView.setTextSize(settingItemCountSize);
        itemCountView.setText(settingItemCount);
        itemForwardArrowView = (ImageView) findViewById(R.id.itemForwardAarowView);
        if (!settingItemForwarArrowVisible) {
            itemForwardArrowView.setVisibility(View.GONE);
        }
    }

    /**
     * 设置箭头是否可见
     */
    public void setSettingItemForwarArrowVisibility(int visibility) {
        itemForwardArrowView.setVisibility(visibility);
    }

    public String getSettingItemTitle() {
        return settingItemTitle;
    }

    public void setSettingItemTitle(String settingItemTitle) {
        this.settingItemTitle = settingItemTitle;
    }

    public String getSettingItemCount() {
        return settingItemCount;
    }

    public void setSettingItemCount(String settingItemCount) {
        this.settingItemCount = settingItemCount;
    }

    public int getSettingItemTitleColor() {
        return settingItemTitleColor;
    }

    public void setSettingItemTitleColor(int settingItemTitleColor) {
        this.settingItemTitleColor = settingItemTitleColor;
        itemTitleView.setTextColor(settingItemTitleColor);
    }

    public int getSettingItemCountColor() {
        return settingItemCountColor;
    }

    public void setSettingItemCountColor(int settingItemCountColor) {
        this.settingItemCountColor = settingItemCountColor;
        itemCountView.setTextColor(settingItemCountColor);
    }

}
