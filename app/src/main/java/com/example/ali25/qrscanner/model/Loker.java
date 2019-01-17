package com.example.ali25.qrscanner.model;

public class Loker {
    private String LokerID;
    private String status;

    public Loker() {
    }

    public Loker(String lokerID, String status) {
        LokerID = lokerID;
        this.status = status;
    }

    public String getLokerID() {
        return LokerID;
    }

    public void setLokerID(String lokerID) {
        LokerID = lokerID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
