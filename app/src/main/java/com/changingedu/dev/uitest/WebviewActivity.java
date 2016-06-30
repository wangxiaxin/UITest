package com.changingedu.dev.uitest;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by wangxiaxin on 2016/6/17.
 */
public class WebviewActivity extends AppCompatActivity {

    private static final String TAG = "WebviewActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        
        WebView webview = (WebView) findViewById(R.id.wv_webview);
        webview.getSettings().setJavaScriptEnabled(true);// 默认支持JS
        
        // webview.loadUrl("http://www.baidu.com");
        webview.loadUrl("http://m.changingedu.com");
        webview.setWebViewClient(new DefaultClient());
        
    }
    
    public class DefaultClient extends WebViewClient {
        
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i(TAG,"shouldOverrideUrlLoading   " +  url);
            return url.contains("getBack.do")
                    || super.shouldOverrideUrlLoading(view, url);
        }
        
        @Override
        public void onReceivedError(WebView view, int errorCode, String description,
                String failingUrl) {
            Log.i(TAG,"onReceivedError   " +  failingUrl);
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
        
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.i(TAG,"onPageStarted   " +  url);
            super.onPageStarted(view, url, favicon);
        }
        
        @Override
        public void onPageFinished(WebView view, String url) {
            Log.i(TAG,"onPageFinished   " +  url);
            super.onPageFinished(view, url);
        }
        
    }
}
