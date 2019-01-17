package com.example.ali25.qrscanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ali25.qrscanner.model.Booking_list;
import com.example.ali25.qrscanner.model.Loker;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<Booking_list, BookingViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Daftar Loker");

        Button btn_scan = findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, QrScan.class));
            }
        });

            mDatabase = FirebaseDatabase.getInstance().getReference();

            mRecycler = findViewById(R.id.recyclerView);
            mRecycler.setHasFixedSize(true);

            mManager = new LinearLayoutManager(this);
            mManager.setReverseLayout(true);
            mManager.setStackFromEnd(true);
            mRecycler.setLayoutManager(mManager);

            // Set up FirebaseRecyclerAdapter with the Query
            final Query query = getQuery(mDatabase);

            FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Booking_list>()
                    .setQuery(query, Booking_list.class)
                    .build();

            mAdapter = new FirebaseRecyclerAdapter<Booking_list, BookingViewHolder>(options) {
                @Override
                public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                    return new BookingViewHolder(inflater.inflate(R.layout.loker_item, parent, false));
                }
                @Override
                protected void onBindViewHolder(@NonNull BookingViewHolder holder, final int position, @NonNull final Booking_list model) {
                    holder.bindToBooking(model, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final FirebaseDatabase database = FirebaseDatabase.getInstance();
                            final DatabaseReference table_booking = database.getReference("Booking");
                            final DatabaseReference table_loker = database.getReference("Loker");

                            table_booking.child(model.npm).removeValue()
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(MainActivity.this,"Berhasil Keluar", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                            table_loker.child(model.loker).child("status").setValue("Tersedia");
                        }

                    });
                }
            };

            mAdapter.notifyDataSetChanged();
            mRecycler.setAdapter(mAdapter);
        }

        @Override
        public void onStart() {
            super.onStart();
            if (mAdapter != null) {
                mAdapter.startListening();
            }
        }

        @Override
        public void onStop() {
            super.onStop();
            if (mAdapter != null) {
                mAdapter.stopListening();
            }
        }

        private Query getQuery(DatabaseReference mDatabase){
            Query query = mDatabase.child("Booking");
            return query;
        }
}
