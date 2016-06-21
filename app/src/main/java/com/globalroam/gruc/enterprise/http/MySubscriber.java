package com.globalroam.gruc.enterprise.http;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import rx.Subscriber;

/**
 * Created by shang guangneng on 2016/6/16 0016.
 */

public abstract class MySubscriber<T> extends Subscriber<T> {

    LoadDialog loadDialog;

    public MySubscriber(boolean loading, @Nullable Context context){
        if(loading){
            loadDialog=new LoadDialog(context);
            loadDialog.title("msg")
                    .cancelable(false)
                    .message("aaaa");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if(loadDialog!=null){
            loadDialog.show();
        }
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onCompleted() {
        if(loadDialog!=null){
            loadDialog.cancel();
        }
    }

    @Override
    public void onError(Throwable e) {
        if(loadDialog!=null){
            loadDialog.cancel();
        }
    }

}
