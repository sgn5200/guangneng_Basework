package com.globalroam.gruc.enterprise.mvp.login;

import android.content.Context;

import com.globalroam.gruc.enterprise.http.ApiManager;
import com.globalroam.gruc.enterprise.http.MySubscriber;
import com.globalroam.gruc.enterprise.http.entity.Girl;
import com.globalroam.gruc.enterprise.http.entity.GirlData;
import com.globalroam.gruc.enterprise.utils.Log;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by shang guangneng on 2016/6/8 0008.
 */

public class LoginPresenter {

    private MySubscriber<GirlData> mySubscriber;

    private String TAG = getClass().getSimpleName();

    public LoginPresenter(LoginFab fab, Context context) {
        mySubscriber = new MySubscriber<GirlData>(true,context) {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onNext(GirlData girlData) {
                super.onNext(girlData);

                for(Girl girl:girlData.getGirls()){
                    Log.i(TAG,girl.toString());
                }

                fab.loginSuccess();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(TAG,e.toString());
                fab.loginPasswordError();
                fab.loginUserNameError();
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
            }
        };
    }

    public void login(String userName, String password) {



    }

    public void testApi(int page) {
        ApiManager.getInstance()
                .getGirlData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mySubscriber);
    }
}
