package com.globalroam.gruc.enterprise.http;

import com.globalroam.gruc.enterprise.http.entity.GirlData;

import rx.Observable;

/**
 * Created by shang guangneng on 2016/6/16 0016.
 */

public interface ApiRepository {

    Observable<GirlData> getGirlData(int page);

}
