package com.example.readnewpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

public class NewsActivity extends AppCompatActivity {

    WebView webview;
    ImageView imageBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        webview = (WebView)findViewById(R.id.webviewTinTuc);
        imageBack = (ImageView) findViewById(R.id.img_back);

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();

        String link = intent.getStringExtra("linkTinTuc");

        //Để hiển thị trang detail
        webview.loadUrl(link);

        //khi click vào bất kì link hay đối tượng nào trong trang detail, thì nó luôn hiển thị trong app, không đi ra ngoài trình duyệt mặc định
        webview.setWebViewClient(new WebViewClient());
    }
}