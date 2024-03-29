package com.yohan.neys.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.yohan.neys.R;
import com.yohan.neys.model.Article;

import java.util.List;

public class ArticlesAdapter extends
        RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {

    private List<Article> articlesList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView articleImageView;
        private TextView articleTitleTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> {
                Intent newIntent = new Intent(v.getContext(), ArticleDetailActivity.class);
                newIntent.putExtra("Article", articlesList.get(getAdapterPosition()));
                v.getContext().startActivity(newIntent);
            });

            articleImageView = itemView.findViewById(R.id.article_image);
            articleTitleTextView = itemView.findViewById(R.id.article_title);
        }
    }

    public ArticlesAdapter(List<Article> articles) {
        articlesList = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View articleView = inflater.inflate(R.layout.item_article, parent, false);

        return new ViewHolder(articleView);
    }

    @Override
    public void onBindViewHolder(ArticlesAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Article article = articlesList.get(position);

        // Set item views based on your views and data model
        ImageView imageView = viewHolder.articleImageView;
        if (article.getUrlToImage() != null && !article.getUrlToImage().equals("")) {
            Picasso.get().load(article.getUrlToImage()).resize(500,500).centerCrop().into(imageView);
        }

        TextView textView = viewHolder.articleTitleTextView;
        textView.setText(article.getTitle());
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }
}
