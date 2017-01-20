package org.ddstar.rxjavademo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by DDstar on 2016/12/23.
 */

public class RxUtils {
    Context context;

    public static void testRx() {
        Observer observer = new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onStart() {
                super.onStart();
            }


            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
//                unsubscribe();
            }

            @Override
            public void onNext(String s) {

            }
        };
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("");
                subscriber.onNext("");
                subscriber.onNext("");
                subscriber.onCompleted();
                subscriber.unsubscribe();
            }
        });
        Observable<String> just = Observable.just("", "", "");
        Observable<String> from = Observable.from(new String[]{"", "", ""});
        from.subscribe(observer);
        just.subscribe(subscriber);
//        observable.map().filter().subscribeOn().subscribe(subscriber);
        Action1<String> onNext = new Action1<String>() {
            @Override
            public void call(String s) {

            }
        };
        Action1<Throwable> onErr = new Action1<Throwable>() {
            @Override
            public void call(Throwable e) {

            }
        };
        Action0 onComplete = new Action0() {
            @Override
            public void call() {

            }
        };
        observable.subscribe(onNext, onErr, onComplete);//自定义action
    }

    public void gogogo() {
        //给了图片的id，去获取图片，然后显示,此处拿id到转化成bitmap只有一处是耗时的
        final Integer[] drawRes = {1, 2, 3, 3, 3, 2, 1, 5, 6, 5};
        Observable.from(drawRes).map(new Func1<Integer, Drawable>() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public Drawable call(Integer integer) {
                return context.getTheme().getDrawable(integer);
            }
        }).subscribe(new Subscriber<Drawable>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Drawable drawable) {
//显示图片
            }
        });
        //给了图片的url，要去请求网络拿到图片的stream，再转成bitmap，再显示
        //此处从url到bitmap有两处是耗时的
        String[] drawUrl = new String[]{"", "", "", ""};
        Observable.from(drawUrl).flatMap(new Func1<String, Observable<InputStream>>() {
            @Override
            public Observable<InputStream> call(String s) {
                InputStream inputStream = null;
                try {
                    URL drawStream = new URL(s);
                    URLConnection urlConnection = drawStream.openConnection();
                    urlConnection.setReadTimeout(3000);
                    urlConnection.setConnectTimeout(3000);
                    inputStream = urlConnection.getInputStream();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return Observable.just(inputStream);
            }
        }).subscribe(new Action1<InputStream>() {
            @Override
            public void call(InputStream inputStream) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                //设置图片
                //https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20%3D%202151330&format=json
            }
        });

    }
}
