package com.yohan.neys.data;

import com.yohan.neys.model.APIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NewsAPI {
    @GET("top-headlines")
    Call<APIResponse> loadTopHeadlines(@Path("apiKey") String apiKey);

    @GET("everything")
    Call<APIResponse> loadEverything(@Path("apiKey") String apiKey);

    @GET("sources")
    Call<APIResponse> loadSources(@Path("apiKey") String apiKey);
}
