package com.aumento.floodrescuresystem;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aumento.floodrescuresystem.Utils.GlobalPreference;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity implements LocationListener {

    private static final String TAG = "SignupActivity";
    EditText userNameET, userPhoneNumberET, userEmailET, userHouseNameET, userHouseNoET, userTalukET, userUsernameET, userPasswordET;
    TextView submitButtonTV;
    private String ip;
    private LocationManager locationManager;
    private String lat;
    private String lon;
    private String type;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        GlobalPreference globalPreference = new GlobalPreference(getApplicationContext());
        ip = globalPreference.RetriveIP();

        /*type = getIntent().getStringExtra("type");

        if (type.equals("rescuer"))
            url = "http://"+ ip + "/flood_rescue/rescuerRegister.php";
        else if (type.equals("user"))*/
        url = "http://"+ ip + "/flood_prediction/userRegister.php";

        init();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2000,0, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,2000,0, this);

        submitButtonTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

    }

    private void signUp() {

        Log.d(TAG, "signUp: "+lat+" "+lon);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);

                Toast.makeText(SignupActivity.this, ""+response, Toast.LENGTH_SHORT).show();

                if(response.contains("Failed")){
                    Toast.makeText(SignupActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                }
                else {

                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error);
                Toast.makeText(SignupActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("name",userNameET.getText().toString());
                params.put("phone",userPhoneNumberET.getText().toString());
                params.put("email",userEmailET.getText().toString());
                params.put("housename",userHouseNameET.getText().toString());
                params.put("houseno",userHouseNoET.getText().toString());
                params.put("taluk",userTalukET.getText().toString());
                params.put("username",userUsernameET.getText().toString());
                params.put("password",userPasswordET.getText().toString());
                params.put("latitude",lat);
                params.put("longitude",lon);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(SignupActivity.this);
        requestQueue.add(stringRequest);

    }

    private void init() {

        userNameET = (EditText) findViewById(R.id.userNameTextView);
        userPhoneNumberET = (EditText) findViewById(R.id.userPhoneNumberTextView);
        userEmailET = (EditText) findViewById(R.id.userEmailTextView);
        userHouseNameET = (EditText) findViewById(R.id.userHouseNameTextView);
        userHouseNoET = (EditText) findViewById(R.id.userHouseNumberTextView);
        userTalukET = (EditText) findViewById(R.id.userTalukTextView);
        userUsernameET = (EditText) findViewById(R.id.userUsernameTextView);
        userPasswordET = (EditText) findViewById(R.id.userPasswordTextView);
        submitButtonTV = (TextView) findViewById(R.id.submitButtonTextView);

    }

    @Override
    public void onLocationChanged(Location location) {
        lat = String.valueOf(location.getLatitude());
        lon = String.valueOf(location.getLongitude());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
