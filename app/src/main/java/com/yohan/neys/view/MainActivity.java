package com.yohan.neys.view;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import com.yohan.neys.R;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();
        // FIXME: Hack to change color of Action Bar title
        toolbar.setTitle(Html.fromHtml("<font color='#000000'>Latest Headlines</font>"));

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        this.configureBottomView();

        loadFragment(new LatestNewsFragment());
    }

    private void configureBottomView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> updateMainFragment(item.getItemId()));
    }

    private Boolean updateMainFragment(Integer integer){
        switch (integer) {
            case R.id.action_latest:
                loadFragment(new LatestNewsFragment());
                // FIXME: Hack to change color of Action Bar title
                toolbar.setTitle(Html.fromHtml("<font color='#000000'>Latest Headlines</font>"));
                break;
            case R.id.action_search:
                loadFragment(new SearchNewsFragment());
                // FIXME: Hack to change color of Action Bar title
                toolbar.setTitle(Html.fromHtml("<font color='#000000'>Search News</font>"));
                break;
            case R.id.action_like:
                loadFragment(new LikedNewsFragment());
                // FIXME: Hack to change color of Action Bar title
                toolbar.setTitle(Html.fromHtml("<font color='#000000'>Liked News</font>"));
                break;
        }
        return true;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
