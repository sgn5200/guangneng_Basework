package com.globalroam.gruc.enterprise.mvp.login;

import com.globalroam.gruc.enterprise.http.ApiManager;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by shang guangneng on 2016/6/24 0024.
 */

public class LoginModelImp implements LoginModel {

    private LoginListener listener;

    public LoginModelImp(LoginListener loginListener){
        this.listener=loginListener;
    }

    @Override
    public void login(String userName, String password) {
        ApiManager.getInstance()
                .getGirlData(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        listener.onStart();
                    }

                    @Override
                    public void onCompleted() {
                        listener.onEnd();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        listener.onError();
                    }

                    @Override
                    public void onNext(ResponseBody body) {
                        listener.onSuccess();
                    }
                });
    }
}
