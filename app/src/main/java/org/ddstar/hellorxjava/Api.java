package org.ddstar.hellorxjava;

import android.net.Uri;

import java.util.List;

/**
 * Created by DDstar on 2016/12/22.
 */

public interface Api {
    //step1 同步
    //api有两个方法 根据url拿到cat的集合
//    List<Cat> queryCats(String query);

    //找到cutness的cat后下载
//    Uri store(Cat cat);


    //step 2 异步
    public interface CatsListQueryCallBack {
        void onCatsListReceived(List<Cat> catList);

        void onQueryFaild(Exception e);
    }

    public interface StoreCatCallBack {
        void onCatstored(Uri uri);

        void onStoreFaild(Exception e);
    }

    void queryCats(String query, CatsListQueryCallBack catsListQueryCallBack);

    void store(Cat cat, StoreCatCallBack storeCatCallBack);
}
