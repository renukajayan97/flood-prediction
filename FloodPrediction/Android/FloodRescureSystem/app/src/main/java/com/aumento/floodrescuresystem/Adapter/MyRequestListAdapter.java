package com.aumento.floodrescuresystem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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
import com.aumento.floodrescuresystem.MyRequestListActivity;
import com.aumento.floodrescuresystem.R;
import com.aumento.floodrescuresystem.RequestDetailsActivity;
import com.aumento.floodrescuresystem.Utils.GlobalPreference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyRequestListAdapter extends RecyclerView.Adapter<MyRequestListAdapter.MyViewHolder> {

    Context mtx;
    List<RequestListModelClass> requestList;

    public MyRequestListAdapter(Context mtx, List<RequestListModelClass> requestList) {
        this.mtx = mtx;
        this.requestList = requestList;
    }

    @NonNull
    @Override
    public MyRequestListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.raw_my_request, parent, false);
        return new MyRequestListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyRequestListAdapter.MyViewHolder holder, final int position) {
        final RequestListModelClass lists = requestList.get(position);

        holder.victimNameTV.setText(lists.getName());
        holder.victimLocationTV.setText(lists.getLocation());

        final String reqId = lists.getId();

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mtx, RequestDetailsActivity.class);
                intent.putExtra("reqId",reqId);
                mtx.startActivity(intent);
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
        LinearLayout ll;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            victimNameTV = itemView.findViewById(R.id.victimNameTextView);
            victimLocationTV = itemView.findViewById(R.id.victimLocationTextView);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
