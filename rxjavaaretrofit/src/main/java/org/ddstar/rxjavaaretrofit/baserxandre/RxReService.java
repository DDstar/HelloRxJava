package org.ddstar.rxjavaaretrofit.baserxandre;

import org.ddstar.rxjavaaretrofit.databean.ResultData;
import org.ddstar.rxjavaaretrofit.databean.UserInfo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by DDstar on 2016/12/29.
 */

public interface RxReService {
    @POST("user/login")
    Observable<ResultData<String>> login(
            @Query("username") String name,
            @Query("password") String psd);

    @POST("token")
    Observable<ResultData<UserInfo>> getUser(@Query("token") String token);

    @POST("user/login")
    Call<String> login(@QueryMap Map<String, String> map);
}
