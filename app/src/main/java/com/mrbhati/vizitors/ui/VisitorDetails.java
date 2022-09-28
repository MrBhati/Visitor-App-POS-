package com.mrbhati.vizitors.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mrbhati.vizitors.Model.AddVisitModel;
import com.mrbhati.vizitors.Model.VisitorDetailsModel;
import com.mrbhati.vizitors.Model.VisitorModel;
import com.mrbhati.vizitors.R;
import com.mrbhati.vizitors.adapters.myadapter;
import com.mrbhati.vizitors.services.RetrofitClient;
import com.mrbhati.vizitors.ui.notifications.NotificationsFragment;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitorDetails  extends AppCompatActivity {


    SharedPreferences pref;
    TextView name_tv, mobile_tv, society_tv, total_count_tv, last_updated_tv, created_tv;
    ProgressBar spinner;
    ImageView visitor_image;
    RelativeLayout relativeLayout;
    Button edit_btn;
    String visitor_id;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_details);
        visitor_id = getIntent().getStringExtra("VISITOR_ID");
        Log.d("VISITOR ID", visitor_id.toString());
        relativeLayout = findViewById(R.id.visitor_data);
        edit_btn = findViewById(R.id.visitor_edti_btn);
        spinner = findViewById(R.id.progressBar_visitor);
        name_tv = findViewById(R.id.visitor_name_tv);
        mobile_tv = findViewById(R.id.visitor_mobile_tv);
        visitor_image = findViewById(R.id.visitor_image);
//        email_tv = findViewById(R.id.visitor_email_tv);
        society_tv = findViewById(R.id.visitor_society_tv);
        total_count_tv = findViewById(R.id.totol_visit_tv);
        created_tv = findViewById(R.id.created_value_tv);
        last_updated_tv = findViewById(R.id.last_update_value_tv);
        relativeLayout.setVisibility(View.GONE);

        pref = VisitorDetails.this.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        token=pref.getString("token", "");


        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisitorDetails.this, EditVisitor.class);
                intent.putExtra("VISITOR_ID", visitor_id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        relativeLayout.setVisibility(View.GONE);
        spinner.setVisibility(View.VISIBLE);
        getVisitor(token,visitor_id);
        Log.d("WELCOME BACK","RESTART ACTIVITY");

    }



    private void getVisitor(String token, String id){

            Call<VisitorDetailsModel> call = RetrofitClient.getInstance().getMyApi().getVisitorDetails("Bearer "+token, id);
        call.enqueue(new Callback<VisitorDetailsModel>() {
            @Override
            public void onResponse(Call<VisitorDetailsModel> call, Response<VisitorDetailsModel> response) {

                VisitorDetailsModel visitorDetailsModel = response.body();
                Log.d("LOGIN USER","Login User Name"+visitorDetailsModel);



                name_tv.setText(visitorDetailsModel.name);
                mobile_tv.setText(visitorDetailsModel.mobile);
//                email_tv.setText(visitorDetailsModel.email);
                society_tv.setText(visitorDetailsModel.society);
                total_count_tv.setText("Total Visit: "+visitorDetailsModel.total);
                last_updated_tv.setText(getFormatDate(visitorDetailsModel.last_visit_at));
                created_tv.setText(getFormatDate(visitorDetailsModel.created_at));
               Log.d("IMAGE URL",""+visitorDetailsModel.picture);
                if(visitorDetailsModel.picture != "" && visitorDetailsModel.picture != null){
                    Picasso.get().load(visitorDetailsModel.picture).into(visitor_image);
                }
                relativeLayout.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<VisitorDetailsModel> call, Throwable t) {
                Log.d("LOGIN USER","Login User Name"+t.getMessage());
                // Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }
    public static String getFormatDate(Date date){
        if(date!= null){
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
            return format.format(date);
        }else {
            return "N/A";
        }


    }

}