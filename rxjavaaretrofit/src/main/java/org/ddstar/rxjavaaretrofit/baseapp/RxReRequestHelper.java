package org.ddstar.rxjavaaretrofit.baseapp;

import org.ddstar.rxjavaaretrofit.baserxandre.HttpResultFunc;
import org.ddstar.rxjavaaretrofit.baserxandre.RxReRequest;
import org.ddstar.rxjavaaretrofit.databean.ResultData;
import org.ddstar.rxjavaaretrofit.databean.UserInfo;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by DDstar on 2017/1/18.
 */

public class RxReRequestHelper {
    //单个请求的情况，就是一步一步来
//    public static void getUser(String token, Subscriber<ResultData<UserInfo>> subscriber) {
//        RxReRequest.getUser(token)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
    //修改为返回结果预处理机制
    public void getUser(String token, Subscriber<UserInfo> subscriber) {
        RxReRequest.getUser(token)
                .map(new HttpResultFunc<UserInfo>())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

//    //一般请求
//    public static void login(String userName, String psd, Subscriber<ResultData<String>> subscriber) {
//        RxReRequest.login(userName, psd)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }

    //结果预处理
    public static void login(String userName, String psd, Subscriber<String> subscriber) {
        RxReRequest.login(userName, psd)
                .map(new HttpResultFunc<String>())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static void loginRetro() {
        RxReRequest.loginRetro("", "").cancel();
    }

    //连锁请求 先通过token获得用户名和密码以后才能登陆
    public static void login(String token, Subscriber<String> subscriber) {
        RxReRequest.getUser(token).flatMap(new Func1<ResultData<UserInfo>, Observable<ResultData<String>>>() {
            @Override
            public Observable<ResultData<String>> call(ResultData<UserInfo> userInfoResultData) {
                UserInfo userInfo = userInfoResultData.getData();
                return RxReRequest.login(userInfo.getName(), userInfo.getPsd());
            }
        })
                .map(new HttpResultFunc<String>())//机智地进行了转型判断
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
