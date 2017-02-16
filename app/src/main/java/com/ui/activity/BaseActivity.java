package com.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.network.common.net.able.RequestBack;

import java.io.Serializable;


/**
 * Created by Administrator on 2016/3/29.
 */
public class BaseActivity extends AppCompatActivity implements RequestBack {
    private final String TAG = "BaseActivity";
    public BaseApplication baseApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseApplication = (BaseApplication) getApplication();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    public Serializable getDataObj(String key) {
        Intent it = getIntent();
        Bundle bundle = null;
        if (it != null) {
            bundle = it.getExtras();
        }
        Serializable obj = null;
        if (bundle != null) {
            obj = bundle.getSerializable(key);
        }
        return obj;
    }


    public void OnBack(int what, Object obj, String msg) {

    }

    @Override
    public void OnBack(int what, Object obj, String msg, String other) {
        OnBack(what, obj, msg);
    }


}
