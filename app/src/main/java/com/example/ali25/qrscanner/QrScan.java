package com.example.ali25.qrscanner;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ali25.qrscanner.model.Booking;
import com.example.ali25.qrscanner.model.Users;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

public class QrScan extends AppCompatActivity implements ZXingScannerView.ResultHandler  {
    private DatabaseReference database;
    private ZXingScannerView mScannerView;
    public String npm = "";
    public String loker = "";
    public String waktu = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(final Result rawResult) {

        try {
            JSONObject object = new JSONObject(rawResult.getText());
            npm = object.getString("npm");
            loker = object.getString("loker");
            waktu = object.getString("waktu");

            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference table_booking = database.getReference("Booking");
            final DatabaseReference table_users = database.getReference("Users");
            final DatabaseReference table_loker = database.getReference("Loker");

            // Ambil data user dari npm
            table_users.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //ambil nama dari npm
                    Users users = dataSnapshot.child(npm).getValue(Users.class);

                    //masukin data ke booking
                    Booking booking = new Booking(npm,loker,users.getNama(),waktu);
                    table_booking.child(npm).setValue(booking);

                    //Update status loker
                    table_loker.child(loker).child("status").setValue("Penuh");

                    Toast.makeText(QrScan.this,"Looker berhasil di isi", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }catch (JSONException e){
            e.printStackTrace();
        }

        mScannerView.resumeCameraPreview(this);
    }


}
