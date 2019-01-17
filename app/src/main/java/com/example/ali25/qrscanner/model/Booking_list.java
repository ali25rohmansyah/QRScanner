package com.example.ali25.qrscanner.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Booking_list {
    public String npm;
    public String loker;
    public String nama;
    public  String waktu;

    public Booking_list() {
    }

    public Booking_list(String npm, String loker, String nama, String waktu) {
        this.npm = npm;
        this.loker = loker;
        this.nama = nama;
        this.waktu = waktu;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("npm", npm);
        result.put("loker", loker);
        result.put("nama", nama);
        result.put("waktu", waktu);
        return result;
    }

}