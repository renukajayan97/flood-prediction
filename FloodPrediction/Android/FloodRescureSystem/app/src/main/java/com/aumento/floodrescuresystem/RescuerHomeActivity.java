package com.aumento.floodrescuresystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aumento.floodrescuresystem.Utils.GPS_service;

public class RescuerHomeActivity extends AppCompatActivity {

    Button viewRescueRequestBT, viewMyRequestBT;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescuer_home);

        Intent in=new Intent(getApplicationContext(), GPS_service.class);
        startService(in);

        viewRescueRequestBT = (Button) findViewById(R.id.viewRescueRequestButton);
        viewRescueRequestBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(RescuerHomeActivity.this,RescueRequestListActivity.class);
                startActivity(intent);
            }
        });

        viewMyRequestBT = (Button) findViewById(R.id.viewMyRequestButton);
        viewMyRequestBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(RescuerHomeActivity.this,MyRequestListActivity.class);
                startActivity(intent);
            }
        });
    }
}
