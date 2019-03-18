package com.tony.processkeeplive.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by TonyYao on 2019/3/18.
 */
public class KeepActivity extends Activity {
    private static final String TAG = "KeepActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window=getWindow();
        //设置activity左上角
        window.setGravity(Gravity.START|Gravity.TOP);

        WindowManager.LayoutParams params=window.getAttributes();
        params.width=1;
        params.height=1;
        params.x=0;
        params.y=0;

        window.setAttributes(params);

        KeepManager.getOurInstance().setKeep(this);
        Log.e(TAG, "onCreate: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }
}
