package com.yohan.neys;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yohan.neys.data.NewsAPI;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Injection {

    private static final String BASE_URL = "https://newsapi.org/v2/";
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
}
