package org.ddstar.hellorxjava;

import android.net.Uri;

import java.util.Collections;
import java.util.List;

/**
 * Created by DDstar on 2016/12/22.
 */

public class CatsHelper {
    Api api;

//    public Uri saveTheCutnessCat(String query) {
//        List<Cat> catList = api.queryCats(query);
//        Cat cutest = findCutest(catList);
//        return api.store(cutest);
//    }

    private Cat findCutest(List<Cat> catList) {
        return Collections.max(catList);
    }
    //step 2 异步

    public interface CutestCatCallBack {
        void onCutestCatSaved(Uri uri);

        void onError(Exception e);
    }

    //    public void saveTheCutnessCat(String query, final CutestCatCallBack cutestCatCallBack) {
//        api.queryCats(query, new Api.CatsListQueryCallBack() {
//            @Override
//            public void onCatsListReceived(List<Cat> catList) {
//                Cat cutest = findCutest(catList);
//                api.store(cutest, new Api.StoreCatCallBack() {
//                    @Override
//                    public void onCatstored(Uri uri) {
//                        cutestCatCallBack.onCutestCatSaved(uri);
//                    }
//
//                    @Override
//                    public void onStoreFaild(Exception e) {
//                        cutestCatCallBack.onError(e);
//                    }
//                });
//
//            }
//
//            @Override
//            public void onQueryFaild(Exception e) {
//                cutestCatCallBack.onError(e);
//            }
//        });
//
//    }
    //step3 包装类
    ApiWrapper apiWrapper;

//    public void saveTheCutnessCat(String query, final CallBack<Uri> uriCallBack) {
//        apiWrapper.queryCats(query, new CallBack<List<Cat>>() {
//            @Override
//            public void onResult(List<Cat> result) {
//                Cat cutest = findCutest(result);
//                apiWrapper.store(cutest, uriCallBack);
//            }
//
//            @Override
//            public void onError(Exception e) {
//                uriCallBack.onError(e);
//            }
//        });
//    }
    //step4 AsyncJob

//    public AsyncJob<Uri> saveTheCutnessCat(final String query) {
//        return new AsyncJob<Uri>() {
//            @Override
//            public void start(final CallBack<Uri> callBack) {
//                apiWrapper.queryCats(query).start(new CallBack<List<Cat>>() {
//                    @Override
//                    public void onResult(List<Cat> result) {
//                        Cat cutest = findCutest(result);
//                        apiWrapper.store(cutest).start(new CallBack<Uri>() {
//                            @Override
//                            public void onResult(Uri result) {
//                                callBack.onResult(result);
//                            }
//
//                            @Override
//                            public void onError(Exception e) {
//                                callBack.onError(e);
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//                        callBack.onError(e);
//                    }
//                });
//            }
//        };
//    }

    //step 5拆
//    public AsyncJob<Uri> saveTheCutnessCat(String query) {
//        final AsyncJob<List<Cat>> listAsyncJob = apiWrapper.queryCats(query);
//        final AsyncJob<Cat> cutestCatAsyncJob = new AsyncJob<Cat>() {
//            @Override
//            public void start(final CallBack<Cat> callBack) {
//                listAsyncJob.start(new CallBack<List<Cat>>() {
//                    @Override
//                    public void onResult(List<Cat> result) {
//                        Cat cutest = findCutest(result);
//                        callBack.onResult(cutest);
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//                        callBack.onError(e);
//                    }
//                });
//            }
//        };
//        AsyncJob<Uri> storeUriAsyncJob = new AsyncJob<Uri>() {
//            @Override
//            public void start(final CallBack<Uri> cutestCallBack) {
//                cutestCatAsyncJob.start(new CallBack<Cat>() {
//                    @Override
//                    public void onResult(Cat result) {
//                        apiWrapper.store(result).start(new CallBack<Uri>() {
//                            @Override
//                            public void onResult(Uri result) {
//                                cutestCallBack.onResult(result);
//                            }
//
//                            @Override
//                            public void onError(Exception e) {
//                                cutestCallBack.onError(e);
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//                              cutestCallBack.onError(e);
//                    }
//                });
//            }
//        };
//        return storeUriAsyncJob;
//    }
    //step6 参数转换 func<T,R>
    public AsyncJob<Uri> saveTheCutnessCat(String query) {
        final AsyncJob<List<Cat>> catsListAsyncJob = apiWrapper.queryCats(query);
        final AsyncJob<Cat> cutestCatAsyncJob = catsListAsyncJob.map(new Func<List<Cat>, Cat>() {
            @Override
            public Cat call(List<Cat> catList) {
                return findCutest(catList);
            }
        });
        //lambda语法
//        final AsyncJob<Cat> cutestCatAsyncJob = catsListAsyncJob.map(catList->findCutest(catList));

//        AsyncJob<Uri> storeAsyncjob = new AsyncJob<Uri>() {
//            @Override
//            public void start(final CallBack<Uri> callBack) {
//                cutestCatAsyncJob.start(new CallBack<Cat>() {
//                    @Override
//                    public void onResult(Cat result) {
//                        apiWrapper.store(result).start(new CallBack<Uri>() {
//                            @Override
//                            public void onResult(Uri result) {
//                                callBack.onResult(result);
//                            }
//
//                            @Override
//                            public void onError(Exception e) {
//                                callBack.onError(e);
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//                        callBack.onError(e);
//                    }
//                });
//            }
//        };
//        AsyncJob<AsyncJob<Uri>> storeAsyncjob =cutestCatAsyncJob.map(new Func<Cat, AsyncJob<Uri>>() {
//            @Override
//            public AsyncJob<Uri> call(Cat cat) {
//                AsyncJob<Uri> store = apiWrapper.store(cat);
//                return null;
//            }
//        });
        //step7flatMap
        AsyncJob<Uri> storeAsyncjob = cutestCatAsyncJob.flatMap(new Func<Cat, AsyncJob<Uri>>() {
            @Override
            public AsyncJob<Uri> call(Cat cat) {

                return apiWrapper.store(cat);
            }
        });
        //lambda语法
//        AsyncJob<Uri> storeAsyncjob = cutestCatAsyncJob.flatMap(cat->apiWrapper.store(cat));
        return storeAsyncjob;
    }

}
