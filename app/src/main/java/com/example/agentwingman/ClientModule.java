package com.example.agentwingman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClientModule extends AppCompatActivity {
private Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_module);
        b1=(Button)findViewById(R.id.update);
        b2=(Button)findViewById(R.id.schedule);
        b3=(Button)findViewById(R.id.sear);

        /*b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ClientModule.this, UpdateModule.class);
                startActivity(i);

            }
        });*/
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ClientModule.this, SearchModule.class);
                startActivity(i);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ClientModule.this, ScheduleModule.class);
                startActivity(i);
            }
        });
    }
    public void update(View v)
    {
        Intent intent = getIntent();
        final String uname = intent.getStringExtra("uname");
        Intent i=new Intent(this, UpdateModule.class);
        //i.putExtra("uname", uname);
        startActivity(i);
    }
}
