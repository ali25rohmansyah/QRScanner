package com.example.ali25.qrscanner.model;

import android.support.annotation.NonNull;

public class Users {

    private String jkel;
    private String nama;
    private String pass;

    public Users(){

    }
    public Users(String jk, String nm, String ps){
        jkel = jk;
        nama = nm;
        pass = ps;
    }
    public String getJkel(){
        return jkel;
    }
    public void setJkel(String jkel) {
        this.jkel = jkel;
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {

        this.nama = nama;
    }
    public String getPass(){
        return jkel;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }

    @NonNull
    @Override
    public String toString() {
        return " "+jkel+"\n" +
                " "+nama+"\n" +
                " "+pass;
    }
}
