package com.globalroam.gruc.enterprise.baseui;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.globalroam.gruc.enterprise.R;
import com.globalroam.gruc.enterprise.http.netstatus.NetChangeCallBack;
import com.globalroam.gruc.enterprise.http.netstatus.NetStatusReceiver;
import com.globalroam.gruc.enterprise.utils.Log;
import com.globalroam.gruc.enterprise.utils.NetUtil;

/**
 * Created by shang guangneng on 2016/6/8 0008.
 * Android development framework
 */
public abstract class BaseActivity extends FragmentActivity implements IDelegate {


    protected String TAG;

    private NetChangeCallBack netChangeCallBack;
    private SparseArray<View> mViews = new SparseArray<>();
    private View rootView = null;

    /**
     * @return root 设置root视图ID =xml layout id
     */
    public abstract int getRootLayoutId();

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
            view = (T) this.findViewById(id);
            mViews.put(id, view);
        }
        return view;
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
     * 返回当前的Activity
     *
     * @param <T>
     * @return
     */
    public <T extends Activity> T getActivity() {
        return (T) rootView.getContext();
    }

    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (null == rootView) {
            rootView=inflater.inflate(getRootLayoutId(), container, false);
            Log.i(TAG, " create rootView success");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();

        create(getLayoutInflater(), null, savedInstanceState);

        setContentView(rootView);

        initNetworkListener();

        ActivityCollections.getInstance().addActivity(this);

        setTopBar();

        initView();

    }

    /**
     * 设置网络状态改变回掉
     */
    private void initNetworkListener() {
        netChangeCallBack = new NetChangeCallBack() {
            @Override
            public void onNetConnected(NetUtil.NetType type) {
                handleNetWorkConnection();
            }

            @Override
            public void onNetDisConnected() {
                handleNetWorkDisConnection();
            }
        };
        NetStatusReceiver.registerNetChangeCallBack(netChangeCallBack);
        NetStatusReceiver.registerNetworkStateReceiver(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG);
        NetStatusReceiver.unRegisterNetworkStateReceiver(this);
        NetStatusReceiver.removeRegisterNetChangeCallBack(netChangeCallBack);
        ActivityCollections.getInstance().removeActivity(this);
    }

    /**
     * 显示一个提示信息
     *
     * @param msg
     */
    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示一个提示信息
     *
     * @param strId 显示信息在XML中ID
     */
    protected void showToast(int strId) {
        Toast.makeText(this, strId, Toast.LENGTH_SHORT).show();
    }


    /**
     * 启动Activity 不带参数
     *
     * @param className
     */
    protected void lunchActivity(Class<?> className) {
        startActivity(new Intent(this, className));
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
        finish();
    }

    /**
     * 启动Activity 带参数
     *
     * @param className
     */
    protected void lunchActivity(Class<?> className, Bundle bundle) {
        Intent intent = new Intent(this, className);
        if (null != bundle) {
            intent.putExtras(bundle);
            overridePendingTransition(bundle.getInt("animIn", R.anim.left_in), bundle.getInt("animOut", R.anim.left_out));
        }
        startActivity(intent);
        finish();
    }

    /**
     * 启动一个Activity 并等待返回
     *
     * @param className
     * @param requestCode
     * @param bundle
     */
    protected void lunchActivityForResult(Class<?> className, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, className);
        if (null != bundle) {
            intent.putExtras(bundle);
            overridePendingTransition(bundle.getInt("animIn", R.anim.left_in), bundle.getInt("animOut", R.anim.left_out));
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 设置状态栏
     */
    protected void setTopBar() {
    }

    /**
     * 网络状态连接时回掉方法
     */
    protected void handleNetWorkConnection() {
    }

    /**
     * 网络不可用回掉方法
     */
    protected void handleNetWorkDisConnection() {
    }

}