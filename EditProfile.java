package com.example.projectmobapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EditProfile extends AppCompatActivity {

    private long id;
    private String judul_notes;
    private String konten_notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        final EditText editJudul = (EditText)findViewById(R.id.edtJudulB);
        final EditText editKonten = (EditText)findViewById(R.id.edtKontenB);
        Button btnReset = (Button)findViewById(R.id.btnResetB);
        Button btnSimpan = (Button)findViewById(R.id.btnSimpanB);

//Buat objek untuk Class MyDBHandler
        final DBHandlerNotes dbHandler = new DBHandlerNotes(this);

//Membuka koneksi database
        try { dbHandler.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Bundle bundle = this.getIntent().getExtras();
        id = bundle.getLong("id");
        judul_notes = bundle.getString("judul");
        konten_notes = bundle.getString("konten");

        this.setTitle("Edit Profile: "+ id);
        editJudul.setText(judul_notes);
        editKonten.setText(konten_notes);

        btnSimpan.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            notes ctt = new notes();
            ctt.setID(id);
            String Bjudul = editJudul.getText().toString();
            String Bkonten = editKonten.getText().toString();
            if (Bjudul.isEmpty()||Bkonten.isEmpty()){
                Toast.makeText(EditProfile.this, "Data ada yang Kosong",Toast.LENGTH_LONG).show();
            }else{
                ctt.getJudul_notes(Bjudul);
                ctt.getKonten_notes(Bkonten);
                dbHandler.updateNotes(ctt);

                Toast.makeText(EditProfile.this, "Profile berhasil diedit",Toast.LENGTH_LONG).show();
                Intent i = new Intent(EditProfile.this, ContactActivity.class);
                startActivity(i);
                EditProfile.this.finish();
                dbHandler.close();
            }
        }
        });

        btnReset.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            editJudul.setText("");
            editKonten.setText("");
            editJudul.requestFocus();
        }

        });
    }
}