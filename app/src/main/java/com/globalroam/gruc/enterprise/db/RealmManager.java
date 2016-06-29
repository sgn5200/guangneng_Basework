package com.globalroam.gruc.enterprise.db;

import com.globalroam.gruc.enterprise.http.entity.User;
import com.globalroam.gruc.enterprise.utils.Log;

import io.realm.Realm;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by shang guangneng on 2016/6/29 0029.
 */

public class RealmManager {

    public Observable<User> rigestUser(User user){
        return RealmObservable.object(new Func1<Realm, User>() {

            @Override
            public User call(Realm realm) {
                return realm.copyToRealm(user);
            }
        });
    }

    public Observable<String> findUser(final String username) {
        return RealmObservable.object(new Func1<Realm, User>() {
            @Override
            public User call(Realm realm) {
                User user=realm.where(User.class).equalTo("username", username).findFirst();
                Log.i("call",user.toString());
                return user;
            }
        }).map(new Func1<User, String>() {
            @Override
            public String call(User user) {
                return user.toString();
            }
        });

        /*map(new Func1<User, String>() {
            @Override
            public String call(User user) {
                return user.toString();
            }
        });*/
    }

}
