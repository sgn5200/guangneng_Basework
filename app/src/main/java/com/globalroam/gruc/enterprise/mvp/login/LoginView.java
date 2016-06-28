package com.globalroam.gruc.enterprise.mvp.login;

/**
 * Created by shang guangneng on 2016/6/8 0008.
 *
 * this seems to be Login callback
 */
public interface LoginView {

    void loginSuccess();

    void loginError();

    void showDialog();

    void hideDialog();

}
