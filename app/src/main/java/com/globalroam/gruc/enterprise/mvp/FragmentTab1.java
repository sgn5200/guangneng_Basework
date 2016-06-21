package com.globalroam.gruc.enterprise.mvp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.globalroam.gruc.enterprise.R;
import com.globalroam.gruc.enterprise.mvp.call.IncallActivity;
import com.globalroam.gruc.enterprise.baseui.BaseFragment;


public class FragmentTab1 extends BaseFragment {


    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_fragment_tab1;
    }

    @Override
    public void initView() {
        initListener(v -> {
            showStnackBar("fragment stnackBar");
            lunchActivity(IncallActivity.class);
        },R.id.btTest);
    }
}
