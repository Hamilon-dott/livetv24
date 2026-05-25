package com.livetv24.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private WebView webView;
    private ProgressBar progressBar;
    private static final String WEBSITE_URL = "https://livetv24.vercel.app/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fullscreen for TV
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_FULLSCREEN |
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );

        // Build layout programmatically
        RelativeLayout layout = new RelativeLayout(this);
        layout.setBackgroundColor(0xFF000000);

        // Loading screen
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);
        RelativeLayout.LayoutParams pbParams = new RelativeLayout.LayoutParams(150, 150);
        pbParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        progressBar.setLayoutParams(pbParams);
        layout.addView(progressBar);

        // Loading text
        TextView loadingText = new TextView(this);
        loadingText.setText("LiveTV24 লোড হচ্ছে...");
        loadingText.setTextColor(0xFF00BCD4);
        loadingText.setTextSize(18);
        RelativeLayout.LayoutParams tvParams = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        tvParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        tvParams.addRule(RelativeLayout.BELOW, progressBar.getId());
        tvParams.topMargin = 20;
        loadingText.setLayoutParams(tvParams);
        progressBar.setId(View.generateViewId());
        tvParams.addRule(RelativeLayout.BELOW, progressBar.getId());
        layout.addView(loadingText);

        // WebView
        webView = new WebView(this);
        webView.setVisibility(View.INVISIBLE);
        RelativeLayout.LayoutParams wvParams = new RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        );
        webView.setLayoutParams(wvParams);

        // WebView settings
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        settings.setUserAgentString(
            "Mozilla/5.0 (Linux; Android 11; AndroidTV) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) " +
            "Chrome/120.0.0.0 Safari/537.36"
        );

        // WebView client
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                loadingText.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request,
                                        WebResourceError error) {
                // Show error but keep loading
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        layout.addView(webView);
        setContentView(layout);

        // Load website
        webView.loadUrl(WEBSITE_URL);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Handle remote control navigation
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    protected void onDestroy() {
        webView.destroy();
        super.onDestroy();
    }
}
