package com.deepbaytech.deeplibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    /**
     * hook listener
     * @param savedInstanceState
     */
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//
//        ListenerManager.Builer builer = new ListenerManager.Builer();
//        builer.buildOnClickListener(new HookListenerContract.OnClickListener() {
//            @Override
//            public void doInListener(View v) {
//                Toast.makeText(BaseActivity.this, "单击时我执行", Toast.LENGTH_SHORT).show();
//            }
//        }).buildOnLongClickListener(new HookListenerContract.OnLongClickListener() {
//            @Override
//            public void doInListener(View v) {
//                Toast.makeText(BaseActivity.this, "长按时我执行", Toast.LENGTH_SHORT).show();
//            }
//        }).buildOnFocusChangeListener(new HookListenerContract.OnFocusChangeListener() {
//            @Override
//            public void doInListener(View v, boolean hasFocus) {
//                Toast.makeText(BaseActivity.this, "焦点变化时我执行", Toast.LENGTH_SHORT).show();
//            }
//        });
//        HookCore.getInstance().startHook(this, ListenerManager.create(builer));
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().finishActivity(this);
    }
}
