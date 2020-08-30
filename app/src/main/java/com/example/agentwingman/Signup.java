package com.example.agentwingman;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class Signup extends AppCompatActivity {
EditText name,aid,email,pass,pass2,phone;
Button sign;
DatabaseReference data;
member mb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        phone=(EditText)findViewById(R.id.phone);
        name=(EditText)findViewById(R.id.name);
        aid=(EditText)findViewById(R.id.aid);
        email=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.pass);
        pass2=(EditText)findViewById(R.id.pass2);
        sign=(Button)findViewById(R.id.signup);
        mb=new member();
        data= FirebaseDatabase.getInstance().getReference().child("Agent");
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pa = pass.getText().toString();
                String pa2 = pass2.getText().toString();
                String na = name.getText().toString();
                String ai = aid.getText().toString();
                String em = email.getText().toString();
                String ph = phone.getText().toString();
                if(na.isEmpty() || pa.isEmpty() || pa2.isEmpty() || ai.isEmpty() || em.isEmpty())
                {
                    if(na.isEmpty())
                    {
                        name.setError("Please Enter Name");
                    }
                    if(pa.isEmpty())
                    {
                        pass.setError("Please Enter Name");
                    }
                    if(pa2.isEmpty())
                    {
                        pass2.setError("Please Enter Name");
                    }
                    if(ai.isEmpty())
                    {
                        aid.setError("Please Enter Name");
                    }
                    if(em.isEmpty())
                    {
                                  email.setError("Please Enter Name");
                    }
                }
                else if(pa.equals(pa2)) {
                    mb.setName(na);
                    mb.setPass(pa);
                    mb.setAid(ai);
                    mb.setEmail(em);
                    data.child(ai).setValue(mb);
                    Intent i=new Intent(Signup.this, MainActivity.class);
                    startActivity(i);
                }
                else{
                    pass.setError("InValid Password");
                    pass2.setError("Invalid Password");
                }
            }
        });

    }

}
