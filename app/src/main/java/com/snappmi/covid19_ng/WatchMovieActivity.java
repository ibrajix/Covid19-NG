package com.snappmi.covid19_ng;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

public class WatchMovieActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_watch_movie);

        web_view = findViewById(R.id.webView);
        progress_bar = findViewById(R.id.progressBar);
        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.setWebViewClient(new WebViewClient());
        web_view.loadUrl("https://www.popcornflix.com");
        loading_text = findViewById(R.id.loadingText);



        /*myWebView  = new WebView(this);

        myWebView.getSettings().setJavaScriptEnabled(true);

        final WatchMovieActivity activity = this;

        myWebView.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                *//*Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();*//*
            }
            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progress_bar.setVisibility(View.GONE);
            }
        });


        myWebView.loadUrl("https://www.popcornflix.com/");
        setContentView(myWebView );*/

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


        /*setContentView(R.layout.activity_watch_movie);*/

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
