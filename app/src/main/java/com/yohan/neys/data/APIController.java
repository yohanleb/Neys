package com.yohan.neys.data;

import android.text.TextUtils;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yohan.neys.model.APIResponse;
import android.content.SharedPreferences;
import com.yohan.neys.model.Article;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class APIController implements Callback<APIResponse> {
    private NewsAPI newsAPI;
    private List<Article> articles;

    public APIController(NewsAPI newsAPI) {
        this.newsAPI = newsAPI;
    }

    public void start() {
        String apiKey = "1d5ff6ded54f46b786ce955bd13ddee2";
        Call<APIResponse> call = newsAPI.loadTopHeadlines(apiKey, "fr");
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
        if(response.isSuccessful()) {
            APIResponse apiResponse = response.body();
            if (apiResponse.getArticles() != null && apiResponse.getStatus().equals("ok")) {
                System.out.println(apiResponse.getArticles().get(0).getContent());
                System.out.println("Call successful");
                /*
                products.add(apiResponse.getProduct());
                storeData(products);
                view.refreshList(products, true);
                */
            } else {
                System.out.println("API Call Unsuccessful");
                //Toast.makeText(view, "Sorry, Product not found...", Toast.LENGTH_LONG).show();
            }
        } else {
            System.out.println("marche pas :(");
            //List<Product> products = getDataFromCache();
            //view.refreshList(products, false);
        }
    }

    @Override
    public void onFailure(Call<APIResponse> call, Throwable t) {
        t.printStackTrace();
    }
}
