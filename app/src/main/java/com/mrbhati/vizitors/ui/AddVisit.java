package com.mrbhati.vizitors.ui;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.mrbhati.vizitors.Model.AddVisitModel;
import com.mrbhati.vizitors.Model.AddVisitResponse;
import com.mrbhati.vizitors.Model.DepartmentsModel;
import com.mrbhati.vizitors.Model.LoginResponse;
import com.mrbhati.vizitors.Model.UserRequest;
import com.mrbhati.vizitors.Model.VisitorModel;
import com.mrbhati.vizitors.Model.dashboardModel;
import com.mrbhati.vizitors.R;
import com.mrbhati.vizitors.adapters.myadapter;
import com.mrbhati.vizitors.services.RetrofitClient;
import com.mrbhati.vizitors.ui.notifications.NotificationsFragment;
import com.mswipetech.wisepad.sdk.Print;
import com.socsi.smartposapi.printer.Align;
import com.socsi.smartposapi.printer.FontLattice;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddVisit extends AppCompatActivity {

    // Define the pic id
    private static final int pic_id = 123;
    AutoCompleteTextView autoCompleteTextView;
    // Define the button and imageview type variable
    Button camera_open_id, print_btn, reset_btn, save_print_btn;
    ImageView add_image;
    ProgressBar spinner;
    RelativeLayout relativeLayout;
    private TextInputEditText name_et,number_et,society_et,purpose_et;
    Bitmap photo;
    CircleImageView click_image_id;
    ArrayList<String> departmentsNameList = new ArrayList<String>();
    ArrayList<String> departmentsIdList = new ArrayList<String>();
    List<DepartmentsModel> departmentsList;
    String departmentID;
    String departmentSelected;
String datetime;
    String imageString;
    SharedPreferences pref;
    ArrayAdapter<String> adapter;
    String token, PhotoUrl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = AddVisit.this.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        token =pref.getString("token", "");
        setContentView(R.layout.activity_add_visit);
        autoCompleteTextView = findViewById(R.id.AutoCompleteTextview);
        reset_btn = findViewById(R.id.reset_btn);
        relativeLayout = findViewById(R.id.form_ui);
        spinner = findViewById(R.id.progressBar_add_visit);
spinner.setVisibility(View.GONE);
        save_print_btn = findViewById(R.id.save_btn);
        add_image = findViewById(R.id.add_image_icon);
        click_image_id = findViewById(R.id.visit_image_camera);
        name_et = findViewById(R.id.name_et_et);
        number_et = findViewById(R.id.mobile_et_et);
        society_et = findViewById(R.id.society_et_et);
        purpose_et = findViewById(R.id.purpose_et_et);



        getDepartments();
        Date todaysdate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        datetime = format.format(todaysdate);


        adapter = new ArrayAdapter<String>(this, R.layout.dropdown_item, departmentsNameList);
        autoCompleteTextView.setAdapter(adapter);


        //to get selected value add item click listener
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //  Toast.makeText(getApplicationContext(), "" + departmentsIdList.get(position), Toast.LENGTH_SHORT).show();
                departmentID = departmentsIdList.get(position);
departmentSelected = departmentsNameList.get(position);
            }
        });



        // By ID we can get each component which id is assigned in XML file get Buttons and imageview.
//        camera_open_id = findViewById(R.id.camera_button);
//        print_btn = findViewById(R.id.print_button);
//        click_image_id = findViewById(R.id.click_image);

        // Camera_open button is for open the camera and add the setOnClickListener in this button

        add_image.setOnClickListener(v -> {
            // Create the camera_intent ACTION_IMAGE_CAPTURE it will open the camera for capture the image
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Start the activity with camera_intent, and request pic id
            startActivityForResult(camera_intent, pic_id);
        });
reset_btn.setOnClickListener(new View.OnClickListener(){

    @Override
    public void onClick(View v) {
        reset();
    }
});


        save_print_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                relativeLayout.setVisibility(View.GONE);
                spinner.setVisibility(View.VISIBLE);
                if(imageString != null){
                    PhotoUrl = "data:image/png;base64,"+imageString;
                }
                AddVisitModel addVisitModel = new AddVisitModel(name_et.getText().toString(),
                        number_et.getText().toString(),
                        society_et.getText().toString(),
                        departmentID,
                        purpose_et.getText().toString(),
                        PhotoUrl);

                addVisit(addVisitModel);



            }
        });
    }

    private void addVisit(AddVisitModel addVisitModel){

        //WORKING CODE
        Call<AddVisitResponse> call = RetrofitClient.getInstance().getMyApi().addVisit(addVisitModel, "Bearer "+token);
        call.enqueue(new Callback<AddVisitResponse>() {
            @Override
            public void onResponse(Call<AddVisitResponse> call, Response<AddVisitResponse> response) {
                Log.d("LOGIN USER","Login User Name"+response.code());

                if(response.code() == 200){
                    AddVisitResponse addVisitResponse = response.body();
                    Log.d("LOGIN USER","Login User Name"+addVisitResponse.id);
                    printRecipt(addVisitResponse.code);
                    relativeLayout.setVisibility(View.VISIBLE);
                    spinner.setVisibility(View.GONE);
                    reset();
                }else {
                    Toast.makeText(getApplicationContext(), "The given data was invalid", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AddVisitResponse> call, Throwable t) {
                Log.d("LOGIN USER","Login User Name"+t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void printRecipt(String code){
        Print.init(AddVisit.this);
        Print.StartPrinting("VISIT ID: "+code, FontLattice.THIRTY, true, Align.CENTER, true);
//        Print.StartPrinting();
//        if(photo != null){
//            Bitmap b = Bitmap.createScaledBitmap(photo, 410, 550, false);
//            Print.StartPrintingImage(b, Align.CENTER);
//        }else{
//            Bitmap icon = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.usericon);
//            Bitmap b = Bitmap.createScaledBitmap(icon, 400, 400, false);
////                Bitmap icon = photo;
//            Print.StartPrintingImage(b, Align.CENTER);
//        }
//        Print.StartPrinting();
        Print.StartPrinting("Name of Visitor:",FontLattice.TWENTY_TWO, true, Align.LEFT, true);
        Print.StartPrinting(name_et.getText().toString(), FontLattice.THIRTY, true, Align.LEFT, true);
        Print.StartPrinting("Mobile Number:",FontLattice.TWENTY_TWO, true, Align.LEFT, true);
        Print.StartPrinting(number_et.getText().toString(), FontLattice.THIRTY, true, Align.LEFT, true);
        Print.StartPrinting("Purpose/Visit Reason:",FontLattice.TWENTY_TWO, true, Align.LEFT, true);
        Print.StartPrinting(purpose_et.getText().toString(), FontLattice.TWENTY_TWO, true, Align.LEFT, true);
        Print.StartPrinting("Visited To:",FontLattice.TWENTY_TWO, true, Align.LEFT, true);
        if(departmentSelected != null){
            Print.StartPrinting(departmentSelected.toString(), FontLattice.THIRTY, true, Align.LEFT, true);
        }

        Print.StartPrinting("Visit Date and time:",FontLattice.TWENTY_TWO, true, Align.LEFT, true);
        //  Print.StartPrinting(visit_at_tv.getText().toString(), FontLattice.THIRTY, true, Align.LEFT, true);
        Print.StartPrinting(datetime+"", FontLattice.THIRTY, true, Align.LEFT, true);
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

    private void getDepartments(){
        Call<List<DepartmentsModel>> call = RetrofitClient.getInstance().getMyApi().getDepartments("Bearer "+token);
        call.enqueue(new Callback<List<DepartmentsModel>>() {

            @Override
            public void onResponse(Call<List<DepartmentsModel>> call, Response<List<DepartmentsModel>> response) {

                departmentsList = response.body();
                Log.d("LOGIN USER","Login User Name"+departmentsList.size()+"");

                for(int i=0; departmentsList.size() > i; i++){
                    Log.d("LOGIN USER","Count : "+i+"");
                    departmentsNameList.add(departmentsList.get(i).name+" ("+departmentsList.get(i).designation+")");
                    departmentsIdList.add(departmentsList.get(i).id);
                    Log.d("LOGIN USER","LIST : "+departmentsNameList+"");
                }

            }

            @Override
            public void onFailure(Call<List<DepartmentsModel>> call, Throwable t) {
                Log.d("LOGIN USER","Login User Name"+t.getMessage());
                // Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }




    // This method will help to retrieve the image
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Match the request 'pic id with requestCode
        if (requestCode == pic_id) {
            // BitMap is data structure of image file which store the image in memory
            photo = (Bitmap) data.getExtras().get("data");
            // Set the image in imageview for display
            if(photo != null){
                click_image_id.setImageBitmap(photo);
            }
             imageString = convertBase(photo);
            Log.d("IMAGE STRING", imageString);

        }
    }
    public static String convertBase(Bitmap bitmap)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }

    public void reset()
    {
       name_et.setText("");
        name_et.setText("");
        society_et.setText("");
        purpose_et.setText("");
        number_et.setText("");
        name_et.setText("");
        autoCompleteTextView.setCompletionHint("Select Department");
        click_image_id.setImageDrawable(getResources().getDrawable(R.drawable.usericon));





    }

    public static String getFormatDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
        return format.format(date);
    }

}