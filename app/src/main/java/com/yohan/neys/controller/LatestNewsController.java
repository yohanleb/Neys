package com.yohan.neys.controller;

import android.text.TextUtils;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yohan.neys.Injection;
import com.yohan.neys.model.APIResponse;
import android.content.SharedPreferences;
import com.yohan.neys.model.Article;
import com.yohan.neys.view.LatestNewsFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LatestNewsController implements Callback<APIResponse> {
    private LatestNewsFragment view;
    private NewsAPI newsAPI;
    private SharedPreferences sharedPreferences;
    private List<Article> articles;

    public LatestNewsController(LatestNewsFragment view, NewsAPI newsAPI, SharedPreferences sharedPreferences) {
        this.view = view;
        this.newsAPI = newsAPI;
        this.sharedPreferences = sharedPreferences;
        this.articles = getDataFromCache();
    }

    public void startLoadTopHeadlines() {
        Call<APIResponse> call = newsAPI.loadTopHeadlines(Injection.getApiKey(), Injection.getLanguage(), 10);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
        if(response.isSuccessful()) {
            APIResponse apiResponse = response.body();
            if (apiResponse.getArticles() != null && apiResponse.getStatus().equals("ok")) {
                articles = apiResponse.getArticles();
                storeData(articles);
                view.refreshList(articles, false);
            } else {
                System.out.println("API Call Unsuccessful");
                Toast.makeText(view.getActivity(), "Sorry, no articles found...", Toast.LENGTH_LONG).show();
            }
        } else {
            System.out.println("API Call Unsuccessful");
            List<Article> articles = getDataFromCache();
        }
    }

    @Override
    public void onFailure(Call<APIResponse> call, Throwable t) {
        t.printStackTrace();
    }

    private void storeData(List<Article> products) {
        Gson gson = new Gson();
        String listProductString = gson.toJson(products);
        sharedPreferences
                .edit()
                .putString("articles_latest_list", listProductString)
                .apply();
    }

    public ArrayList<Article> getDataFromCache() {
        String listArticlesString = sharedPreferences.getString("articles_latest_list", "");
        if(!TextUtils.isEmpty(listArticlesString)){
            Type listType = new TypeToken<List<Article>>(){}.getType();
            return new Gson().fromJson(listArticlesString, listType);
        }
        return new ArrayList<>();
    }
}
