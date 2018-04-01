package com.eternallove.mdmp.util;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/3/22 16:27
 */
public class CookieManager implements CookieJar {

    private static List<Cookie> mCookies;


    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if(url.encodedPath().contains("login")){
            CookieManager.mCookies = cookies;
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        if (mCookies == null||url.encodedPath().contains("login")) {
            return new ArrayList<>();
        }
        return mCookies;
    }
}
