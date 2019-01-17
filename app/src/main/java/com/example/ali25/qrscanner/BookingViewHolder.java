package com.example.ali25.qrscanner;

import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.example.ali25.qrscanner.model.Booking_list;


public class BookingViewHolder extends RecyclerView.ViewHolder {

    public TextView tvLoker;
    public TextView tvNama;
    public TextView tvNpm;
    public TextView btnKeluar;

    public BookingViewHolder(View itemView) {
        super(itemView);
        tvLoker = itemView.findViewById(R.id.tv_loker);
        tvNama = itemView.findViewById(R.id.tv_nama);
        tvNpm = itemView.findViewById(R.id.tv_npm);
        btnKeluar = itemView.findViewById(R.id.btn_keluar);
    }

    public void bindToBooking(Booking_list booking_list, View.OnClickListener onClickListener){
        tvNama.setText("Nama : "+booking_list.nama);
        tvLoker.setText("Loker "+booking_list.loker);
        tvNpm.setText("Npm : "+booking_list.npm);
        btnKeluar.setOnClickListener(onClickListener);
    }
}
