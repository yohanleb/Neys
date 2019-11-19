package com.yohan.neys.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.yohan.neys.Injection;
import com.yohan.neys.R;
import com.yohan.neys.data.APIController;
import com.yohan.neys.model.Article;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private APIController apiController;
    private RecyclerView rvArticles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvArticles = findViewById(R.id.recycler_view);
        TextView emptyListText = findViewById(R.id.empty_list_title);

        apiController = new APIController(this, Injection.getRestApiInstance(), this.getSharedPreferences("user_prefs", Context.MODE_PRIVATE));

        apiController.start();

        ArrayList<Article> articles = apiController.getDataFromCache();
        System.out.println(articles);

        if (articles.isEmpty()) {
            rvArticles.setVisibility(View.INVISIBLE);
        } else {
            emptyListText.setVisibility(View.INVISIBLE);
            this.refreshList(articles, false);
        }

    }

    public void refreshList (List<Article> articles, Boolean printSnackBar) {
        ArticlesAdapter adapter = new ArticlesAdapter(articles);
        rvArticles.setAdapter(adapter);
        rvArticles.setLayoutManager(new LinearLayoutManager(this));
        /*
        if (printSnackBar) {
            Snackbar.make(findViewById(android.R.id.content), R.string.product_added, Snackbar.LENGTH_SHORT)
                    .show();
        }
        */
    }
}
