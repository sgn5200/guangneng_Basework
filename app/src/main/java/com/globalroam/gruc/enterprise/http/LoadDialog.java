package com.globalroam.gruc.enterprise.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.ViewGroup;

/**
 * Created by shang guangneng on 2016/6/17 0017.
 */

public class LoadDialog extends ProgressDialog{
    private Context context;
    private ViewGroup viewGroup;


    public LoadDialog(Context context) {
        super(context);
    }

    public LoadDialog title(String msg){
        setTitle(msg);
        return this;
    }

    public LoadDialog message(String msg){
        setMessage(msg);
        return this;
    }

    public LoadDialog cancelable(boolean cancelable){
        setCancelable(cancelable);
        return this;
    }
}
