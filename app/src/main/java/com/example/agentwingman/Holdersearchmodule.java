package com.example.agentwingman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Holdersearchmodule extends AppCompatActivity {
TextView tt;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holdersearchmodule);
        tt = (TextView)findViewById(R.id.sname);
        btn = (Button)findViewById(R.id.sear);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = tt.getText().toString();
                Intent i = new Intent(Holdersearchmodule.this, Holder_Search.class);
                i.putExtra("message", text);
                startActivity(i);
            }
        });
    }
}
