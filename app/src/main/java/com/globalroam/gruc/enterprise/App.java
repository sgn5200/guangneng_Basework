package com.globalroam.gruc.enterprise;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.globalroam.gruc.enterprise.utils.Log;
import com.tencent.bugly.crashreport.CrashReport;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.util.regex.Pattern;

/**
 * Created by shang guangneng on 2016/6/8 0008.
 */
public class App extends Application {
    private String TAG="GRUCApplication";
    private static App instance;
    public static App getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;


        CrashReport.initCrashReport(getApplicationContext(), "900036343", false);

        if(com.globalroam.gruc.enterprise.BuildConfig.DEBUG){
            Log.i(TAG,"is debug");
            Stetho.initialize(Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                    .build());
            byte[] bytes=new byte[64];// 加密 key
            RealmInspectorModulesProvider.builder(this)
                    .withFolder(getCacheDir())
                    .withEncryptionKey("encrypted.realm",bytes)
                    .withMetaTables()
                    .withDescendingOrder()
                    .withLimit(1000)
                    .databaseNamePattern(Pattern.compile(".+\\.realm"))
                    .build();
        }else {
            Log.i(TAG,"is not debug");
        }

    }
}
