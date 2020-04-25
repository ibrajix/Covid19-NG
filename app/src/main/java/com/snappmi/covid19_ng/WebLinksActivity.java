package com.snappmi.covid19_ng;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class WebLinksActivity extends AppCompatActivity {

    private WebView web_view, myWebView;
    private ProgressBar progress_bar;
    private TextView loading_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            Window w = getWindow();
            w.setStatusBarColor(Color.TRANSPARENT);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_links);

        String url  = getIntent().getStringExtra("link_url");

        web_view = findViewById(R.id.webView);
        progress_bar = findViewById(R.id.progressBar);
        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.setWebViewClient(new WebLinksActivity.WebViewClient());
        web_view.loadUrl(url);
        loading_text = findViewById(R.id.loadingText);

        web_view.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if(progress < 100 && progress_bar.getVisibility() == ProgressBar.GONE){
                    progress_bar.setVisibility(ProgressBar.VISIBLE);
                    loading_text.setVisibility(View.VISIBLE);
                }

                progress_bar.setProgress(progress);
                if(progress == 100) {
                    progress_bar.setVisibility(ProgressBar.GONE);
                    loading_text.setVisibility(View.GONE);
                }
            }
        });

    }

    public class WebViewClient extends android.webkit.WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progress_bar.setVisibility(View.GONE);
        }
    }

}

