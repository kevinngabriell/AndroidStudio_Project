package com.example.projectmobapp;

public class notes {

    private long _id;
    private String judul_notes; private String konten_notes;


    //Constructor untuk Class Barang
    public notes(){

    }

    //Method untuk set ID Barang
    public void setID(long id){
        this._id = id;
    }

    //Method untuk mendapatkan ID Barang
    public long getID(){
        return this._id;
    }

    //Method untuk set Nama Barang
    public void getJudul_notes(String namabarang){
        this.judul_notes = namabarang;
    }

    //Method untuk mendapatkan Nama Barang
    public String getJudul_notes(){
        return this.judul_notes;
    }

    //Method untuk set Kategori Barang
    public void getKonten_notes(String kategoribarang){
        this.konten_notes = kategoribarang;
    }

    //Method untuk mendapatkan Kategori Barang
    public String getKonten_notes(){
        return this.konten_notes;
    }

    //method override yang dipakai untuk mengubah objek Barang menjadi String
    @Override
    public String toString(){
        return "Nama Lengkap\t\t\t\t: " + judul_notes + " \nNomor Telepon\t\t: " + konten_notes;
    }
}
