package com.spk.model;

import java.util.Comparator;

public class HasilAkhir {
    int id;
    String nama_destinasi;
    float hasil_akhir;
    String url_gambar;
    String alamat;
    String lat;
    String longi;
    String deskripsi;
    String biaya;
    String jarak;
    String alamat_lengkap;
    String urlWeb;
    String jml_wisatawan;
    String produk;

    public HasilAkhir() {
    }

    public HasilAkhir(int id, String nama_destinasi, float hasil_akhir, String url_gambar, String alamat,
                      String lat, String longi,
                      String deskripsi,
                      String biaya,
                      String jarak,
                      String alamat_lengkap,
                      String urlWeb,
                      String jml_wisatawan,
                      String produk) {
        this.id = id;
        this.nama_destinasi = nama_destinasi;
        this.hasil_akhir = hasil_akhir;
        this.url_gambar = url_gambar;
        this.alamat = alamat;
        this.lat = lat;
        this.longi = longi;
        this.deskripsi = deskripsi;
        this.biaya = biaya;
        this.jarak = jarak;
        this.alamat_lengkap = alamat_lengkap;
        this.urlWeb = urlWeb;
        this.jml_wisatawan = jml_wisatawan;
        this.produk = produk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaDestinasi() {
        return nama_destinasi;
    }

    public void setNamaDestinasi(String nama_destinasi) {
        this.nama_destinasi = nama_destinasi;
    }

    public String getUrl() {
        return url_gambar;
    }

    public void setUrl(String url_gambar) {
        this.url_gambar = url_gambar;
    }

    public String getalamat() {
        return alamat;
    }

    public void setalamat(String alamat) {
        this.alamat = alamat;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getdeskripsi() {
        return deskripsi;
    }

    public void setdeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getBiaya() {
        return biaya;
    }

    public void setBiaya(String biaya) {
        this.biaya = biaya;
    }

    public String getJarak() {
        return jarak;
    }

    public void setJarak(String jarak) {
        this.jarak = jarak;
    }

    public String getAlamatLengkap() {
        return alamat_lengkap;
    }

    public void setAlamatLengkap(String alamat_lengkap) {
        this.alamat_lengkap = alamat_lengkap;
    }

    public String getUrlWeb() {
        return urlWeb;
    }

    public void setUrlWeb(String urlWeb) {
        this.urlWeb = urlWeb;
    }

    public String getJmlWisatawan() {
        return jml_wisatawan;
    }

    public void setJmlWisatawan(String produk) {
        this.produk = produk;
    }

    public String getProduk() {
        return produk;
    }

    public void setProduk(String produk) {
        this.produk = produk;
    }

    public float getHasil() {
        return hasil_akhir;
    }

    public void setHasil(float hasil_akhir) {
        this.hasil_akhir = hasil_akhir;
    }

    public static Comparator<HasilAkhir> StuRollno = new Comparator<HasilAkhir>() {

        public int compare(HasilAkhir s1, HasilAkhir s2) {

            float rollno1 = s1.getHasil();
            float rollno2 = s2.getHasil();

            /*int result = (int)rollno2-(int)rollno1;
            return result;*/


            if (rollno2 > rollno1)
                return -1;
            if (rollno1 > rollno2)
                return 1;

            return 0;

        }
    };

    public static Comparator<HasilAkhir> StuNameComparator = new Comparator<HasilAkhir>() {

        public int compare(HasilAkhir s1, HasilAkhir s2) {
            String StudentName1 = s1.getNamaDestinasi().toUpperCase();
            String StudentName2 = s2.getNamaDestinasi().toUpperCase();

            //ascending order
            return StudentName1.compareTo(StudentName2);

            //descending order
            //return StudentName2.compareTo(StudentName1);
        }
    };
}