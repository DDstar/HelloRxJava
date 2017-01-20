package org.ddstar.rxjavademo;


import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by DDstar on 2016/12/23.
 */

public class DataRequestUtil {
    static String weatherUrl = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20%3D%202151330&format=json";

    public static String requstData() {
        URL url = null;
        String dataResult = null;
        BufferedReader reader = null;
        InputStream readStream = null;
        try {
            url = new URL(weatherUrl);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setConnectTimeout(3000);
            urlConnection.setReadTimeout(3000);
            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuffer dataBuffer = new StringBuffer();
            String temp = "";
            while (!TextUtils.isEmpty(temp = reader.readLine())) {
                dataBuffer.append(temp);
            }
            dataResult = dataBuffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (readStream != null) {
                try {
                    readStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return dataResult;
    }

    private void str2Json(String resultData) {

    }

    private void postResult() {

    }

}
