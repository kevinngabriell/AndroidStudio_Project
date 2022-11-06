package com.example.projectmobapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class tampilanHome extends AppCompatActivity {
    ImageButton AsuransiKebakaran,CekPembayaran, AsuransiKendaraan, CekPolis, AsuransiKecelakaan, StatusKlaim, contact;
    TextView txtAsuransiKebakaran,txtCekPembayaran, txtAsuransiKendaraan,
            txtCekPolis, txtAsuransiKecelakaan, txtStatusKlaim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilan_home);

        AsuransiKebakaran = (ImageButton) findViewById(R.id.AsuransiKebakaran);
        CekPembayaran = (ImageButton) findViewById(R.id.CekPembayaran);
        AsuransiKendaraan = (ImageButton) findViewById(R.id.AsuransiKendaraan);
        CekPolis = (ImageButton) findViewById(R.id.CekPolis);
        AsuransiKecelakaan = (ImageButton) findViewById(R.id.AsuransiKecelakaan);
        StatusKlaim = (ImageButton) findViewById(R.id.StatusKlaim);
        contact = (ImageButton) findViewById(R.id.btnContactUs);

        txtAsuransiKebakaran = (TextView) findViewById(R.id.txtAsuransiKebakaran);
        txtCekPembayaran = (TextView) findViewById(R.id.txtCekPembayaran);
        txtAsuransiKendaraan = (TextView) findViewById(R.id.txtAsuransiKendaraan);
        txtCekPolis = (TextView) findViewById(R.id.txtCekPolis);
        txtAsuransiKecelakaan = (TextView) findViewById(R.id.txtAsuransiKecelakaan);
        txtStatusKlaim = (TextView) findViewById(R.id.txtStatusKlaim);

        StatusKlaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent StatusKlaim = new Intent(tampilanHome.this, CekStatusClaim.class);
                startActivity(StatusKlaim);
            }
        });

        txtStatusKlaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent txtStatusKlaim = new Intent(tampilanHome.this, CekStatusClaim.class);
                startActivity(txtStatusKlaim);
            }
        });

        CekPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent CekPembayaran = new Intent (tampilanHome.this, CekStatusPembayaran.class);
                startActivity(CekPembayaran);
            }
        });

        txtCekPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent txtCekPembayaran = new Intent (tampilanHome.this, CekStatusPembayaran.class);
                startActivity(txtCekPembayaran);
            }
        });

        AsuransiKebakaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AsuransiKebakaran = new Intent(tampilanHome.this, AsuransiKebakaran.class);
                startActivity(AsuransiKebakaran);
            }
        });

        txtAsuransiKebakaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent txtAsuransiKebakaran = new Intent(tampilanHome.this, AsuransiKebakaran.class);
                startActivity(txtAsuransiKebakaran);
            }
        });

        AsuransiKendaraan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AsuransiKendaraan = new Intent(tampilanHome.this, AsuransiKendaraan.class);
                startActivity(AsuransiKendaraan);
            }
        });

        txtAsuransiKendaraan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent txtAsuransiKendaraan = new Intent(tampilanHome.this, AsuransiKendaraan.class);
                startActivity(txtAsuransiKendaraan);
            }
        });

        AsuransiKecelakaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AsuransiKecelakaan = new Intent(tampilanHome.this, AsuransiKecelakaan.class);
                startActivity(AsuransiKecelakaan);
            }
        });

        txtAsuransiKecelakaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent txtAsuransiKecelakaan = new Intent(tampilanHome.this, AsuransiKecelakaan.class);
                startActivity(txtAsuransiKecelakaan);
            }
        });

        CekPolis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent CekPolis = new Intent(tampilanHome.this, CekPolis.class);
                startActivity(CekPolis);
            }
        });

        txtCekPolis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent txtCekPolis = new Intent(tampilanHome.this, CekPolis.class);
                startActivity(txtCekPolis);
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t = new Intent(tampilanHome.this, ContactActivity.class);
                startActivity(t);
            }
        });

    }
}