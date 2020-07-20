package com.aumento.floodrescuresystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import java.util.PriorityQueue;

public class LoginActivity extends AppCompatActivity {


    private static final String TAG = "LoginActivity";

    private LinearLayout loginSignUpTextLL;
    private EditText loginUsernameET;
    private EditText loginPasswordET;
    private Button loginButton;
    private GlobalPreference globalPreference;
    private String url;
    private String type;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        type = getIntent().getStringExtra("type");

        init();

        globalPreference = new GlobalPreference(LoginActivity.this);
        String ip = globalPreference.RetriveIP();

        if (type.equals("rescuer"))
            url = "http://"+ ip + "/flood_prediction/rescuerlogin.php";
        else if (type.equals("user"))
            url = "http://"+ ip + "/flood_prediction/userLogin.php";

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
//                startActivity(intent);
            }
        });

        if(type.equals("rescuer"))
            loginSignUpTextLL.setVisibility(View.GONE);

        loginSignUpTextLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
               /* if (type.equals("rescuer"))
                    intent.putExtra("type","rescuer");
                else if (type.equals("user"))
                    intent.putExtra("type","user");*/
                startActivity(intent);
            }
        });
    }

    private void login() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);

                if(response.contains("Failed")){
                    Toast.makeText(LoginActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                }
                else {

                    try{

                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        JSONObject object = jsonArray.getJSONObject(0);
                        String uid = object.getString("id");
                        String name = object.getString("name");

                        if (type.equals("user")) {
                            globalPreference.addUID(uid);
                            intent = new Intent(LoginActivity.this, UserHomeActivity.class);
                        }
                        else if (type.equals("rescuer")) {
                            globalPreference.addRID(uid);
                            intent = new Intent(LoginActivity.this, RescuerHomeActivity.class);
                        }

                        startActivity(intent);

                    }catch (JSONException e){
                        e.printStackTrace();
                    }


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error);
                Toast.makeText(LoginActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("username",loginUsernameET.getText().toString());
                params.put("password",loginPasswordET.getText().toString());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        requestQueue.add(stringRequest);
    }

    private void init() {

        loginUsernameET = (EditText) findViewById(R.id.loginUsernameEditText);
        loginPasswordET = (EditText) findViewById(R.id.loginPasswordEditText);
        loginButton = (Button) findViewById(R.id.loginButton);
        loginSignUpTextLL = (LinearLayout) findViewById(R.id.loginSignUpTextLL);

    }

}
