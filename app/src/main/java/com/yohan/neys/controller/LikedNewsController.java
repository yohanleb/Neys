package com.yohan.neys.controller;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yohan.neys.model.Article;
import com.yohan.neys.view.LikedNewsFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LikedNewsController {
    private ArrayList<Article> likedArticles;
    private SharedPreferences sharedPreferences;

    public LikedNewsController(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        this.likedArticles = getDataFromCache();
    }

    public void likeDislikeArticle(Article article) {
        if (! likedArticles.contains(article)) {
            likedArticles.add(article);
        } else {
            likedArticles.remove(article);
        }
        storeData(likedArticles);
    }

    public ArrayList<Article> getLikedArticles() {
        return likedArticles;
    }


    private void storeData(List<Article> articles) {
        Gson gson = new Gson();
        String listProductString = gson.toJson(articles);
        sharedPreferences
                .edit()
                .putString("liked_articles", listProductString)
                .apply();
    }

    public ArrayList<Article> getDataFromCache() {
        String listArticlesString = sharedPreferences.getString("liked_articles", "");
        if(!TextUtils.isEmpty(listArticlesString)){
            Type listType = new TypeToken<List<Article>>(){}.getType();
            return new Gson().fromJson(listArticlesString, listType);
        }
        return new ArrayList<>();
    }

    public boolean liked(Article article) {
        return likedArticles.contains(article);
    }
}
