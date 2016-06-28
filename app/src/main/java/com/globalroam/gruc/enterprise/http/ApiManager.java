package com.globalroam.gruc.enterprise.http;

import android.support.annotation.NonNull;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.globalroam.gruc.enterprise.BuildConfig;
import com.globalroam.gruc.enterprise.http.entity.User;
import com.globalroam.gruc.enterprise.utils.ACache;
import com.globalroam.gruc.enterprise.utils.Log;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by shang guangneng on 2016/6/16 0016.
 */

public class ApiManager implements ApiRepository {

    private final String TAG = getClass().getSimpleName();

    public static final String BASE_URL="http://61.8.195.42/api/";

    private String token="JWT eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiZ3VhbmduZW5nIiwiZXhwIjoxNDY5MDgwNDk4LCJpYXQiOjE0NjY0ODg0OTgsInR5cGUiOiJTdWJVc2VyIiwibmJmIjoxNDY2NDg4NDk4LCJpZGVudGl0eSI6IlNVQlVTUjQzMjA2NWIyLWY3NDYtMTFlNS05N2I2LWZhMTYzZTE3ZjRlZCJ9.rFO66lMm0_RFk-G8TsJg8bfhaRIjg-aMq1apsTUp34c";

    private static final int TIMEOUT_READ = 5;

    private ApiService apiService;

    private ApiManager() {

        SSLSocketFactory sslSocketFactory=SSLContextUtils.getSSLContext().getSocketFactory();

        Log.i(TAG,""+ACache.cacheDir);

        //缓存目录
        Cache cache = new Cache(new File(ACache.cacheDir), 10 * 1024 * 1024);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                //添加证书
                //.sslSocketFactory(sslSocketFactory)

                //必须是设置Cache目录
                .cache(cache)
                //失败重连
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);

        if(BuildConfig.DEBUG){
            //stetho,可以在chrome中查看请求
            builder.addNetworkInterceptor(new StethoInterceptor());
        }

        Retrofit.Builder retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        retrofit.client(builder.build());
        apiService=retrofit.build().create(ApiService.class);
    }

    private static class Singleton {
        private static final ApiManager apiManager = new ApiManager();
    }

    public static ApiManager getInstance() {
        return Singleton.apiManager;
    }

    public Observable<ResponseBody> getGirlData(int page) {
        Log.i(TAG);
        return apiService.getGrilsRx(page);
    }

    public Observable<ResponseBody> getUsers() {

        HashMap<String,String> map=new HashMap<>();

        map.put("Authorization",token);
        get(null);
        return apiService.getGRUCUsers(token);
    }

    private void get(@NonNull String a){

    }

    public Observable<ResponseBody> createUser(User user) {
        return apiService.createUser(user);
    }
}
