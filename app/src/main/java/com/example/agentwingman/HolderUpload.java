package com.example.agentwingman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class HolderUpload extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    EditText h_name,h_lno,h_dno,h_sdate,h_edate,ph,em;
    Button up;
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holder_upload);
        h_name = (EditText)findViewById(R.id.h_name);
        h_lno = (EditText)findViewById(R.id.h_lno);
        h_dno = (EditText)findViewById(R.id.h_dno);
        h_sdate = (EditText)findViewById(R.id.h_sdate);
        h_edate = (EditText)findViewById(R.id.h_edate);
        ph = (EditText)findViewById(R.id.ph);
        em = (EditText)findViewById(R.id.em);
        up = (Button)findViewById(R.id.up);
        reff = FirebaseDatabase.getInstance().getReference().child("Holder");


        Intent intent = getIntent();
        String name =intent.getStringExtra("name");
        String policy =intent.getStringExtra("policy");
        h_name.setText(name);
         h_sdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datepicker = new DatePickerFragment();
                datepicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        h_edate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment date = new Date2();
                date.show(getSupportFragmentManager(), "date2");
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = h_name.getText().toString();
                String no = h_lno.getText().toString();
                String dno = h_dno.getText().toString();
                String sdate = h_sdate.getText().toString();
                String edate = h_edate.getText().toString();
                String pho = ph.getText().toString();
                String email = em.getText().toString();
                if(name.isEmpty()||no.isEmpty()||dno.isEmpty()||sdate.isEmpty()||edate.isEmpty())
                {
                    if(name.isEmpty())
                    {
                        h_name.setError("Tab Is Empty");
                    }
                    if(no.isEmpty())
                    {
                        h_lno.setError("Tab Is Empty");
                    }
                    if(dno.isEmpty())
                    {
                        h_dno.setError("Tab Is Empty");
                    }
                    if(sdate.isEmpty())
                    {
                        h_sdate.setError("Tab Is Empty");
                    }
                    if(edate.isEmpty())
                    {
                        h_edate.setError("Tab Is Empty");
                    }
                    if(pho.isEmpty())
                    {
                        ph.setError("Tab Is Empty");
                    }
                    if(email.isEmpty())
                    {
                        em.setError("Tab Is Empty");
                    }
                }
                else {
                    HolderPojo hp = new HolderPojo();
                    hp.setName(name);
                    hp.setPolicyNumber(no);
                    hp.setDueDuration(dno);
                    hp.setSdate(sdate);
                    hp.setEdate(edate);
                    hp.setPhone(pho);
                    hp.setEmail(email);
                    reff.child(name).setValue(hp);
                }
            }
        });

    }

    @Override
    public void onDateSet(DatePicker datePicker,int i, int i1, int i2) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, i);
        c.set(Calendar.MONTH, i1);
        c.set(Calendar.DAY_OF_MONTH, i2);
        String Cdate = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(c.getTime());
        h_sdate.setText(Cdate);


    }



}
