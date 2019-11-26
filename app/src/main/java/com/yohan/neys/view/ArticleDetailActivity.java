package com.yohan.neys.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.yohan.neys.R;
import com.yohan.neys.model.Article;

public class ArticleDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.overridePendingTransition(R.anim.slide_in_right,
                R.anim.slide_out_left);

        Article article = (Article) getIntent().getSerializableExtra("Article");

        ImageView articleImageView = findViewById(R.id.article_detail_image);
        Picasso.get().load(article.getUrlToImage()).into(articleImageView);

        Toolbar articleSource = findViewById(R.id.toolbar);
        articleSource.setTitle(article.getSource().getName());

        TextView articleTitle = findViewById(R.id.article_detail_title);
        articleTitle.setText(article.getTitle());

        TextView articleDate = findViewById(R.id.article_detail_date);
        articleDate.setText(article.getPublishedAtFormatted());

        TextView articleContent = findViewById(R.id.article_detail_content);
        String content = article.getContent();
        if (content == null) content = article.getDescription();
        articleContent.setText(String.format("%s...", content.split("…")[0]));

        String more = String.format("Plus sur : %s", article.getUrl());
        TextView articleUrl = findViewById(R.id.article_detail_url);
        articleUrl.setText(more);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
            this.overridePendingTransition(R.anim.slide_in_left,
                    R.anim.slide_out_right);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}
