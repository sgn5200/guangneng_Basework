package com.globalroam.gruc.enterprise.http;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.globalroam.gruc.enterprise.BuildConfig;
import com.globalroam.gruc.enterprise.http.entity.GirlData;
import com.globalroam.gruc.enterprise.utils.ACache;
import com.globalroam.gruc.enterprise.utils.Log;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by shang guangneng on 2016/6/16 0016.
 */

public class ApiManager implements ApiRepository {

    private final String TAG = getClass().getSimpleName();

    private static final String BASE_URL = "http://gank.io/api/";

    private static final int TIMEOUT_READ = 25;

    private ApiService apiService;

    private ApiManager() {

        SSLSocketFactory sslSocketFactory=SSLContextUtils.getSSLContext().getSocketFactory();

        Log.i(TAG,""+ACache.cacheDir);

        //缓存目录
        Cache cache = new Cache(new File(ACache.cacheDir), 10 * 1024 * 1024);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                //必须是设置Cache目录
                .cache(cache)
                //失败重连
                .retryOnConnectionFailure(true)
                //time out
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS);
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

    @Override
    public Observable<GirlData> getGirlData(int page) {
        return apiService.getGrilsRx(page);
    }
}
