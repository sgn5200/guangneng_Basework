package com.globalroam.gruc.enterprise.mvp.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.globalroam.gruc.enterprise.R;
import com.globalroam.gruc.enterprise.http.LoadDialog;
import com.globalroam.gruc.enterprise.http.entity.User;
import com.globalroam.gruc.enterprise.mvp.MainActivity;
import com.globalroam.gruc.enterprise.baseui.BaseActivity;
import com.globalroam.gruc.enterprise.mvp.Presenter;

public class LoginActivity extends BaseActivity implements LoginView, View.OnClickListener{

    private Button bt0, bt1, bt2, bt3;

    private Presenter loginPresenter;
    private LoadDialog loadDialog;

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

        loginPresenter = new LoginPresenter(this,"username","password");
    }

    @Override
    public void loginSuccess() {
        showToast("登陆成功");
        lunchActivity(MainActivity.class);
    }

    @Override
    public void loginError() {
        showToast("密码错误");
    }

    @Override
    public void showDialog() {
        if(null==loadDialog){
            loadDialog=new LoadDialog(this);
        }
        loadDialog.title("Network request").message("Loading...").cancelable(false).show();
    }

    @Override
    public void hideDialog() {
        if(loadDialog!=null){
            loadDialog.dismiss();
            loadDialog=null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt0:
                showToast("click bt0");
                loginPresenter.executeRxRetrofit("Login");
                break;
            case R.id.bt1:
                User user=new User();
                user.setDomain("caas.grcaassip.com");
                user.setEmail("sgn5200@gmail.com");
                user.setMobile("123456789");
                user.setPassword("123456");
                user.setUsername("aaa101");
                break;
            case R.id.bt2:
                page++;
                break;
            case R.id.bt3:
                showToast("click bt3");
                break;
        }
    }

}
