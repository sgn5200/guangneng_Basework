package com.globalroam.gruc.enterprise.baseui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by shang guangneng on 2016/6/12 0012.
 * 视图层代理的接口协议
 */
public interface IDelegate {

    /**
     * 加XML载布局
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 初始化组件
     */
    void initView();

    /**
     * 初始化事件监听
     */
    void initListener(View.OnClickListener listener, int... ids);

    void initListener(View.OnClickListener listener,View... views);
}
