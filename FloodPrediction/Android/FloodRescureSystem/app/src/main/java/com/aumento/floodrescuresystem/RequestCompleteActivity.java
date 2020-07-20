package com.aumento.floodrescuresystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RequestCompleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_complete);

        Button requestStatusBT = (Button) findViewById(R.id.requestStatusButton);
        requestStatusBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RequestCompleteActivity.this,RescueRequestStatusActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
