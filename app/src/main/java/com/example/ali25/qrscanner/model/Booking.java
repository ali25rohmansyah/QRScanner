package com.example.ali25.qrscanner.model;

import android.support.annotation.NonNull;

public class Booking {

    private String npm;
    private String loker;
    private String nama;
    private String waktu;

    public Booking(){

    }

    public Booking(String npm, String loker, String nama, String waktu) {
        this.npm = npm;
        this.loker = loker;
        this.nama = nama;
        this.waktu = waktu;
    }

    public String getNpm(){
        return npm;
    }
    public void setNpm(String npm) {
        this.npm = npm;
    }


    public String getLoker(){
        return loker;
    }
    public void setLoker(String loker) {
        this.loker = loker;
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {

        this.nama = nama;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    @NonNull
    @Override
    public String toString() {
        return " "+npm+"\n" +
                " "+loker+"\n" +
                 " "+nama+"\n" +
                  " "+waktu;
    }

}
