package com.globalroam.gruc.enterprise.mvp.login;

import com.globalroam.gruc.enterprise.mvp.Presenter;
import com.globalroam.gruc.enterprise.utils.Log;

/**
 * Created by shang guangneng on 2016/6/8 0008.
 */

public class LoginPresenter implements Presenter,LoginListener {

    private LoginView view;
    private LoginModel model;
    private String username,password;

    private String TAG=getClass().getSimpleName();

    public LoginPresenter(LoginView fab,String username,String password) {
        this.view = fab;
        model=new LoginModelImp(this);

        this.username=username;
        this.password=password;
    }


    @Override
    public void executeRx(String tag) {
        Log.i(tag);
    }

    @Override
    public void executeRxRetrofit(String tag) {
        Log.i(tag);
        model.login(username,password);
    }

    @Override
    public void onSuccess() {
        Log.i(TAG);
        view.loginSuccess();
    }

    @Override
    public void onError() {
        Log.i(TAG);
        view.loginError();
    }

    @Override
    public void onStart() {
        Log.i(TAG);
        view.showDialog();
    }

    @Override
    public void onEnd() {
        Log.i(TAG);
        view.hideDialog();
    }
}
