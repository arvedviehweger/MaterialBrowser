package com.arved95.materialbrowser;


import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.graphics.Bitmap;
import android.widget.ZoomButtonsController;


import java.lang.reflect.Method;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    WebView ourbrowser;
    EditText URL;
    Button go;
    ProgressBar progressBar;
    Bitmap favicon;
    Button actionsettings;
    Button frwbtn;
    Button rfbtn;
    private ZoomButtonsController zoom_controll = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ourbrowser = (WebView) findViewById(R.id.webView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        ourbrowser.getSettings().setJavaScriptEnabled(true);
        ourbrowser.getSettings().setLoadWithOverviewMode(true);
        ourbrowser.getSettings().setUseWideViewPort(true);
        ourbrowser.restoreState(savedInstanceState);
        ourbrowser.setWebViewClient(new client1());


        try {
            ourbrowser.loadUrl("http://google.com/");
        } catch (Exception e) {
            e.printStackTrace();
        }

        go = (Button) findViewById(R.id.go_btn);
        URL = (EditText) findViewById(R.id.EditText);
        rfbtn = (Button) findViewById(R.id.rfbutton);
        frwbtn = (Button) findViewById(R.id.forwardbtn);


        actionsettings = (Button) findViewById(R.id.action_settings);

        go.setOnClickListener(this);
        frwbtn.setOnClickListener(this);
        rfbtn.setOnClickListener(this);

        ourbrowser.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);

            }
        });

    }


    private void setDisplayZoomControls(boolean enabled) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_btn:
                String theWebsite = new String("http://");
                theWebsite = URL.getText().toString();
                ourbrowser.loadUrl("http://" + theWebsite);
                break;
            case R.id.forwardbtn:
                if (ourbrowser.canGoForward()) ;
                ourbrowser.goForward();
                break;
            case R.id.rfbutton:
                ourbrowser.reload();
                break;


        }


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (ourbrowser.canGoBack()) {
                        ourbrowser.goBack();
                    } else {
                        finish();
                    }
            }
        }

        return true;
    }


    public class client1 extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);
            URL.setText(url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
            URL.setText(url);
        }


    }

}


















