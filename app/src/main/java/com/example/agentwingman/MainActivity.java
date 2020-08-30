package com.example.agentwingman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
private EditText name,pass;
private Button b1;
DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.name);
        pass=(EditText)findViewById(R.id.pass);
        b1=(Button)findViewById(R.id.bb1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vname=name.getText().toString();
                reff=FirebaseDatabase.getInstance().getReference().child("Agent").child(vname);

                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String vname=name.getText().toString();
                        String vpass=pass.getText().toString();
                        String gname=dataSnapshot.child("aid").getValue().toString();
                        String gpass=dataSnapshot.child("pass").getValue().toString();
                        if(gname.equals(vname)&& gpass.equals(vpass))
                        {
                            Intent i=new Intent(MainActivity.this, mainmodule.class);
                           // i.putExtra("uname",vname);
                            startActivity(i);

                        }
                        else
                        {
                            name.setError("Invalid UserName");
                            pass.setError("Password Is Invalid");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }




//Function For Login Button
    public void Signup(View v){

        Intent i = new Intent(this,Signup.class);
        startActivity(i);
    }








    //Alert Box For BackButton
    public void onBackPressed ()
    {

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Are you sure want to exit");
        builder.setCancelable(true);
        builder.setPositiveButton("YES",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i ){
                finish();
            }
        } );
        builder.setNegativeButton("NO!" ,new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }  );
        AlertDialog alertDialog = builder.create();

        alertDialog.show();

    }
}
