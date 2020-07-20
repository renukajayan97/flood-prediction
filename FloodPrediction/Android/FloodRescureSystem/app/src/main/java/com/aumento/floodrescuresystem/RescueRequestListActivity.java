package com.aumento.floodrescuresystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aumento.floodrescuresystem.Adapter.RequestListAdapter;
import com.aumento.floodrescuresystem.Adapter.RequestListModelClass;
import com.aumento.floodrescuresystem.Utils.GlobalPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RescueRequestListActivity extends AppCompatActivity {

    private static final String TAG = "RescueRequestListActivi";
    private RecyclerView requestListRV;
    private List<RequestListModelClass> requestList;
    private RequestListAdapter adapter;
    private String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescue_request_list);

        GlobalPreference globalPreference = new GlobalPreference(getApplicationContext());
        String rid = globalPreference.RetriveRID();
        String ip = globalPreference.RetriveIP();
        String url = "http://" + ip + "/flood_prediction/getRescueList.php";


        requestListRV = (RecyclerView) findViewById(R.id.requestListRecylerView);
        requestListRV.setLayoutManager(new LinearLayoutManager(this));
        requestListRV.setNestedScrollingEnabled(true);
        requestListRV.setItemAnimator(new DefaultItemAnimator());

        loadRequests(url, rid);
    }

    private void loadRequests(String url, final String rid) {

        requestList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);

                if(response.contains("Failed")){
                    Toast.makeText(RescueRequestListActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                }
                else {

                    try{

                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String rid = object.getString("id");
                            String name = object.getString("name");
                            String location = object.getString("location");
                            String latitude = object.getString("latitude");
                            String longitude = object.getString("longitude");

                            requestList.add(new RequestListModelClass(rid, name, location, latitude, longitude));
                        }

                        adapter = new RequestListAdapter(RescueRequestListActivity.this,requestList);
                        requestListRV.setAdapter(adapter);


                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error);
                Toast.makeText(RescueRequestListActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("rid",rid);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(RescueRequestListActivity.this);
        requestQueue.add(stringRequest);

    }
}
