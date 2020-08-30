package com.example.agentwingman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Sms_View_Module extends AppCompatActivity  {
    DatabaseReference reff;
    RecyclerView recyclerView;
    ArrayList<Holderlistview> list;
    SmsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms__view__module);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Holderlistview>();
        reff = FirebaseDatabase.getInstance().getReference().child("Holder");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    Holderlistview li = dataSnapshot1.getValue(Holderlistview.class);
                    String date = li.getSdate();
                    String due = li.getDueDuration();
                    int due1 = Integer.parseInt(due);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar c = Calendar.getInstance();
                    String today = sdf.format(new Date());
                    char[] arr1 = today.toCharArray();
                    String str3 = Character.toString(arr1[3]);
                    String str4 = Character.toString(arr1[4]);
                    String str5 = str3 + str4;

                    try {
                        c.setTime(sdf.parse(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    c.add(Calendar.MONTH, due1);
                    String Cdate = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(c.getTime());
                    char[] arr = Cdate.toCharArray();
                    String str1 = Character.toString(arr[3]);
                    String str2 = Character.toString(arr[4]);


                    String mn = str1 + str2;
                    if(str5.equals(mn)) {
                        list.add(li);
                    }

                }

                adapter = new SmsAdapter(Sms_View_Module.this, list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
