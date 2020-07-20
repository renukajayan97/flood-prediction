package com.aumento.floodrescuresystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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

public class RescueRequestStatusActivity extends AppCompatActivity {

    private static final String TAG = "RescueRequestStatusActi";

    LinearLayout rescuerDetails;
    TextView rescueStatusTextView, rescuerStatusTextView, rescuerNameTextView, rescuerPhoneTextView, rescuerLocationTextView;
    Button rescuerTrackButton;
    private String latitude;
    private String longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescue_request_status);

        init();

        GlobalPreference globalPreference = new GlobalPreference(RescueRequestStatusActivity.this);
        String ip = globalPreference.RetriveIP();
        String url = "http://"+ ip + "/flood_prediction/requestStatus.php";
        String uid = globalPreference.RetriveUID();
        getRequestStatus(url,uid);
    }

    private void init() {

        rescuerDetails = (LinearLayout) findViewById(R.id.rescuerDetails);
        rescuerStatusTextView = (TextView) findViewById(R.id.rescuerStatusTextView);
        rescueStatusTextView = (TextView) findViewById(R.id.rescueStatusTextView);
        rescuerNameTextView = (TextView) findViewById(R.id.rescuerNameTextView);
        rescuerPhoneTextView = (TextView) findViewById(R.id.rescuerPhoneTextView);
        rescuerLocationTextView = (TextView) findViewById(R.id.rescuerLocationTextView);
        rescuerTrackButton = (Button) findViewById(R.id.rescuerTrackButton);
        rescuerTrackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RescueRequestStatusActivity.this,RescuerTrackActivity.class);
                intent.putExtra("lat",latitude);
                intent.putExtra("lon",longitude);
                startActivity(intent);
            }
        });

    }

    private void getRequestStatus(String url, final String uid) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);

                if(response.contains("Failed")){
                    rescueStatusTextView.setText("No Requests !");
                    Toast.makeText(RescueRequestStatusActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                }
                else if(response.contains("Not Assigned")){
                    rescuerStatusTextView.setText("No");
                    Toast.makeText(RescueRequestStatusActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                }
                else {

                    try{

                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        JSONObject object = jsonArray.getJSONObject(0);
                        String uid = object.getString("id");
                        String name = object.getString("name");
                        String Lname = object.getString("Lname");
                        String phone_no = object.getString("phone_no");
                        String location = object.getString("location");
                        latitude = object.getString("latitude");
                        longitude = object.getString("longitude");


                        rescuerDetails.setVisibility(View.VISIBLE);
                        rescuerStatusTextView.setText("Yes");
                        rescuerNameTextView.setText(name+" "+Lname);
                        rescuerPhoneTextView.setText(phone_no);
                        rescuerLocationTextView.setText(location);
                        rescuerTrackButton.setVisibility(View.VISIBLE);

                    }catch (JSONException e){
                        e.printStackTrace();
                    }


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error);
                Toast.makeText(RescueRequestStatusActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("uid",uid);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(RescueRequestStatusActivity.this);
        requestQueue.add(stringRequest);

    }
}
