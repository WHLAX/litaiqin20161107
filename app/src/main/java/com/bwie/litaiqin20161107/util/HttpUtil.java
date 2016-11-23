package com.bwie.litaiqin20161107.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by w9072 on 2016/11/7.
 */

public class HttpUtil {
    public static String getjsonData(String url) throws Exception {
        URL Url = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) Url.openConnection();
        InputStream is = connection.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String len = "";
        StringBuffer sb = new StringBuffer();
        while ((len = br.readLine()) != null) {
            sb.append(len);
        }
        String json = sb.toString();
        br.close();
        isr.close();
        is.close();
        return json;
    }
}
