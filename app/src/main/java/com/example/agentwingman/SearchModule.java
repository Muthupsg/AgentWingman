package com.example.agentwingman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SearchModule extends AppCompatActivity {
private Button b1;
TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_module);
        b1=(Button)findViewById(R.id.sear);
        t1 = (TextView)findViewById(R.id.sname);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t= t1.getText().toString();
                Intent i = new Intent(SearchModule.this, Main.class);
                i.putExtra("search", t);
                startActivity(i);

            }
        });
    }
}
