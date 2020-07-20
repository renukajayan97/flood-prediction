package com.aumento.floodrescuresystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button rescuerLoginBT, userLoginBT;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rescuerLoginBT = (Button) findViewById(R.id.rescuerLoginButton);
        rescuerLoginBT.setOnClickListener(this);
        userLoginBT = (Button) findViewById(R.id.userLoginButton);
        userLoginBT.setOnClickListener(this);
        intent = new Intent(MainActivity.this,LoginActivity.class);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.rescuerLoginButton:
                intent.putExtra("type","rescuer");
                startActivity(intent);
                break;
            case R.id.userLoginButton:
                intent.putExtra("type","user");
                startActivity(intent);
                break;
        }

    }
}
