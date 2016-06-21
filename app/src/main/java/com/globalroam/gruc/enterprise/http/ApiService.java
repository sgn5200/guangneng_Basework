package com.globalroam.gruc.enterprise.http;


import com.globalroam.gruc.enterprise.http.entity.GirlData;
import com.globalroam.gruc.enterprise.http.entity.QuestOTP;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by shang guangneng on 2016/6/16 0016.
 */

interface ApiService {

    @GET("data/福利/10/{page}")
    Call<GirlData> getGrils(@Path("page") int page);

    @GET("data/福利/10/{page}")
    Observable<GirlData> getGrilsRx(@Path("page") int page);

    @PUT("CoreApi/v1/otp")
    Observable<QuestOTP> getOtp();
}
