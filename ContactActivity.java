package com.example.projectmobapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ContactActivity extends ListActivity implements AdapterView.OnItemLongClickListener {

    private DBHandlerNotes dbHandler;
    private ArrayList<notes> values;
    private Button btnEdit;
    private Button btnDelete;
    Context context = this;

    Button btnAboutUs, btnEditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        btnAboutUs = (Button) findViewById(R.id.btnAboutUs);
        btnEditProfile = (Button) findViewById(R.id.btnEditProfile);

        btnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AboutUs = new Intent(ContactActivity.this, AboutUs.class);
                startActivity(AboutUs);
            }
        });

//        btnEditProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent editProfile = new Intent(ContactActivity.this, EditProfile.class);
//                startActivity(editProfile);
//            }
//        });

        //Buat objek untuk Class MyDBHandler
        dbHandler = new DBHandlerNotes(this);

//Membuka koneksi database
        try {
            dbHandler.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        values = dbHandler.getAllNotes();

        ArrayAdapter<notes> adapter = new ArrayAdapter<notes>(this, android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);

        ListView list = (ListView)findViewById(android.R.id.list);
        list.setOnItemLongClickListener(this);

        btnEditProfile.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            Intent i = new Intent(getApplicationContext(),tambah.class);
            startActivity(i);
        }
        });
    }

    //Method yang digunakan jika ListView ditekan agak lama
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int
            i, long l) {
//Menampilkan dialog dan mengambil layout dari dialog.xml
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        // tidak bisa mengeluarkan title jadi manual dari design dialog.xml
        // dialog.setTitle("Pilih Aksi");
        dialog.show();

        final notes ctt = (notes)getListAdapter().getItem(i);
        final long id = ctt.getID();

        btnEdit = dialog.findViewById(R.id.btnEdit);
        btnDelete = dialog.findViewById(R.id.btnHapus);

//Method yang digunakan jika Button Edit pada dialog.xml ditekan
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notes ctt = dbHandler.getNote(id);
                Intent i = new Intent(getApplicationContext(),EditProfile.class);
                Bundle bundle = new Bundle();
                bundle.putLong("id", ctt.getID());
                bundle.putString("judul", ctt.getJudul_notes());
                bundle.putString("konten", ctt.getKonten_notes());

                i.putExtras(bundle);
                startActivity(i);
                dialog.dismiss();
            }
        });

//Method yang digunakan jika Button Delete pada dialog.xml ditekan
        btnDelete.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            AlertDialog.Builder konfirm = new AlertDialog.Builder(context);
            konfirm.setTitle("Hapus Profile");
            konfirm.setMessage("Anda yakin akan menghapus Profile ini?");
            konfirm.setPositiveButton("Ya", new
                    DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dbHandler.deleteNotes(id);

//Menutup MainActivity dan membukanya kembali untuk merefresh konten ListView
                            finish();
                            startActivity(getIntent());
                            Toast.makeText(ContactActivity.this, "Profile berhasil dihapus",Toast.LENGTH_LONG).show();
                        }
                    });
            konfirm.setNegativeButton("Tidak", new
                    DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            konfirm.show(); dialog.dismiss();
        }
        });
        return true;
    }
}