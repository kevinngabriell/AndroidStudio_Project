package com.example.projectmobapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CekStatusClaim extends AppCompatActivity {

    Button btnPolisKebakaran3, btnPolisKecelakaan3, btnPolisKendaraan3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_status_claim);

        btnPolisKebakaran3 = (Button) findViewById(R.id.btnPolisKebakaran3);
        btnPolisKecelakaan3 = (Button) findViewById(R.id.btnPolisKecelakaan3);
        btnPolisKendaraan3 = (Button) findViewById(R.id.btnPolisKendaraan3);

        btnPolisKebakaran3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cekPembayaranKebarakan = new Intent(CekStatusClaim.this, StatusClaimKebakaran.class);
                startActivity(cekPembayaranKebarakan);
            }
        });

        btnPolisKecelakaan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cekPembayaranKecelakaan = new Intent (CekStatusClaim.this, StatusClaimKecelakaan.class);
                startActivity(cekPembayaranKecelakaan);
            }
        });

       btnPolisKendaraan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cekPembayaranKendaraan = new Intent (CekStatusClaim.this, StatusClaimKendaraan.class);
                startActivity(cekPembayaranKendaraan);
           }
        });
    }
}