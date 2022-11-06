package com.example.projectmobapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class tambah extends AppCompatActivity { @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tambah);

    final EditText edtJudul = (EditText)findViewById(R.id.edtJudul);
    final EditText edtKonten = (EditText)findViewById(R.id.edtKonten);
    Button btnReset = (Button)findViewById(R.id.btnReset);
    Button btnSimpan = (Button)findViewById(R.id.btnSimpan);

//Buat objek untuk Class MyDBHandler
    final DBHandlerNotes dbHandler = new DBHandlerNotes(this);

//Membuka koneksi database
    try { dbHandler.open();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    btnSimpan.setOnClickListener(new View.OnClickListener() { @Override
    public void onClick(View view) {
        notes ctt = new notes();
        String bJudul = edtJudul.getText().toString();
        String bKonten = edtKonten.getText().toString();
        if (bJudul.isEmpty()||bKonten.isEmpty()){
            Toast.makeText(tambah.this, "Data ada yang kosong",Toast.LENGTH_LONG).show();
        }else {
            dbHandler.createNotes(bJudul, bKonten);

            Toast.makeText(tambah.this, "Profile berhasil ditambahkan", Toast.LENGTH_LONG).show();
            edtJudul.setText("");
            edtKonten.setText("");
            edtJudul.requestFocus();

            Intent i = new Intent(tambah.this, ContactActivity.class);
            startActivity(i);
            tambah.this.finish();
            dbHandler.close();
        }
    }
    });

    btnReset.setOnClickListener(new View.OnClickListener() { @Override
    public void onClick(View view) {
        edtJudul.setText("");
        edtKonten.setText("");
        edtJudul.requestFocus();
        /*
        * Kode dibawah tidak dijalankan agar saat Reset tidak mengembalikan User Ke MainActivity
        tetapi hanya mereset input pada EdiText
        * */
        //Intent i = new Intent(tambah.this, MainActivity.class);
        //startActivity(i);
        //tambah.this.finish();
        //dbHandler.close();
    }

    });
}
}