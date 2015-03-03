package com.ryan.ryanapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.ryan.ryanapp.R;


public class ActivitySplash extends ActivityBase implements Handler.Callback {
    private Handler handler = new Handler(this);

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarVisibility(false);
        addContentView(R.layout.fragment_splash);
        setToolbarVisibility(false);
        handler.sendEmptyMessageDelayed(0, 1000);
    }

    @Override public boolean handleMessage(Message msg) {
        startActivity(new Intent(this, ActivityMain.class));
        finish();
        return true;
    }
}
