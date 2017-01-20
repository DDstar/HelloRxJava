package org.ddstar.rxjavaaretrofit.baserxandre;

import org.ddstar.rxjavaaretrofit.databean.ResultData;
import org.ddstar.rxjavaaretrofit.databean.UserInfo;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by DDstar on 2016/12/29.
 */

public class RxReRequest {
    private static String BASEURL = "https://www.github.com/";
    private static RxReService mRxReService;

    public static void initRxRe() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create()).build();
        mRxReService = retrofit.create(RxReService.class);
    }

    //    public static Observable<ResultData<String>> login(String userName, String psd) {
//        return mRxReService.login(userName, psd);
//    }
    public static Observable<ResultData<String>> login(String userName, String psd) {
        return mRxReService.login(userName, psd);
    }

    public static Call<String> loginRetro(String userName, String psd) {
        return mRxReService.login(new HashMap<String, String>());
    }

    //    public static Observable<ResultData<UserInfo>> getUser(String token) {
//        return mRxReService.getUser(token);
//    }
    public static Observable<ResultData<UserInfo>> getUser(String token) {
        return mRxReService.getUser(token);
    }
}
