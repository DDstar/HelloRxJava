package org.ddstar.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = (TextView) findViewById(R.id.textView);
    }

    public void retroRequest(View view) {

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://www.println.net/")
//                .build();
//        GithubServices services = retrofit.create(GithubServices.class);
//        Call<Respond> listAndroid = services.listAndroid("android");
//        listAndroid.enqueue(new Callback<Respond>() {
//            @Override
//            public void onResponse(Call<Respond> call, Response<Respond> response) {
//                Respond body = response.body();
//                tvResult.setText("拿到数据了 =》" + body.getQuery().getCreated());
//            }
//
//            @Override
//            public void onFailure(Call<Respond> call, Throwable t) {
//                tvResult.setText("狗 待");
//            }
//        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GithubServices githubServices = retrofit.create(GithubServices.class);
        Call<List<Respond>> dDstar = githubServices.listRepos("DDstar");
        dDstar.enqueue(new Callback<List<Respond>>() {
            @Override
            public void onResponse(Call<List<Respond>> call, Response<List<Respond>> response) {
                tvResult.setText("拿到数据了 =》" + response.body().get(1).getName());
            }


            @Override
            public void onFailure(Call<List<Respond>> call, Throwable t) {
                tvResult.setText("狗 待");
            }
        });
    }
//    public void retroRequest(View view) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://www.weather.com.cn/")
//                .build();
//        GithubServices githubServices = retrofit.create(GithubServices.class);
//        Call<ResponseBody> weather = githubServices.getWeather("101010100");
//        weather.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                tvResult.setText("拿到数据了 =》" + response.body());
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                tvResult.setText("狗 待");
//            }
//        });
//    }
}
