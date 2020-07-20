package com.aumento.floodrescuresystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserHomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button requestRescueBT, requestStatusBT, requestPredictionBT;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        requestRescueBT = (Button) findViewById(R.id.requestRescueButton);
        requestRescueBT.setOnClickListener(this);
        requestStatusBT = (Button) findViewById(R.id.requestStatusButton);
        requestStatusBT.setOnClickListener(this);
        requestPredictionBT = (Button) findViewById(R.id.requestPredictionButton);
        requestPredictionBT.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.requestRescueButton:
                intent = new Intent(UserHomeActivity.this,RescueRequestActivity.class);
                startActivity(intent);
                break;
            case R.id.requestStatusButton:
                intent = new Intent(UserHomeActivity.this,RescueRequestStatusActivity.class);
                startActivity(intent);
                break;
            case R.id.requestPredictionButton:
                intent = new Intent(UserHomeActivity.this,FloodPredictionActivity.class);
                startActivity(intent);
                break;
        }
    }
}
