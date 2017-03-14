package com.wedevol.smartclass.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.utils.interfaces.Constants;

/** Created by paolo on 3/13/17.*/
public class ViewFileActivity extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_file);
        setElements();
        setActions();
    }

    private void setElements() {
        String url= getIntent().getStringExtra(Constants.BUNDLE_FILE_URL);
        WebView myWebView = (WebView) this.findViewById(R.id.webView);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setBackgroundColor(Color.TRANSPARENT);
        myWebView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + url);
    }

    private void setActions() {
    }
}