package com.example.ryan.testpackapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import designPatern.XiJinPing;

/**
 * Created by ryan on 4/10/16.
 */
public class TestLeakCanaryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testleakcanary);
        TextView leakcanary = (TextView) findViewById(R.id.test_leakcanary);
        XiJinPing.getInstance(this).setRetainedTextView(leakcanary);
    }
}
