package org.ddstar.hellorxjava;

import android.net.Uri;

import java.util.List;

/**
 * Created by DDstar on 2016/12/22.
 */

public class ApiWrapper {
    Api api;

    //    public void queryCats(String query, final CallBack<List<Cat>> catsListCallBack) {
//        api.queryCats(query, new Api.CatsListQueryCallBack() {
//            @Override
//            public void onCatsListReceived(List<Cat> catList) {
//                catsListCallBack.onResult(catList);
//            }
//
//            @Override
//            public void onQueryFaild(Exception e) {
//                catsListCallBack.onError(e);
//            }
//        });
//    }
//
//    public void store(Cat cat, final CallBack<Uri> uriCallBack) {
//        api.store(cat, new Api.StoreCatCallBack() {
//            @Override
//            public void onCatstored(Uri uri) {
//                uriCallBack.onResult(uri);
//            }
//
//            @Override
//            public void onStoreFaild(Exception e) {
//                uriCallBack.onError(e);
//            }
//        });
//    }

    //step4 AsyncJob
    public AsyncJob<List<Cat>> queryCats(final String query) {
        return new AsyncJob<List<Cat>>() {
            @Override
            public void start(final CallBack<List<Cat>> callBack) {
                api.queryCats(query, new Api.CatsListQueryCallBack() {
                    @Override
                    public void onCatsListReceived(List<Cat> catList) {
                        callBack.onResult(catList);
                    }

                    @Override
                    public void onQueryFaild(Exception e) {
                        callBack.onError(e);
                    }
                });
            }
        };
    }


    public AsyncJob<Uri> store(final Cat cat) {
        return new AsyncJob<Uri>() {
            @Override
            public void start(final CallBack<Uri> callBack) {
                api.store(cat, new Api.StoreCatCallBack() {
                    @Override
                    public void onCatstored(Uri uri) {
                        callBack.onResult(uri);
                    }

                    @Override
                    public void onStoreFaild(Exception e) {
                        callBack.onError(e);
                    }
                });
            }
        };
    }


}
