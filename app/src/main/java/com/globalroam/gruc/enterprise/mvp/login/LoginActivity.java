package com.globalroam.gruc.enterprise.mvp.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.globalroam.gruc.enterprise.R;
import com.globalroam.gruc.enterprise.mvp.MainActivity;
import com.globalroam.gruc.enterprise.baseui.BaseActivity;
import com.globalroam.gruc.enterprise.http.entity.Girl;
import com.globalroam.gruc.enterprise.http.entity.GirlData;
import com.globalroam.gruc.enterprise.utils.Log;
import com.globalroam.gruc.enterprise.utils.SPUtil;

import rx.Subscriber;

public class LoginActivity extends BaseActivity implements LoginFab, View.OnClickListener {

    private Button bt0, bt1, bt2, bt3;
    private Subscriber<GirlData> sub;
    LoginPresenter loginControl;
    int page=1;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        bt0 = bind(R.id.bt0);
        bt1 = bind(R.id.bt1);
        bt2 = bind(R.id.bt2);
        bt3 = bind(R.id.bt3);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListener(this, bt0, bt1, bt2, bt3);

        sub=new Subscriber<GirlData>() {
            @Override
            public void onCompleted() {
                Log.i(TAG);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG," onError "+e.toString());

            }

            @Override
            public void onNext(GirlData girlData) {
                Log.i(TAG,girlData.isError()+"");

                for(Girl girl:girlData.getGirls()){
                    Log.i(TAG,girl.toString());
                }
            }
        };

        loginControl = new LoginPresenter(this,this);
    }


    @Override
    public void loginSuccess() {
        showToast("登陆成功");
        lunchActivity(MainActivity.class);
    }

    @Override
    public void loginUserNameError() {
        showToast("用户名错误");

    }

    @Override
    public void loginPasswordError() {
        showToast("密码错误");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt0:
                showToast("click bt0");

                SPUtil.put(this,"BT0","page"+page);
                break;
            case R.id.bt1:
                showToast(SPUtil.get(this,"BT0","null")+"");
                break;
            case R.id.bt2:
                page++;
                loginControl.testApi(page);
                break;
            case R.id.bt3:
                showToast("click bt3");
                loginControl.login("username","password");
                break;
        }
    }
}
