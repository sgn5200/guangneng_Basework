package com.globalroam.gruc.enterprise.http;



import com.globalroam.gruc.enterprise.http.entity.User;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by shang guangneng on 2016/6/16 0016.
 */

interface ApiService {

    @GET("data/福利/10/{page}")
    Call<String> getGrils(@Path("page") int page);

    @GET("http://gank.io/api/data/福利/10/{page}")
    Observable<ResponseBody> getGrilsRx(@Path("page") int page);

    @GET("http://gank.io/api/data/福利/10/{page}")
    Call<ResponseBody> getGrilsRe(@Path("page") int page);

    /**
     * 获取GRUC 用户
     * @param
     * @return
     */
    @GET("v1/user")
    Observable<ResponseBody> getGRUCUsers(@Header("Authorization") String token);

    /**
     * 用户注册
     * @param user
     * @return
     */
    @POST("op/create_user")
    Observable<ResponseBody> createUser(@Body User user);
}
