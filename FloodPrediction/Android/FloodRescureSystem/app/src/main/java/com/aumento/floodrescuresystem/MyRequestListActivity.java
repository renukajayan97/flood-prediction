package com.aumento.floodrescuresystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aumento.floodrescuresystem.Adapter.MyRequestListAdapter;
import com.aumento.floodrescuresystem.Adapter.RequestListAdapter;
import com.aumento.floodrescuresystem.Adapter.RequestListModelClass;
import com.aumento.floodrescuresystem.Utils.GlobalPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyRequestListActivity extends AppCompatActivity {

    private static final String TAG = "MyRequestListActivity";
    RecyclerView myRequestListRV;
    private List<RequestListModelClass> requestList;
    private MyRequestListAdapter adapter;
    private String rid;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_request_list);

        GlobalPreference globalPreference = new GlobalPreference(MyRequestListActivity.this);
        String ip = globalPreference.RetriveIP();
        rid = globalPreference.RetriveRID();
        url = "http://"+ ip + "/flood_prediction/myRescueList.php";

        myRequestListRV = (RecyclerView) findViewById(R.id.myRequestListRecyclerView);
        myRequestListRV.setLayoutManager(new LinearLayoutManager(this));
        myRequestListRV.setNestedScrollingEnabled(true);
        myRequestListRV.setItemAnimator(new DefaultItemAnimator());

        loadList(url, rid);
    }

    private void loadList(String url, final String rid) {

        requestList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);

                if(response.contains("Failed")){
                    Toast.makeText(MyRequestListActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                }
                else {

                    try {

                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String rid = object.getString("id");
                            String name = object.getString("name");
                            String location = object.getString("location");
                            String latitude = object.getString("latitude");
                            String longitude = object.getString("longitude");

                            Log.d("****", "onResponse: "+latitude+" "+longitude);

                            requestList.add(new RequestListModelClass(rid, name, location, latitude, longitude));
                        }

                        adapter = new MyRequestListAdapter(MyRequestListActivity.this, requestList);
                        myRequestListRV.setAdapter(adapter);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error);
                Toast.makeText(MyRequestListActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("rid",rid);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(MyRequestListActivity.this);
        requestQueue.add(stringRequest);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadList(url, rid);
    }
}
