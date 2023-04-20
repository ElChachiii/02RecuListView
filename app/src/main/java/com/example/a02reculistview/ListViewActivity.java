package com.example.a02reculistview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    ListView lista;
    ArrayAdapter<String> adapter;
    ArrayList<String> links = new ArrayList<>();
    WebView web;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        lista = findViewById(R.id.lista);
        web = findViewById(R.id.web);

        links.add("https://www.joyfe.es");
        links.add("https://iepjoyfe.clickedu.eu/user.php?action=login");
        links.add("https://www.classroom.google.com");
        links.add("https://www.twitter.com");
        links.add("https://www.facebook.com");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, links);
        lista.setAdapter(adapter);

        web.getSettings().setJavaScriptEnabled(true);

        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // Abre los enlaces dentro del WebView en lugar de utilizar el navegador del dispositivo
                view.loadUrl(url);
                return true;
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String link = links.get(position);
                lista.setVisibility(View.INVISIBLE);
                web.setVisibility(View.VISIBLE);
                web.loadUrl(link);
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (web.getVisibility() == View.VISIBLE) {
            lista.setVisibility(View.VISIBLE);
            web.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }
}


