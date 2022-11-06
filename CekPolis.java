package com.example.projectmobapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.CellIdentity;
import android.view.View;
import android.widget.Button;

public class CekPolis extends AppCompatActivity {

    Button btnPolisKebakaran, btnPolisKecelakaan, btnPolisKendaraan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_polis);

        btnPolisKebakaran = (Button) findViewById(R.id.btnPolisKebakaran);
        btnPolisKecelakaan = (Button) findViewById(R.id.btnPolisKecelakaan);
        btnPolisKendaraan = (Button) findViewById(R.id.btnPolisKendaraan);

        btnPolisKebakaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cekPolisKebarakan = new Intent(CekPolis.this, cekPolisKebakaran.class);
                startActivity(cekPolisKebarakan);
            }
        });

        btnPolisKecelakaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cekPolisKecelakaan = new Intent (CekPolis.this, cekPolisKecelakaan.class);
                startActivity(cekPolisKecelakaan);
            }
        });

        btnPolisKendaraan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cekPolisKendaraan = new Intent (CekPolis.this, cekPolisKendaraan.class);
                startActivity(cekPolisKendaraan);
            }
        });
    }
}