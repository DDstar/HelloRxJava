package org.ddstar.rxjavaaretrofit.baseapp;

import android.app.Application;

import org.ddstar.rxjavaaretrofit.baserxandre.RxReRequest;

/**
 * Created by DDstar on 2017/1/18.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RxReRequest.initRxRe();
    }
}
