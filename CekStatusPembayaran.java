package com.example.projectmobapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CekStatusPembayaran extends AppCompatActivity {

    Button btnPolisKebakaran2, btnPolisKecelakaan2, btnPolisKendaraan2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_status_pembayaran);

        btnPolisKebakaran2 = (Button) findViewById(R.id.btnPolisKebakaran2);
        btnPolisKecelakaan2 = (Button) findViewById(R.id.btnPolisKecelakaan2);
        btnPolisKendaraan2 = (Button) findViewById(R.id.btnPolisKendaraan2);

        btnPolisKebakaran2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cekPembayaranKebarakan = new Intent(CekStatusPembayaran.this, StatusPembayaranKebakaran.class);
                startActivity(cekPembayaranKebarakan);
            }
        });

        btnPolisKecelakaan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent cekPembayaranKecelakaan = new Intent (CekStatusPembayaran.this, StatusPembayaranKecelakaan.class);
                startActivity(cekPembayaranKecelakaan);
            }
        });

        btnPolisKendaraan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cekPembayaranKendaraan = new Intent (CekStatusPembayaran.this, StatusPembayaranKendaraan.class);
                startActivity(cekPembayaranKendaraan);
            }
        });
    }
}