package com.aumento.floodrescuresystem;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aumento.floodrescuresystem.Utils.CustomToast;
import com.aumento.floodrescuresystem.Utils.GlobalPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RescueRequestActivity extends AppCompatActivity  implements LocationListener {

    private static final String TAG = "RescueRequestActivity";

    private String uid;
    private EditText locationEditText,emergencyEditText, pplCountEditText, petsCountEditText, disabledEditText, messageEditText;
    private CheckBox needs_aid_CB1, needs_food_CB2, needs_water_CB3, needs_clothing_CB4, hazards_electrical_CB1, hazards_trees_CB2, hazards_road_CB3, hazards_others_CB4;
    private Button requestButton;
    private String url;
    StringBuilder needs;
    StringBuilder hazards;
    private String lat;
    private String lon;
    boolean tick = false;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescue_request);

        GlobalPreference globalPreference = new GlobalPreference(RescueRequestActivity.this);
        String ip = globalPreference.RetriveIP();
        uid = globalPreference.RetriveUID();
        url = "http://"+ ip + "/flood_prediction/insertRequest.php";
        init();

        view = findViewById(android.R.id.content).getRootView();

        needs = new StringBuilder();
        hazards = new StringBuilder();

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2000,0, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,2000,0, this);

        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkBox();
                sendRequest();
            }
        });

    }

    private void checkBox() {

        //Needs CheckBoxes

        if(needs_aid_CB1.isChecked())
        {
            needs.append("First Aid, ");
        }
        if(needs_food_CB2.isChecked())
        {
            needs.append("Food, ");
        }
        if(needs_water_CB3.isChecked())
        {
            needs.append("Water, ");
        }
        if(needs_clothing_CB4.isChecked())
        {
            needs.append("Clothing, ");
        }


        //Hazard CheckBoxes

        if(hazards_electrical_CB1.isChecked())
        {
            hazards.append("Electrical, ");
        }
        if(hazards_trees_CB2.isChecked())
        {
            hazards.append("Tree Falling, ");
        }
        if(hazards_road_CB3.isChecked())
        {
            hazards.append("Road Blocked, ");
        }
        if(hazards_others_CB4.isChecked())
        {
            hazards.append("Others, ");
        }

    }

    private void sendRequest() {

        Log.d("****", "sendRequest: "+needs+" \n"+hazards);

        if(locationEditText.getText().toString().equals("")) {
            locationEditText.setError("Fill the Field");
        }
        else if(emergencyEditText.getText().toString().equals("")) {
            emergencyEditText.setError("Fill the Field");
        }
        else if(needs.toString().equals("")) {
            Toast.makeText(this, "Please tick checkbox", Toast.LENGTH_SHORT).show();
        }
        else if(hazards.toString().equals("")) {
            Toast.makeText(this, "Please tick checkbox", Toast.LENGTH_SHORT).show();
        }
        else if(pplCountEditText.getText().toString().equals("0")) {
            pplCountEditText.setError("Fill the Field");
        }
        else if(messageEditText.getText().toString().equals("")) {
            messageEditText.setError("Fill the Field");
        }

        else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Log.d(TAG, "onResponse: " + response);

                    if (response.contains("Failed")) {
                        new CustomToast().Show_Toast(getApplicationContext(), view , response);
                    }
                    else if(response.contains("success"))
                    {
                        Intent intent = new Intent(RescueRequestActivity.this, RequestCompleteActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        new CustomToast().Show_Toast(getApplicationContext(), view , response);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "onErrorResponse: " + error);
                    Toast.makeText(RescueRequestActivity.this, "" + error, Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("uid", uid);
                    params.put("location", locationEditText.getText().toString());
                    params.put("emergency", emergencyEditText.getText().toString());
                    params.put("needs", needs.toString());
                    params.put("hazards", hazards.toString());
                    params.put("pplCount", pplCountEditText.getText().toString());
                    params.put("petCount", petsCountEditText.getText().toString());
                    params.put("disabledCount", disabledEditText.getText().toString());
                    params.put("message", messageEditText.getText().toString());
                    params.put("latitude", lat);
                    params.put("longitude", lon);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(RescueRequestActivity.this);
            requestQueue.add(stringRequest);

        }

    }

    private void init() {

        //EditText
        locationEditText = (EditText) findViewById(R.id.locationEditText);
        emergencyEditText = (EditText) findViewById(R.id.emergencyEditText);
        pplCountEditText = (EditText) findViewById(R.id.pplCountEditText);
        petsCountEditText = (EditText) findViewById(R.id.petsCountEditText);
        disabledEditText = (EditText) findViewById(R.id.disabledEditText);
        messageEditText = (EditText) findViewById(R.id.messageEditText);

        //Checkbox
        needs_aid_CB1 = (CheckBox) findViewById(R.id.needs_aid_CB1);
        needs_food_CB2 = (CheckBox) findViewById(R.id.needs_food_CB2);
        needs_water_CB3 = (CheckBox) findViewById(R.id.needs_water_CB3);
        needs_clothing_CB4 = (CheckBox) findViewById(R.id.needs_clothing_CB4);
        hazards_electrical_CB1 = (CheckBox) findViewById(R.id.hazards_electrical_CB1);
        hazards_trees_CB2 = (CheckBox) findViewById(R.id.hazards_trees_CB2);
        hazards_road_CB3 = (CheckBox) findViewById(R.id.hazards_road_CB3);
        hazards_others_CB4 = (CheckBox) findViewById(R.id.hazards_others_CB4);

        //Button
        requestButton = (Button) findViewById(R.id.requestButton);

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
