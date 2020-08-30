package com.example.agentwingman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.TextView;
import android.widget.Toast;

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

public class Sending_Sms extends AppCompatActivity {
    DatabaseReference reff;
    TextView t11,t2;
    ArrayList<String> Phone= new ArrayList<>();
    Context context;
    int count = 0;
    String Message = "Pradeep";
    String num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending__sms);
        t11 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);

        reff = FirebaseDatabase.getInstance().getReference().child("Holder");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    HolderPojo li = dataSnapshot1.getValue(HolderPojo.class);
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
                        //Phone.add(li.getPhone());
                        int permission = ContextCompat.checkSelfPermission(Sending_Sms.this, Manifest.permission.SEND_SMS);
                        if (permission == PackageManager.PERMISSION_GRANTED) {
                            if (!li.getPhone().equals("") ||!Message.equals("")) {
                                //String nm = number.get(i);
                                SmsManager smsManager = SmsManager.getDefault();
                                smsManager.sendTextMessage(li.getPhone(), null, "This message from Agent "+Message+". You have to pay the due amount for this month", null, null);
                                //Toast.makeText(this, "MESSAGE SENT", Toast.LENGTH_SHORT).show();
                                toast();
                            }
                                                   else {
//                            Toast.makeText(this, "MESSAGE NOT SENT", Toast.LENGTH_SHORT).show();
                                toastnot();
                        }
                        }
                    }

                }
                //t11.setText(Phone.get(1));
//                count = number.size();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        int permission =ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
//
//        if(permission == PackageManager.PERMISSION_GRANTED)
//        {
//            Mymessage();
//        }
//
//        else{
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},0);
//        }
    }

//    private void Mymessage()
//    {
//        String numberr = "+919597412583";
//        for(int i=0; i<number.size(); i++) {
//            if (!numberr.equals("") || !Message.equals("")) {
//                //String nm = number.get(i);
//                SmsManager smsManager = SmsManager.getDefault();
//                smsManager.sendTextMessage(numberr, null, String.valueOf(count), null, null);
//                Toast.makeText(this, "MESSAGE SENT", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(this, "MESSAGE NOT SENT", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
    public void toast(){
        Toast.makeText(this, "MESSAGE SENT", Toast.LENGTH_SHORT).show();

    }

    public void toastnot(){
        Toast.makeText(this, "MESSAGE NOT SENT", Toast.LENGTH_SHORT).show();
    }
}
