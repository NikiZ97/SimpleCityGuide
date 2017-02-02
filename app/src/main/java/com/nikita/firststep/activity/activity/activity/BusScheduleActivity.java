package com.nikita.firststep.activity.activity.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import nikita.myappfirststep.R;

public class BusScheduleActivity extends AppCompatActivity {

    private static final String URL = "http://yartr.ru";
    private WebView mWebView;
    private ProgressBar mProgressBar;
    private ImageView mImgHeader;

    @SuppressLint("PrivateResource")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_schedule);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (toolbar != null) {
            toolbar.setTitle("Расписание автобусов");
            toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    back();
                }
            });
        }

        mWebView = (WebView) findViewById(R.id.webView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mImgHeader = (ImageView) findViewById(R.id.backdrop);

        if (!isNetworkStatusAvailable(this)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(BusScheduleActivity.this);
            builder.setTitle("Нет сети")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setMessage("Нет интернет-соединения. Пожалуйста, проверьте " +
                            "подключение к сети и попробуйте снова.")
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
            builder.show();
        }

        initWebView();
        setCollapsingToolbar();
        loadImage();

        mWebView.loadUrl(URL);
    }

    private void back() {
        if (mWebView.canGoBack())
            mWebView.goBack();
        else
            onBackPressed();

    }

    private void loadImage() {
        Glide.with(getApplicationContext()).load(R.drawable.marshrutka)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImgHeader);
    }

    private void setCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the txtPostTitle when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle("Расписание автобусов");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    private void initWebView() {
        // чтобы контролировать процесс загрузки страницы
        mWebView.setWebChromeClient(new WebChromeClient());
        // чтобы взаимодейстовать со страницей, когда она загрузилась
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mProgressBar.setVisibility(View.VISIBLE);
                invalidateOptionsMenu();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mWebView.loadUrl(url);
                return true;
            }

            // если страница загрузилась
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressBar.setVisibility(View.GONE);
                invalidateOptionsMenu();
            }
        });

        mWebView.clearCache(true);
        mWebView.clearHistory();
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setHorizontalScrollBarEnabled(false);
    }

    public static boolean isNetworkStatusAvailable(Context context) {
        ConnectivityManager con = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (con != null) {
            NetworkInfo info = con.getActiveNetworkInfo();
            if (info != null) {
                if (info.isConnected())
                    return true;
            }
        }
        return false;
    }
}
