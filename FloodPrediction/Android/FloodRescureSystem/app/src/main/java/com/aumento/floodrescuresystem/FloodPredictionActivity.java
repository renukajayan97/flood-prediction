package com.aumento.floodrescuresystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.opengl.ETC1;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FloodPredictionActivity extends AppCompatActivity {

    private static final String TAG = "FloodPredictionActivity";

    EditText rainEditText, waterEditText;
    TextView resultTextView;
    private String url;
    private ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flood_prediction);

        GlobalPreference globalPreference = new GlobalPreference(this);
        String ip = globalPreference.RetriveIP();

        url = "http://"+ ip + "/flood_prediction/Prediction/py.php";

        rainEditText = findViewById(R.id.rainEditText) ;
        waterEditText = findViewById(R.id.waterEditText);
        resultTextView = findViewById(R.id.resultTextView);

        final Button predictionButton = findViewById(R.id.predictionButton);
        predictionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!rainEditText.getText().toString().equals("") || !waterEditText.getText().toString().equals(""))
                    predict();
                else
                    Toast.makeText(FloodPredictionActivity.this, "Please Fill All Options", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void predict() {

        progressDoalog = new ProgressDialog(this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Please wait while its process....");
        progressDoalog.setTitle("Prediction Process");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.setCancelable(false);
        progressDoalog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDoalog.dismiss();
                Log.d(TAG, "onResponse: "+response);
                resultTextView.setText(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error);
                progressDoalog.dismiss();
                Toast.makeText(FloodPredictionActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("rain",rainEditText.getText().toString());
                params.put("water",waterEditText.getText().toString());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(FloodPredictionActivity.this);
        requestQueue.add(stringRequest);
    }
}
