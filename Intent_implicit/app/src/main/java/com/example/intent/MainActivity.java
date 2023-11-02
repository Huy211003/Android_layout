package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imgBrowser, imgMessage, imgCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgBrowser = (ImageView) findViewById(R.id.imageViewBrowser);
        imgMessage = (ImageView) findViewById(R.id.imageViewMessage);
        imgCall = (ImageView) findViewById(R.id.imageViewCall);

        imgBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://google.com"));
                startActivity(intent);
            }
        });

        imgMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                intent.putExtra("sms_body","Chào bạn...");
                intent.setData(Uri.parse("sms:0848613959"));
                startActivity(intent);
            }
        });

        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);//nên dùng DIAL thay vì CALL, vì DIAL sẽ mở ứng dụng trên điện thoại
                intent.setData(Uri.parse("tel:0353464265"));
                startActivity(intent);
            }
        });
    }
}