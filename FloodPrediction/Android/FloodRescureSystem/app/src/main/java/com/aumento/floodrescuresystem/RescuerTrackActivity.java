package com.aumento.floodrescuresystem;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aumento.floodrescuresystem.Utils.GlobalPreference;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.HashMap;
import java.util.Map;

public class RescuerTrackActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "RescuerTrackActivity";

    private GoogleMap mMap;
    private LatLng rescuerLatLon;
    private Button rescuedButton;
    private String uid;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescuer_track);

        GlobalPreference globalPreference = new GlobalPreference(RescuerTrackActivity.this);
        String ip = globalPreference.RetriveIP();
        uid = globalPreference.RetriveUID();
        url = "http://"+ ip + "/flood_prediction/updateStatus.php";

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        double lat = Double.parseDouble(intent.getStringExtra("lat"));
        double lon = Double.parseDouble(intent.getStringExtra("lon"));
        rescuerLatLon = new LatLng(lat, lon);

        rescuedButton = (Button) findViewById(R.id.rescuedButton);
        rescuedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateStatus();

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.addMarker(new MarkerOptions().position(rescuerLatLon).title("Rescuer").icon(BitmapDescriptorFactory.fromResource(R.drawable.rescuer)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(rescuerLatLon));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(rescuerLatLon, 18.0f));

    }

    private void updateStatus() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error);
                Toast.makeText(RescuerTrackActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("uid",uid);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(RescuerTrackActivity.this);
        requestQueue.add(stringRequest);

    }
}
