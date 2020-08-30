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

public class UpdateModule extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private EditText name,policyname,phone,email,date;
    private Button upda;
    DatabaseReference data;
    updatepojo up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_module);
      name=(EditText)findViewById(R.id.name);
        policyname=(EditText)findViewById(R.id.policyname);
        phone=(EditText)findViewById(R.id.phone);
        email=(EditText)findViewById(R.id.email);
        date=(EditText)findViewById(R.id.date);
        upda=(Button)findViewById(R.id.update);
        up= new updatepojo();
        Intent intent = getIntent();
        final String uname = intent.getStringExtra("uname");
        Intent i = getIntent();
        String nname = i.getStringExtra("name");
        String mobile = i.getStringExtra("mobile");
        String policy = i.getStringExtra("policy");
        String emaill = i.getStringExtra("email");
        name.setText(nname);
        policyname.setText(policy);
        phone.setText(mobile);
        email.setText(emaill);
        data= FirebaseDatabase.getInstance().getReference().child("Client");


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datepicker = new DatePickerFragment();
                datepicker.show(getSupportFragmentManager(), "date picker");
            }
        });


        upda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String na= name.getText().toString();
                String pn = policyname.getText().toString();
                String em = email.getText().toString();
                String ph = phone.getText().toString();
                String da = date.getText().toString();
                up.setName(na);
                up.setPolicyName(pn);
                up.setEmail(em);
                up.setPhone(ph);
                up.setDate(da);
                data.child(na).setValue(up);
                Intent i=new Intent(UpdateModule.this, ClientModule.class);
                startActivity(i);

            }
        });


    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, i);
        c.set(Calendar.MONTH, i1);
        c.set(Calendar.DAY_OF_MONTH, i2);
        String Cdate = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(c.getTime());
        date.setText(Cdate);
    }
}
