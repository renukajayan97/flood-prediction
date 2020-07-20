package com.aumento.floodrescuresystem.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aumento.floodrescuresystem.R;
import com.aumento.floodrescuresystem.Utils.GlobalPreference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestListAdapter extends RecyclerView.Adapter<RequestListAdapter.MyViewHolder> {

    Context mtx;
    List<RequestListModelClass> requestList;

    public RequestListAdapter(Context mtx, List<RequestListModelClass> requestList) {
        this.mtx = mtx;
        this.requestList = requestList;
    }

    @NonNull
    @Override
    public RequestListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.raw_incoming_request, parent, false);
        return new RequestListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RequestListAdapter.MyViewHolder holder, final int position) {
        final RequestListModelClass lists = requestList.get(position);

        GlobalPreference globalPreference = new GlobalPreference(mtx);
        final String IP = globalPreference.RetriveIP();
        final String rid = globalPreference.RetriveRID();

        holder.victimNameTV.setText(lists.getName());
        holder.victimLocationTV.setText(lists.getLocation());

        final String reqId = lists.getId();

        holder.requestAcceptBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://"+IP+"/flood_prediction/acceptRequest.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equals("sucess"))
                            holder.requestAcceptBT.setVisibility(View.GONE);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mtx, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }){

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        params.put("rid", String.valueOf(rid));
                        params.put("reqId", reqId);
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(mtx);
                requestQueue.add(stringRequest);

            }
        });

    }

    @Override
    public int getItemCount() {
        Log.d("TAG", "getItemCount: "+requestList.size());
        return requestList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView victimNameTV, victimLocationTV;
        Button requestAcceptBT;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            victimNameTV = itemView.findViewById(R.id.victimNameTextView);
            victimLocationTV = itemView.findViewById(R.id.victimLocationTextView);
            requestAcceptBT = itemView.findViewById(R.id.requestAcceptButton);
        }
    }
}
