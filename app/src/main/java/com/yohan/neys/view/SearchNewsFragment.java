package com.yohan.neys.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.yohan.neys.Injection;
import com.yohan.neys.R;
import com.yohan.neys.controller.SearchNewsController;
import com.yohan.neys.model.Article;

import java.util.ArrayList;
import java.util.List;

public class SearchNewsFragment extends Fragment {
    private SearchNewsController apiController;
    private RecyclerView rvArticlesSearch;
    private EditText searchEditText;
    private ArrayList<Article> articles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_news,
                container, false);

        searchEditText = view.findViewById(R.id.editTextSearch);

        rvArticlesSearch = view.findViewById(R.id.recycler_view_search);

        apiController = new SearchNewsController(this,
                Injection.getRestApiInstance(),
                this.getActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE));

        articles = apiController.getDataFromCache();
        this.refreshList(articles, false);

        searchEditText.setOnKeyListener((v, keyCode, event) -> {
            // If the event is a key-down event on the "enter" button
            if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                apiController.startLoadSearch(searchEditText.getText().toString());
                articles = apiController.getDataFromCache();
                this.refreshList(articles, false);
                return true;
            }
            return false;
        });

        return view;
    }

    public void refreshList (List<Article> articles, Boolean printSnackBar) {
        ArticlesAdapter adapter = new ArticlesAdapter(articles);
        rvArticlesSearch.setAdapter(adapter);
        rvArticlesSearch.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        /*
        if (printSnackBar) {
            Snackbar.make(findViewById(android.R.id.content), R.string.product_added, Snackbar.LENGTH_SHORT)
                    .show();
        }
        */
    }
}
