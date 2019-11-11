package com.example.milestone2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    public ArrayList<String> reportList;

    public RecyclerViewAdapter(ArrayList<String> reportList) {
        this.reportList = reportList;
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
        holder.reportTitle.setText(bloodReportTitle);
        //Position is bind to the item, so that onClickListener knows which item is clicked
        //Used later to retrieve the clicked sensor
        holder.myPosition = position;
    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    //ViewHolder Class for each item
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //Variables
        TextView reportTitle;

        //myPosition represents the position of this viewHolder within the RecyclerView
        public int myPosition;

        //Context used for Intents
        Context context;

        //Constructor class
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            reportTitle = itemView.findViewById(R.id.reportTitle);
            context = itemView.getContext();

            //Setting up OnClickListener, to prepare for user to click on each viewHolder
            itemView.setOnClickListener(this);
        }

        //OnClick method called when viewHolder is clicked
        @Override
        public void onClick(View v) {
            //Send user to the detail page with the position of the clicked viewHolder as Extra
            Intent ToSensorDetail = new Intent(context, ReportActivity.class);
            ToSensorDetail.putExtra("position", myPosition);
            context.startActivity(ToSensorDetail);
        }
    }
}
