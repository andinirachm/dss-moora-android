package com.spk.model;

public class Popularitas {
    int id;
    String popSangatRendah1;
    String popSangatRendah2;
    String popRendah1;
    String popRendah2;
    String popSedang1;
    String popSedang2;
    String popTinggi1;
    String popTinggi2;
    String popSangatTinggi1;
    String popSangatTinggi2;

    public Popularitas() {
    }

    public Popularitas(int id,
                       String popSangatRendah1,
                       String popSangatRendah2,
                       String popRendah1,
                       String popRendah2,
                       String popSedang1,
                       String popSedang2,
                       String popTinggi1,
                       String popTinggi2,
                       String popSangatTinggi1,
                       String popSangatTinggi2) {
        this.id = id;
        this.popSangatRendah1 = popSangatRendah1;
        this.popSangatRendah2 = popSangatRendah2;
        this.popRendah1 = popRendah1;
        this.popRendah2 = popRendah2;
        this.popSedang1 = popSedang1;
        this.popSedang2 = popSedang2;
        this.popTinggi1 = popTinggi1;
        this.popTinggi2 = popTinggi2;
        this.popSangatTinggi1 = popSangatTinggi1;
        this.popSangatTinggi2 = popSangatTinggi2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPopSangatRendah1() {
        return popSangatRendah1;
    }

    public void setPopSangatRendah1(String popSangatRendah1) {
        this.popSangatRendah1 = popSangatRendah1;
    }

    public String getPopSangatRendah2() {
        return popSangatRendah2;
    }

    public void setPopSangatRendah2(String popSangatRendah2) {
        this.popSangatRendah2 = popSangatRendah2;
    }

    public String getPopRendah1() {
        return popRendah1;
    }

    public void setPopRendah1(String popRendah1) {
        this.popRendah1 = popRendah1;
    }

    public String getPopRendah2() {
        return popRendah2;
    }

    public void setPopRendah2(String popRendah2) {
        this.popRendah2 = popRendah2;
    }

    public String getPopSedang1() {
        return popSedang1;
    }

    public void setPopSedang1(String popSedang1) {
        this.popSedang1 = popSedang1;
    }

    public String getPopSedang2() {
        return popSedang2;
    }

    public void setPopSedang2(String popSedang2) {
        this.popSedang2 = popSedang2;
    }

    public String getPopTinggi1() {
        return popTinggi1;
    }

    public void setPopTinggi1(String popTinggi1) {
        this.popTinggi1 = popTinggi1;
    }

    public String getPopTinggi2() {
        return popTinggi2;
    }

    public void setPopTinggi2(String popTinggi2) {
        this.popTinggi2 = popTinggi2;
    }

    public String getPopSangatTinggi1() {
        return popSangatTinggi1;
    }

    public void setPopSangatTinggi1(String popSangatTinggi1) {
        this.popSangatTinggi1 = popSangatTinggi1;
    }

    public String getPopSangatTinggi2() {
        return popSangatTinggi2;
    }

    public void setPopSangatTinggi2(String popSangatTinggi2) {
        this.popSangatTinggi2 = popSangatTinggi2;
    }

}