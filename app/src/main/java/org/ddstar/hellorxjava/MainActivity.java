package org.ddstar.hellorxjava;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void requestCuestCat(String catsUrl) {
        CatsHelper catsHelper = new CatsHelper();
//      step1
        //此uri即猫的图片
//        Uri uri = catsHelper.saveTheCutnessCat(catsUrl);
//step2 使用异步
//        catsHelper.saveTheCutnessCat(catsUrl, new CatsHelper.CutestCatCallBack() {
//            @Override
//            public void onCutestCatSaved(Uri uri) {
//                //此uri即猫的图片
//            }
//
//            @Override
//            public void onError(Exception e) {
//
//            }
//        });
        //step3 使用包装类
//        catsHelper.saveTheCutnessCat(catsUrl, new CallBack<Uri>() {
//            @Override
//            public void onResult(Uri result) {
//                //此uri即猫的图片
//            }
//
//            @Override
//            public void onError(Exception e) {
//
//            }
//        });
        //step4
        catsHelper.saveTheCutnessCat(catsUrl).start(new CallBack<Uri>() {
            @Override
            public void onResult(Uri result) {
                //此uri即猫的图片
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
}
