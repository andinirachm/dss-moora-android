package com.spk.model;

public class Komentar {
    String nama_user;
    String isi;
    String tanggal;


    public Komentar() {
    }

    public Komentar(String nama_user,
                    String isi,
                    String tanggal) {
        this.nama_user = nama_user;
        this.isi = isi;
        this.tanggal = tanggal;
    }


    public String getNamaUser() {
        return nama_user;
    }

    public void setNamaUser(String nama_user) {
        this.nama_user = nama_user;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

}