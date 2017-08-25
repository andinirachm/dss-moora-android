package com.spk.model;

public class DestinasiWisata {
    int id;
    String nama;
    String alamat;
    String lat;
    String longi;
    String deskripsi;
    String url_foto;
    String biaya;
    String jarak;
    String alamat_lengkap;
    String urlWeb;
    String jml_wisatawan;
    String produk;

    public DestinasiWisata() {
    }

    public DestinasiWisata(int id, String nama, String alamat, String lat,
                           String longi, String deskripsi, String url_foto, String biaya, String jarak, String alamat_lengkap, String urlWeb, String jml_wisatawan, String produk) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.lat = lat;
        this.longi = longi;
        this.deskripsi = deskripsi;
        this.url_foto = url_foto;
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

    public String getnama() {
        return nama;
    }

    public void setnama(String nama) {
        this.nama = nama;
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

    public String geturl_foto() {
        return url_foto;
    }

    public void seturl_foto(String url_foto) {
        this.url_foto = url_foto;
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

    public void setJmlWisatawan(String jml_wisatawan) {
        this.jml_wisatawan = jml_wisatawan;
    }

    public String getProduk() {
        return produk;
    }

    public void setProduk(String produk) {
        this.produk = produk;
    }

}