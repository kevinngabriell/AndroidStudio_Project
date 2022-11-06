package com.example.projectmobapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AboutUs extends AppCompatActivity {

    Button btnLocation, btnWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        btnLocation = (Button) findViewById(R.id.btnLocation);
        btnWebView = (Button) findViewById(R.id.btnWebview);

        btnWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent webview = new Intent(AboutUs.this, Browser.class);
                startActivity(webview);
            }
        });

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent maps = new Intent(AboutUs.this, CHUBBLocationMap.class);
                startActivity(maps);
            }
        });
    }
}