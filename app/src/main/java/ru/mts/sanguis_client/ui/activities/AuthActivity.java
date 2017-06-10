package ru.mts.sanguis_client.ui.activities;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.Map;

import ru.mts.sanguis_client.R;

public class AuthActivity extends AppCompatActivity {

    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        WebView authWebview = (WebView) findViewById(R.id.webView);

        WebSettings settings = authWebview.getSettings();
        settings.setJavaScriptEnabled(true);
        authWebview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        progressBar = ProgressDialog.show(AuthActivity.this, "МТС Авторизация", "Загрузка...");

        authWebview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(String url) {
                Log.i("auth", "Processing webview url click...");
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                Log.i("auth", "Finished loading URL: " + url);
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }

            public WebResourceResponse shouldInterceptRequest(final WebView view, final WebResourceRequest request) {
                for (Map.Entry<String, String> entry: request.getRequestHeaders().entrySet()) {
                    Log.i("auth_header", entry.getKey() + ": " + entry.getValue());
                }
                return super.shouldInterceptRequest(view, request);
            }
        });


        authWebview.loadUrl("https://login.mts.ru/amserver/oauth2/auth?client_id=pl1hv5efstq5glopiq1voctdfa4m23ehlk0gqgeklsp45mobghs8k3944elsmq6x@app.b2b.mts.ru&client_password=xJaC6z055qM75X8RFuqvROMwjuYnspUCPi8f5PWoP5kRRjL1yc1eCGh6BN6sLu9n6XLzyjVouwQGjbZuj2JiaLLeHrNGXfx0Xoo&scope=openid%20profile%20mobile&redirect_uri=http s%3A%2F%2Fapp.domain.ru%2Foauth2%2Fcallback.aspx&response_type=code&display=page&state=1");
    }
}
