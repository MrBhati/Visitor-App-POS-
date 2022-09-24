package com.mrbhati.vizitors.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mrbhati.vizitors.Model.Datum;
import com.mrbhati.vizitors.Model.Visit;
import com.mrbhati.vizitors.Model.datamodel;
import com.mrbhati.vizitors.R;
import com.mrbhati.vizitors.utils.VistClickListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class VisitsAdapter extends RecyclerView.Adapter<VisitsAdapter.myviewholder>
{
    ArrayList<Visit> dataholder;
    private VistClickListener vistClickListener;
    public VisitsAdapter(ArrayList<Visit> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.visitorscard_layout,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position)
    {
        if(dataholder.get(position).getPicture() != "" && dataholder.get(position).getPicture() != null){
            Picasso.get().load(dataholder.get(position).getPicture()).into(holder.img);
        }
        holder.fullname.setText(dataholder.get(position).getVisitor());
        holder.visitCode.setText(""+dataholder.get(position).getCode());
        holder.department.setText(dataholder.get(position).getDepartment());
        holder.createdDate.setText(getFormatDate(dataholder.get(position).getCreated_at()));
        holder.designation.setText(dataholder.get(position).getDesignation());
    }

    public static String getFormatDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
        return format.format(date);
    }
    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    public void setClickListener(VistClickListener vistClickListener1) {
        this.vistClickListener = vistClickListener1;
    }

    class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView img;
        TextView fullname, visitCode, createdDate, designation , department;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            img=itemView.findViewById(R.id.img1);
            fullname=itemView.findViewById(R.id.t1);
            visitCode=itemView.findViewById(R.id.t4);
            department=itemView.findViewById(R.id.t3);
            createdDate=itemView.findViewById(R.id.visited_value);
            designation=itemView.findViewById(R.id.t5);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (vistClickListener != null) vistClickListener.onClick(v, getAdapterPosition());
        }
    }
}
