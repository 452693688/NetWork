package com.ui.activity;


import android.os.Bundle;
import android.view.View;

import com.example.guom.network.R;
import com.network.manager.LoginManage;
import com.ui.dialog.CustomWaitingDialog;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private CustomWaitingDialog dialog;
    private LoginManage manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.req_test_btn).setOnClickListener(this);
        dialog = new CustomWaitingDialog(this);
        manager = new LoginManage(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.req_test_btn:
                dialog.show();
                manager.setData("", "");
                manager.doRequest();
                break;
        }
    }

    public void OnBack(int what, Object obj, String msg) {
        dialog.dismiss();
    }
}
