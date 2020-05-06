package com.example.androidchatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class CovidActivity extends AppCompatActivity {

    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("COVID-19");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        wv = findViewById(R.id.web_view);

        wv.getSettings().setLoadsImagesAutomatically(true);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv.loadUrl("https://hoanggiang521999.github.io");
    }
}
