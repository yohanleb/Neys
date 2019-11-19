package com.yohan.neys.data;

import com.yohan.neys.model.APIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPI {
    @GET("top-headlines")
    Call<APIResponse> loadTopHeadlines(@Query("apiKey") String apiKey, @Query("country") String country);

    @GET("everything")
    Call<APIResponse> loadEverything(@Query("apiKey") String apiKey);

    @GET("sources")
    Call<APIResponse> loadSources(@Query("apiKey") String apiKey);
}
