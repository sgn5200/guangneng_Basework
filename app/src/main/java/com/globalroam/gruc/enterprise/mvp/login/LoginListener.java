package com.globalroam.gruc.enterprise.mvp.login;

/**
 * Created by shang guangneng on 2016/6/24 0024.
 *
 * Login model callback interface
 */

public interface LoginListener {

    void onSuccess();

    void onError();

    void onStart();

    void onEnd();
}
