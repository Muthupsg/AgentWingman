package com.example.agentwingman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mainmodule extends AppCompatActivity {
Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmodule);
        b1=(Button)findViewById(R.id.client);
        b2=(Button)findViewById(R.id.holders);
        Intent intent = getIntent();
        final String uname = intent.getStringExtra("uname");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(mainmodule.this, ClientModule.class);
                //i.putExtra("uname", uname);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mainmodule.this, HolderModule.class);
                startActivity(i);
            }
        });
    }
}
