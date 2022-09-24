package com.mrbhati.vizitors.adapters;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mrbhati.vizitors.Model.Datum;
import com.mrbhati.vizitors.R;
import com.mrbhati.vizitors.utils.VistClickListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder>
{
    ArrayList<Datum> dataholder;
    private VistClickListener vistClickListener;

    public myadapter(ArrayList<Datum> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.visit_card,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position)
    {


        if(dataholder.get(position).getPicture() != "" && dataholder.get(position).getPicture() != null){
            Picasso.get().load(dataholder.get(position).getPicture()).into(holder.img);
        }
        holder.fullname.setText(dataholder.get(position).getName());
        holder.mobilenumber.setText(dataholder.get(position).getMobile());
        holder.socitey.setText(dataholder.get(position).getSociety());
        String  date = getFormatDate(dataholder.get(position).getLastVisitAt());
      holder.lastVisit.setText( "Last Visited at: "+date);
      //  holder.createdDate.setText(getFormatDate(dataholder.get(position).getCreatedAt()));
        holder.count.setText("Total Visits: "+dataholder.get(position).getTotal());
    }

    public static String getFormatDate(Date date){
        if(date == null){
            return "N?A";
        }else {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
            return format.format(date);
        }

    }
    @Override
    public int getItemCount() {
        return dataholder.size();
    }


    public void setClickListener(VistClickListener vistClickListener1) {
        this.vistClickListener = vistClickListener1;
    }
    class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img;
        TextView fullname, mobilenumber, socitey, createdDate, count, lastVisit;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            img=itemView.findViewById(R.id.visit_img);
            fullname=itemView.findViewById(R.id.visit_name);
            mobilenumber=itemView.findViewById(R.id.visit_mobile);
            socitey=itemView.findViewById(R.id.visit_society);
         //   createdDate=itemView.findViewById(R.id.visit_create_date);
            lastVisit =  itemView.findViewById(R.id.list_visit_tv);
            count=itemView.findViewById(R.id.visit_count);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            if (vistClickListener != null) vistClickListener.onClick(v, getAdapterPosition());
        }
    }
}
