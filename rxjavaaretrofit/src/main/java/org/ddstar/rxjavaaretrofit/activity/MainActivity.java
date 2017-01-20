package org.ddstar.rxjavaaretrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.ddstar.rxjavaaretrofit.R;
import org.ddstar.rxjavaaretrofit.baseapp.ResultException;
import org.ddstar.rxjavaaretrofit.baseapp.RxReRequestHelper;

import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dataFromNet(View view) {
        RxReRequestHelper.login("token", new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof ResultException) {
                    int errorCode = ((ResultException) e).getErrorCode();
                }

            }

            @Override
            public void onNext(String stringResultData) {

            }
        });
//        Observable.create(new Observable.OnSubscribe<UserInfo>() {
//            @Override
//            public void call(Subscriber<? super UserInfo> subscriber) {
//
//            }
//        }).map(new HttpResultFunc<UserInfo>())
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("").addConverterFactory(GsonConverterFactory.create()).build();
//        final RxService rxService = retrofit.create(RxServlice.class);
//        rxService.login("", "")//获取到Observable对象
//                .subscribeOn(Schedulers.newThread())//设置在其他线程获取数据
////                .observeOn(Schedulers.io())//做其他处理，比如存本地，就另外一个线程
////                .doOnNext(new Action1<UserInfo>() {
////                    @Override
////                    public void call(UserInfo userInfo) {
////                        //数据存本地
////                    }
////                })
//                .observeOn(AndroidSchedulers.mainThread())//在主线程处理数据
//                .subscribe(new Subscriber<UserInfo>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(UserInfo userInfo) {
//
//                    }
//                });

        //先登录，然后获取UserInfo
//        BaseRequest.login("", "")
//                .flatMap(new Func1<String, Observable<ResultData<UserInfo>>>() {
//                    @Override
//                    public Observable<ResultData<UserInfo>> call(String token) {
//                        //更新本地token之类的
//                        return BaseRequest.getUser(token);
//                    }
//                })
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(new HttpResultFunc<UserInfo>())
//                .subscribe(new Subscriber<UserInfo>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        int errorCode = ((ResultException) e).getErrorCode();
//                    }
//
//                    @Override
//                    public void onNext(UserInfo userInfo) {
//
//                    }
//                });
    }

}
