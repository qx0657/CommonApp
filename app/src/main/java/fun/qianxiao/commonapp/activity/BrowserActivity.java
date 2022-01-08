package fun.qianxiao.commonapp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;

import com.orhanobut.logger.Logger;

import fun.qianxiao.commonapp.R;
import fun.qianxiao.commonapp.base.BaseActivity;
import fun.qianxiao.commonapp.databinding.ActivityBrowserBinding;

/**
 * TODO
 *
 * @Author QianXiao
 * @Date 2021/12/25
 */
public class BrowserActivity extends BaseActivity<ActivityBrowserBinding> {
    private String url;

    public static void load(Context context, String url) {
        Intent intent = new Intent(context, BrowserActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        setTitle("网页");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        initWebView();

        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra("url");
            String title = intent.getStringExtra("title");

            if (!TextUtils.isEmpty(title)) {
                setTitle(title);
            }
        }
        if (!TextUtils.isEmpty(url)) {
            binding.webView.loadUrl(url);
        }
    }

    private void initWebView() {
        WebSettings webSettings = binding.webView.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        // 音视频自动播放
        webSettings.setMediaPlaybackRequiresUserGesture(false);

        binding.webView.setWebChromeClient(new WebChromeClient() {
            final int PROGRESS_DONE = 100;

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                binding.progressBar.setProgress(newProgress);
            }
        });
        binding.webView.setWebViewClient(new WebViewClient() {
            final String HTTP_PREFIX = "http";

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                if (url.startsWith(HTTP_PREFIX)) {
                    view.loadUrl(url);
                }
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                binding.progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                binding.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Logger.e("onReceivedError:\n" +
                        "code: %d\n" +
                        "description: %s", error.getErrorCode(), error.getDescription());
                /*binding.llErrorBrowser.setVisibility(View.VISIBLE);
                binding.tvErrorCodeBrowser.setText(String.valueOf(error.getErrorCode()));
                binding.tvErrorDescriptionBrowser.setText(error.getDescription());
                binding.webView.setVisibility(View.GONE);*/
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_browser, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.webView.destroy();
    }

    @Override
    public void onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.menu_item_refresh_browser) {
            binding.webView.clearCache(true);
            binding.webView.reload();
        }
        return super.onOptionsItemSelected(item);
    }
}
