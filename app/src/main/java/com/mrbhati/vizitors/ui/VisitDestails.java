package com.mrbhati.vizitors.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mrbhati.vizitors.Model.VisitDetailsModel;
import com.mrbhati.vizitors.Model.VisitorDetailsModel;
import com.mrbhati.vizitors.R;
import com.mrbhati.vizitors.services.RetrofitClient;
import com.mswipetech.wisepad.sdk.Print;
import com.socsi.smartposapi.printer.Align;
import com.socsi.smartposapi.printer.FontLattice;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisitDestails extends AppCompatActivity {

    Button edit_btn, print_btn;
    TextView name_tv, mobile_tv, visitid_tv, department_tv, designation_tv, reason_tv, visit_at_tv;
    SharedPreferences pref;
    ProgressBar spinner;
    ImageView visit_image;
    RelativeLayout visit_show_details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_details);
        String visitor_id = getIntent().getStringExtra("VISIT_ID");
        Log.d("VISIT ID", visitor_id.toString());

        name_tv = findViewById(R.id.visit_visitor_name_tv);
        visit_show_details = findViewById(R.id.visit_show_details);
        visit_image = findViewById(R.id.visit_image_test);
        visitid_tv = findViewById(R.id.visit_id_value_tv);
        department_tv = findViewById(R.id.visit_department_tv);
        designation_tv = findViewById(R.id.visit_designation_tv);
        mobile_tv = findViewById(R.id.visitor_mobile_tv);
        reason_tv = findViewById(R.id.purpose_visit_tv);
        visit_at_tv = findViewById(R.id.last_update_value_tv);
        spinner = findViewById(R.id.progressBar1);

        visit_show_details.setVisibility(View.GONE);
        pref = VisitDestails.this.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String token =pref.getString("token", "16|HIxSsdnc3u9pT1OYqacJ7a3HERGjdnTNnTPlszUK");

        getVisit(token,visitor_id);
        print_btn = findViewById(R.id.print_visit_btn);



        print_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Print.init(VisitDestails.this);
                Print.StartPrinting("VISIT ID: "+visitid_tv.getText().toString(), FontLattice.THIRTY, true, Align.CENTER, true);


//                    Bitmap icon = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.usericon);
//                    Bitmap b = Bitmap.createScaledBitmap(icon, 400, 400, false);
////                Bitmap icon = photo;
//                    Print.StartPrintingImage(b, Align.CENTER);


                Print.StartPrinting("Name of Visitor:",FontLattice.TWENTY_TWO, true, Align.LEFT, true);
                Print.StartPrinting(name_tv.getText().toString(), FontLattice.THIRTY, true, Align.LEFT, true);
                Print.StartPrinting("Mobile Number:",FontLattice.TWENTY_TWO, true, Align.LEFT, true);
                Print.StartPrinting(mobile_tv.getText().toString(), FontLattice.THIRTY, true, Align.LEFT, true);
                Print.StartPrinting("Purpose/Visit Reason:",FontLattice.TWENTY_TWO, true, Align.LEFT, true);
                Print.StartPrinting(reason_tv.getText().toString(), FontLattice.TWENTY_TWO, true, Align.LEFT, true);
                Print.StartPrinting("Visited To:",FontLattice.EIGHTEEN, true, Align.LEFT, true);

                    Print.StartPrinting(department_tv.getText().toString(), FontLattice.THIRTY, true, Align.LEFT, true);


                Print.StartPrinting("Visit Date and time:",FontLattice.TWENTY_TWO, true, Align.LEFT, true);
                //  Print.StartPrinting(visit_at_tv.getText().toString(), FontLattice.THIRTY, true, Align.LEFT, true);
                Print.StartPrinting(visit_at_tv.getText().toString(), FontLattice.THIRTY, true, Align.LEFT, true);
                Print.StartPrinting("********************************");
                Bitmap logo_icon = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.printlogo1);
                Bitmap b_print = Bitmap.createScaledBitmap(logo_icon, 180, 130, false);
                Print.StartPrintingImage(b_print, Align.CENTER);
                Print.StartPrinting("Slum Rehabilitation Authority",FontLattice.TWENTY_TWO, true, Align.CENTER, true);
                Print.StartPrinting();
                Print.StartPrinting();
                Print.StartPrinting();
                Print.StartPrinting();

            }
        });
    }

    private void getVisit(String token, String id){
        Call<VisitDetailsModel> call = RetrofitClient.getInstance().getMyApi().getVisitDetails("Bearer "+token, id);
        call.enqueue(new Callback<VisitDetailsModel>() {
            @Override
            public void onResponse(Call<VisitDetailsModel> call, Response<VisitDetailsModel> response) {

                VisitDetailsModel visitDetailsModel = response.body();
                Log.d("LOGIN USER","Visit Details"+visitDetailsModel);

                name_tv.setText(visitDetailsModel.visitor.name);
//                if(visitDetailsModel.visitor.mobile != null && visitDetailsModel.visitor.mobile != "" ){
//                    mobile_tv.setText(visitDetailsModel.visitor.mobile);
//                }


                mobile_tv.setText(visitDetailsModel.visitor.mobile);
                visitid_tv.setText(visitDetailsModel.code);
                department_tv.setText(visitDetailsModel.department.name);
                designation_tv.setText(visitDetailsModel.department.designation);
                reason_tv.setText(visitDetailsModel.purpose);
                visit_at_tv.setText(getFormatDate(visitDetailsModel.created_at));
                if(visitDetailsModel.visitor.picture != "" && visitDetailsModel.visitor.picture != null){
                    Picasso.get().load(visitDetailsModel.visitor.picture).into(visit_image);
                }
                visit_show_details.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<VisitDetailsModel> call, Throwable t) {
                Log.d("LOGIN USER","Login User Name"+t.getMessage());
                // Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }

    public static String getFormatDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
        return format.format(date);
    }

}