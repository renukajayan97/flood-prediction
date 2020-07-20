package com.aumento.floodrescuresystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aumento.floodrescuresystem.Utils.GlobalPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RequestDetailsActivity extends AppCompatActivity {

    private static final String TAG = "RequestDetailsActivity";

    TextView requestNameTextView, requestPhoneTextView, requestLocationTextView, requestEmergencyTextView, requestNeedsTextView;
    TextView requestHazardsTextView, requestPeopleTextView, requestPetsTextView, requestDisabledTextView, requestMessageTextView;
    Button requestTrackLocationBT;
    private String latitude;
    private String longitude;
    private String reqId;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details);

        GlobalPreference globalPreference = new GlobalPreference(RequestDetailsActivity.this);
        String ip = globalPreference.RetriveIP();
        String url = "http://"+ ip + "/flood_prediction/getRescueDetails.php";

        final Intent intent = getIntent();
        reqId = intent.getStringExtra("reqId");
        init();

        loadRequestDetails(url);

        requestTrackLocationBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("****", "onClick: "+latitude+" "+longitude);

                Intent intent1 = new Intent(RequestDetailsActivity.this, TrackVictimActivity.class);
                intent1.putExtra("lat",latitude);
                intent1.putExtra("lon",longitude);
                intent1.putExtra("uid",uid);
                startActivity(intent1);
            }
        });
    }

    private void loadRequestDetails(String url) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);

                if(response.contains("Failed")){
                    Toast.makeText(RequestDetailsActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                }
                else {

                    try{

                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        JSONObject object = jsonArray.getJSONObject(0);
                        uid = object.getString("id");
                        String name = object.getString("name");
                        String phone = object.getString("phone");
                        String location = object.getString("location");
                        String emergency = object.getString("emergency");
                        String needs = object.getString("needs");
                        String hazards = object.getString("hazards");
                        String ppl_count = object.getString("ppl_count");
                        String pets_count = object.getString("pets_count");
                        String disabled_count = object.getString("disabled_count");
                        String message = object.getString("message");
                        latitude = object.getString("latitude");
                        longitude = object.getString("longitude");

                        Log.d("****", "onResponse: "+latitude+" "+longitude);


                        requestNameTextView.setText(name);
                        requestPhoneTextView.setText(phone);
                        requestLocationTextView.setText(location);
                        requestEmergencyTextView.setText(emergency);
                        requestNeedsTextView.setText(needs);
                        requestHazardsTextView.setText(hazards);
                        requestPeopleTextView.setText(ppl_count);
                        requestPetsTextView.setText(pets_count);
                        requestDisabledTextView.setText(disabled_count);
                        requestMessageTextView.setText(message);


                    }catch (JSONException e){
                        e.printStackTrace();
                    }


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error);
                Toast.makeText(RequestDetailsActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("reqId",reqId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(RequestDetailsActivity.this);
        requestQueue.add(stringRequest);

    }

    private void init() {

        requestNameTextView = (TextView) findViewById(R.id.requestNameTextView);
        requestPhoneTextView = (TextView) findViewById(R.id.requestPhoneTextView);
        requestLocationTextView = (TextView) findViewById(R.id.requestLocationTextView);
        requestEmergencyTextView = (TextView) findViewById(R.id.requestEmergencyTextView);
        requestNeedsTextView = (TextView) findViewById(R.id.requestNeedsTextView);
        requestHazardsTextView = (TextView) findViewById(R.id.requestHazardsTextView);
        requestPeopleTextView = (TextView) findViewById(R.id.requestPeopleTextView);
        requestPetsTextView = (TextView) findViewById(R.id.requestPetsTextView);
        requestDisabledTextView = (TextView) findViewById(R.id.requestDisabledTextView);
        requestMessageTextView = (TextView) findViewById(R.id.requestMessageTextView);
        requestTrackLocationBT = (Button) findViewById(R.id.requestTrackLocationButton);

    }
}
