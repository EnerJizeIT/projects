package it.enerjize.p_0321_simplebrow;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Browser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        WebView webView = (WebView)findViewById(R.id.web);
        Uri data = getIntent().getData();
        webView.loadUrl(data.toString());

    }
}
