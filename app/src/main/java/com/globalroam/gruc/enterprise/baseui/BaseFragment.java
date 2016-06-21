package com.globalroam.gruc.enterprise.baseui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.globalroam.gruc.enterprise.utils.Log;

/**
 * Created by shang guangneng on 2016/6/8 0008.
 */
public abstract class BaseFragment extends Fragment implements IDelegate{

    protected String TAG;
    protected SparseArray<View> mViews = new SparseArray<>();
    protected View rootView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG=this.getClass().getSimpleName();
        Log.i("onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        create(inflater,container,savedInstanceState);
        initView();
        Log.i("onCreateView");
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("onViewCreated");

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("onAttach");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("onPause");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("onResume");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("onDestroy");

    }


    /**
     * 启动Activity 不带参数
     *
     * @param className
     */
    protected void lunchActivity(Class<?> className) {
        startActivity(new Intent(getActivity(), className));
    }

    /**
     * 启动Activity 带参数
     *
     * @param className
     */
    protected void lunchActivity(Class<?> className, Bundle bundle) {
        Intent intent = new Intent(getActivity(), className);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void showStnackBar(String msg){
        Snackbar.make(rootView,msg,Snackbar.LENGTH_SHORT).show();
    }

    /**
     * @param id
     * @param <T>
     * @return 通过id，获取到控件对象
     */
    public <T extends View> T bind(int id) {
        return (T) bindView(id);
    }

    /**
     * @param id
     * @param <T>
     * @return 绑定视图对象并返回
     */
    private <T extends View> T bindView(int id) {
        T view = (T) mViews.get(id);
        if (view == null) {
            view = (T) rootView.findViewById(id);
            mViews.put(id, view);
        }
        return view;
    }

    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (null == rootView) {
            rootView=inflater.inflate(getRootLayoutId(), container, false);
            Log.i(TAG, " create fragment rootView success");
        }
    }

    @Override
    public void initListener(View.OnClickListener listener, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            bind(id).setOnClickListener(listener);
        }
    }
    @Override
    public void initListener(View.OnClickListener listener,View... views) {
        if (views == null) {
            return;
        }
        for (View view : views) {
            view.setOnClickListener(listener);
        }
    }


    /**
     * @return root 设置root视图ID =xml layout id
     */
    public abstract int getRootLayoutId();
}