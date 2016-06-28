package com.globalroam.gruc.enterprise;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Base64;

import com.globalroam.gruc.enterprise.http.entity.Token;
import com.globalroam.gruc.enterprise.utils.Log;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }


    String TAG="TEST";

    public void testStr(){
        Log.i("str");
        String t1="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiZ3VhbmduZW5nIiwiZXhwIjoxNDY5MDgwNDk4LCJpYXQiOjE0NjY0ODg0OTgsInR5cGUiOiJTdWJVc2VyIiwibmJmIjoxNDY2NDg4NDk4LCJpZGVudGl0eSI6IlNVQlVTUjQzMjA2NWIyLWY3NDYtMTFlNS05N2I2LWZhMTYzZTE3ZjRlZCJ9.rFO66lMm0_RFk-G8TsJg8bfhaRIjg-aMq1apsTUp34c";
        String t2="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiZ3VhbmduZW5nIiwiZXhwIjoxNDY5MTY2NDAxLCJpYXQiOjE0NjY1NzQ0MDEsInR5cGUiOiJTdWJVc2VyIiwibmJmIjoxNDY2NTc0NDAxLCJpZGVudGl0eSI6IlNVQlVTUjExMTcxYzc4LTM0NTktMTFlNi1hZWQ1LWZhMTYzZTE3ZjRlZCJ9.7N8aQCmdv7HrtvQGa2IWOwAlyi4YSlu3JDAC4dSWcSs";
        String t3="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiZ3VhbmduZW5nIiwiZXhwIjoxNDY5MTUyNzMxLCJpYXQiOjE0NjY1NjA3MzEsInR5cGUiOiJTdWJVc2VyIiwibmJmIjoxNDY2NTYwNzMxLCJpZGVudGl0eSI6IlNVQlVTUjQzMjA2NWIyLWY3NDYtMTFlNS05N2I2LWZhMTYzZTE3ZjRlZCJ9.z4IU3tF6qwBVoeMjdbcRgIfsKXeC1A3bzutBONaDtfI";
        String t4="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiZ3VhbmduZW5nMSIsImV4cCI6MTQ2OTE2NzE0OSwiaWF0IjoxNDY2NTc1MTQ5LCJ0eXBlIjoiU3ViVXNlciIsIm5iZiI6MTQ2NjU3NTE0OSwiaWRlbnRpdHkiOiJTVUJVU1IxMTQwMWQ2Yy0zNDU5LTExZTYtYWVkNS1mYTE2M2UxN2Y0ZWQifQ.7lJz0n4MPcUZtTGPaF57bfGyep95D-jP-rkEOWEtpvo";
        byte[] result = Base64.decode(t4, Base64.NO_PADDING);
        String s=new String(result);

        String rs=s.substring(s.indexOf("{",1),s.lastIndexOf("}")+1);

        Gson gson=new Gson();
        Token token=gson.fromJson(rs, Token.class);

        Log.i(TAG,"token rs  == "+rs);
        Log.i(TAG,"token gson  == "+token.toString());

        System.out.print("rs  "+rs);

    }

    public void testRx(){
            List<String> list = new ArrayList<String>();
            list.add("hello1");
            list.add("hello2");
            list.add("hello3");
      /*      Observable.just(list)
                .flatMap(s -> Observable.from(s))
                .subscribe(s1 -> Log.d(TAG, s1));*/

        Observable.just(list.get(1))
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String strings) {
                        return Observable.from(new String[]{strings});
                    }
                })

                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d(TAG, s+"-");
                    }
                });
    }

    public void testQuery(){

    }

}