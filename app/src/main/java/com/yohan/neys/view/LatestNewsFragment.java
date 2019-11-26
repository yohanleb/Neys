package com.yohan.neys.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.yohan.neys.Injection;
import com.yohan.neys.R;
import com.yohan.neys.controller.LatestNewsController;
import com.yohan.neys.model.Article;

import java.util.ArrayList;
import java.util.List;

public class LatestNewsFragment extends Fragment {
    private LatestNewsController latestNewsController;
    private RecyclerView rvArticles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest_news,
                container, false);

        rvArticles = view.findViewById(R.id.recycler_view);
        TextView emptyListText = view.findViewById(R.id.empty_list_title);

        latestNewsController = new LatestNewsController(this, Injection.getRestApiInstance(), this.getActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE));

        latestNewsController.startLoadTopHeadlines();

        ArrayList<Article> articles = latestNewsController.getDataFromCache();

        if (articles.isEmpty()) {
            rvArticles.setVisibility(View.INVISIBLE);
        } else {
            emptyListText.setVisibility(View.INVISIBLE);
            this.refreshList(articles, false);
        }

        return view;
    }

    public void refreshList (List<Article> articles, Boolean printSnackBar) {
        ArticlesAdapter adapter = new ArticlesAdapter(articles);
        rvArticles.setAdapter(adapter);
        rvArticles.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        /*
        if (printSnackBar) {
            Snackbar.make(findViewById(android.R.id.content), R.string.product_added, Snackbar.LENGTH_SHORT)
                    .show();
        }
        */
    }
}
