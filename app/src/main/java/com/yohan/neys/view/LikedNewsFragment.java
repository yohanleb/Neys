package com.yohan.neys.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.yohan.neys.R;
import com.yohan.neys.controller.LikedNewsController;
import com.yohan.neys.model.Article;

import java.util.ArrayList;
import java.util.List;

public class LikedNewsFragment extends Fragment {
    private RecyclerView rvArticlesLiked;
    private LikedNewsController controller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_liked_news,
                container, false);

        rvArticlesLiked = view.findViewById(R.id.recycler_view_liked);
        TextView emptyListText = view.findViewById(R.id.empty_list_title_liked);

        controller = new LikedNewsController(this.getActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE));

        ArrayList<Article> articles = controller.getLikedArticles();

        if (articles.isEmpty()) {
            rvArticlesLiked.setVisibility(View.INVISIBLE);
        } else {
            emptyListText.setVisibility(View.INVISIBLE);
            this.refreshList(articles);
        }

        return view;
    }

    public void refreshList (List<Article> articles) {
        ArticlesAdapter adapter = new ArticlesAdapter(articles);
        rvArticlesLiked.setAdapter(adapter);
        rvArticlesLiked.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }
}
