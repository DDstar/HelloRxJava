package org.ddstar.retrofitdemo;


import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by DDstar on 2016/12/28.
 */

public interface GithubServices {
    @GET("adat/sk/{cityId}.html")
    <T> Call<T> getWeather(T t,@Path("cityId") String cityId);

    @GET("users/{user}/repos")
    Call<List<Respond>> listRepos(@Path("user") String user);

    @GET("/")
    Call<Respond> listAndroid(@Query("cate") String cate);

    @GET("/")
    Call<ResponseBody> listAndroid(@QueryMap Map<String, String> map);

    @GET("/")
    Call<ResponseBody> listAndroid(
            @Query("cat") String cat,
            @QueryMap Map<String, String> map);

    @POST("/")
    Call<ResponseBody> login(@Field("name") String name);

    @POST("/")
    Call<ResponseBody> changePsd(
            @Field("oldPsd") String oldPsd,
            @Field("newPsd") String newPsd);

    @POST("/")
    Call<ResponseBody> changePsd(@FieldMap Map<String, String> padMap);

}
