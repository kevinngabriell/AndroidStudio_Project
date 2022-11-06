package com.example.projectmobapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandlerNotes extends SQLiteOpenHelper {

    //Deklarasi variabel konstanta untuk pembuatan database, tabel dan kolom yang diperlukan
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dataNotes.db";
    private static final String TABLE_NAME = "Notes";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_JUDULNOTES = "JudulNotes";
    private static final String COLUMN_KONTENNOTES = "KontenNotes";

    //Constructor untuk Class MyDBHandler
    public DBHandlerNotes(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Method untuk Create Database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_NOTES= "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_JUDULNOTES + " VARCHAR(50) NOT NULL, " + COLUMN_KONTENNOTES + " VARCHAR(50) NOT NULL)";

        sqLiteDatabase.execSQL(CREATE_TABLE_NOTES);
    }

    //Method yang dipakai untuk upgrade tabel
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    /*---- Insert, Select, Update, Delete */
    private SQLiteDatabase database;

    //Method untuk open database connection
    public void open() throws SQLException {
        database = this.getWritableDatabase();
    }

    //Inisialisasi semua kolom di tabel database
    private String[] allColumns =
            {COLUMN_ID,COLUMN_JUDULNOTES,COLUMN_KONTENNOTES};

    //Method untuk memindahkan isi cursor ke objek NOTES
    private notes cursorToNote(Cursor cursor){
        notes ctt = new notes();

        ctt.setID(cursor.getLong(0));
        ctt.getJudul_notes(cursor.getString(1));
        ctt.getKonten_notes(cursor.getString(2));
        return ctt;

    }

    //Method untuk menambahkan NOTES baru
    public void createNotes(String judulNotes, String kontenNotes){
        ContentValues values = new ContentValues();
        values.put(COLUMN_JUDULNOTES, judulNotes);
        values.put(COLUMN_KONTENNOTES, kontenNotes);

        database.insert(TABLE_NAME, null, values);
    }

    //Method untuk mendapatkan detail per Notes
    public notes getNote(long id){ notes catatan= new notes();

        Cursor cursor =
                database.query(TABLE_NAME,allColumns,"_id="+id,null,null,null,null);
        cursor.moveToFirst();
        catatan = cursorToNote(cursor); cursor.close();
        return catatan;
    }

    //Method untuk mendapatkan data semua Notes di tabel Notes
    public ArrayList<notes> getAllNotes(){
        ArrayList<notes> daftarNotes = new ArrayList<notes>();

        Cursor cursor =
                database.query(TABLE_NAME,allColumns,null,null,null,null,null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            notes catatan = cursorToNote(cursor); daftarNotes.add(catatan); cursor.moveToNext();
        }

        cursor.close();
        return daftarNotes;
    }

    //Method untuk mengupdate data Notes
    public void updateNotes(notes catatan){ String filter = "_id="+catatan.getID();
        ContentValues args = new ContentValues();
        args.put(COLUMN_JUDULNOTES, catatan.getJudul_notes());
        args.put(COLUMN_KONTENNOTES, catatan.getKonten_notes());

        database.update(TABLE_NAME, args, filter, null);
    }

    //Method untuk menghapus data Notes
    public void deleteNotes(long id){ String filter = "_id="+id;

        database.delete(TABLE_NAME, filter, null);
    }
}