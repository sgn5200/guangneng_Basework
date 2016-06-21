package com.globalroam.gruc.enterprise.mvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.globalroam.gruc.enterprise.R;
import com.globalroam.gruc.enterprise.mvp.login.LoginActivity;

public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(() -> {
            startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
            overridePendingTransition(R.anim.left_in, R.anim.left_out);
        }, 1000);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
