package com.yohan.neys;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yohan.neys.controller.NewsAPI;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Injection {

    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static final String API_KEY = "1d5ff6ded54f46b786ce955bd13ddee2";
    private static final String LANGUAGE = "fr";
    private static final String SORT_BY = "publishedAt";
    private static NewsAPI newsAPI;

    public static NewsAPI getRestApiInstance() {
        if (newsAPI == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            newsAPI = retrofit.create(NewsAPI.class);
        }
        return newsAPI;
    }

    public static String getApiKey() {
        return API_KEY;
    }

    public static String getLanguage() {
        return LANGUAGE;
    }

    public static String getSortBy() {
        return SORT_BY;
    }
}
