package com.example.milestone2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    public ArrayList<String> reportList;
    public String currentSessionUsername;

    public RecyclerViewAdapter(ArrayList<String> reportList, String currentSessionUsername) {
        this.reportList = reportList;
        this.currentSessionUsername = currentSessionUsername;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String bloodReportTitle = reportList.get(position);
        //Gonna have to do some splitToken afterwards here
        String[] split = bloodReportTitle.split(",");
        holder.reportTitle.setText(currentSessionUsername);
        holder.reportDate.setText(split[2]);
        //Position is bind to the item, so that onClickListener knows which item is clicked
        //Used later to retrieve the clicked sensor
        holder.myPosition = position;
        holder.UID = split[0];

    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    //ViewHolder Class for each item
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //Variables
        TextView reportTitle, reportDate;

        //myPosition represents the position of this viewHolder within the RecyclerView
        public int myPosition;

        //time String used to retrieve a suitable report
        public String UID;

        //Context used for Intents
        Context context;

        //Constructor class
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            reportTitle = itemView.findViewById(R.id.reportTitle);
            reportDate = itemView.findViewById(R.id.reportDate);
            context = itemView.getContext();

            //Setting up OnClickListener, to prepare for user to click on each viewHolder
            itemView.setOnClickListener(this);
        }

        //OnClick method called when viewHolder is clicked
        @Override
        public void onClick(View v) {
            //Send user to the detail page with the position of the clicked viewHolder as Extra
            Intent ToBloodReportDetail = new Intent(context, ReportActivity.class);
            ToBloodReportDetail.putExtra("position", myPosition);
            ToBloodReportDetail.putExtra("uid", UID);
            context.startActivity(ToBloodReportDetail);
        }
    }
}
