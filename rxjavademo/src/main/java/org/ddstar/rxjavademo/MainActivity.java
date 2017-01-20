package org.ddstar.rxjavademo;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {
    private CompositeSubscription mCom;
    private TextView tvResult;
    private ImageView animaImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = (TextView) findViewById(R.id.textView);
        animaImage = (ImageView) findViewById(R.id.anima_image);
        animaImage.setOnClickListener(v -> {

        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        ((AnimationDrawable) animaImage.getBackground()).start();
    }

    private void rxPostMsg() {
        if (RxBus.getInstance().hasObservers()) {
            RxBus.getInstance().post(new RxPostMsg("小东", 12));
        }
    }


    public void postMsg(View view) {
        RxBus rxBus = RxBus.getInstance();
        if (rxBus.hasObservers()){
        }
//        Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                String s = DataRequestUtil.requstData();
//                subscriber.onNext(s);
//                subscriber.onCompleted();
//            }
//        })
//                .subscribeOn(Schedulers.newThread())//被观察者发生的线程
//                .observeOn(AndroidSchedulers.mainThread())//观察者发生的线程
////                .subscribeOn(AndroidSchedulers.mainThread())//被观察者发生的线程
////                .observeOn(Schedulers.newThread())//观察者发生的线程
//                .map(s -> new Gson().fromJson(s, WeatherData.class))
//                .subscribe(weatherData -> {
//                    tvResult.setText(weatherData.getQuery().getResults().getChannel().getTitle());
//                });
//        rxPostMsg();
    }

    public void post2Msg(View view) {
        rxPost2Msg();
    }

    private void rxPost2Msg() {
        if (RxBus.getInstance().hasObservers()) {
            RxBus.getInstance().post("fuck~~");
        }
    }

    private void reqisterObservers() {
        Subscription subscribe = RxBus.getInstance()
                .toObserverable(RxPostMsg.class)
                .subscribe(msg -> {
                    tvResult.setText(msg.getName());
                });
        add2Com(subscribe);
    }

    private void add2Com(Subscription subscriptions) {
        if (mCom == null) {
            mCom = new CompositeSubscription();
        }
        mCom.add(subscriptions);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCom != null) {
            mCom.unsubscribe();
        }
    }
}
