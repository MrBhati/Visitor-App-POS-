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
        import android.widget.Spinner;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;

        import com.google.android.material.textfield.TextInputEditText;
        import com.mrbhati.vizitors.Model.AddVisitModel;
        import com.mrbhati.vizitors.Model.AddVisitResponse;
        import com.mrbhati.vizitors.Model.DepartmentsModel;
        import com.mrbhati.vizitors.Model.LoginResponse;
        import com.mrbhati.vizitors.Model.UpdateVisitorRequest;
        import com.mrbhati.vizitors.Model.UserRequest;
        import com.mrbhati.vizitors.Model.VisitorDetailsModel;
        import com.mrbhati.vizitors.Model.VisitorModel;
        import com.mrbhati.vizitors.Model.dashboardModel;
        import com.mrbhati.vizitors.R;
        import com.mrbhati.vizitors.adapters.myadapter;
        import com.mrbhati.vizitors.services.RetrofitClient;
        import com.mrbhati.vizitors.ui.notifications.NotificationsFragment;
        import com.mrbhati.vizitors.utils.InternetConnection;
        import com.mswipetech.wisepad.sdk.Print;
        import com.socsi.smartposapi.printer.Align;
        import com.socsi.smartposapi.printer.FontLattice;
        import com.squareup.picasso.Picasso;

        import java.io.ByteArrayOutputStream;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.List;

        import de.hdodenhof.circleimageview.CircleImageView;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

public class EditVisitor extends AppCompatActivity {
    private static final int pic_id = 123;
    // Define the pic id
    Button reset_btn, update_btn;
    ImageView add_image;
    RelativeLayout relativeLayout;
    ProgressBar spinner;
    private TextInputEditText name_et,number_et,society_et;
    Bitmap photo;
    CircleImageView click_image_id;
    String datetime;
    String imageString;
    SharedPreferences pref;
    String token;
    String PhotoUrl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String visitor_id = getIntent().getStringExtra("VISITOR_ID");
        Log.d("VISITOR ID", visitor_id.toString());
        pref = EditVisitor.this.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        token =pref.getString("token", "");
        setContentView(R.layout.activity_edit_visitor);
        relativeLayout = findViewById(R.id.update_data);
        spinner = findViewById(R.id.progressBar_update);
        relativeLayout.setVisibility(View.GONE);

        reset_btn = findViewById(R.id.reset_update_btn);
        update_btn = findViewById(R.id.update_btn);

        add_image = findViewById(R.id.add_edit_image_icon);
        click_image_id = findViewById(R.id.visitor_update_image_camera);

        name_et = findViewById(R.id.name_edit_et_et);
        number_et = findViewById(R.id.mobile_edit_et_et);
        society_et = findViewById(R.id.society_edit_et_et);



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
        getVisitor(token,visitor_id);
        update_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                relativeLayout.setVisibility(View.GONE);
                spinner.setVisibility(View.VISIBLE);
                if(imageString != null){
                    PhotoUrl = "data:image/png;base64,"+imageString;
                }
                UpdateVisitorRequest updateVisitorRequest = new UpdateVisitorRequest(
                        name_et.getText().toString(),
                        number_et.getText().toString(),
                        society_et.getText().toString(),
                        PhotoUrl
                );


                if (InternetConnection.checkConnection(EditVisitor.this)) {
                   // Toast.makeText(EditVisitor.this, "Internet Available...", Toast.LENGTH_SHORT).show();
                    // Internet Available...
                    updateVisitor(updateVisitorRequest, visitor_id);
                } else {
                    Toast.makeText(EditVisitor.this, "Internet Not Available", Toast.LENGTH_SHORT).show();
                    // Internet Not Available...
                }

            }
        });

    }




    private void updateVisitor(UpdateVisitorRequest updateVisitorRequest, String id){

        //WORKING CODE
        Call<AddVisitResponse> call = RetrofitClient.getInstance().getMyApi().updateVisitor(updateVisitorRequest, "Bearer "+token, id);
        call.enqueue(new Callback<AddVisitResponse>() {
            @Override
            public void onResponse(Call<AddVisitResponse> call, Response<AddVisitResponse> response) {
                Log.d("LOGIN USER","Login User Name"+response.code());

                if(response.code() == 200){
                    AddVisitResponse addVisitResponse = response.body();
                    Toast.makeText(getApplicationContext(), "Update Visitor Details Successfully ", Toast.LENGTH_SHORT).show();
                    Log.d("LOGIN USER","Login User Name"+addVisitResponse.id);
//                    goToLogin();
                }else {
                    Toast.makeText(getApplicationContext(), "The given data was invalid", Toast.LENGTH_SHORT).show();
                }

                relativeLayout.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<AddVisitResponse> call, Throwable t) {
                Log.d("LOGIN USER","Login User Name"+t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
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
        society_et.setText("");

    }



    private void getVisitor(String token, String id){

        Call<VisitorDetailsModel> call = RetrofitClient.getInstance().getMyApi().getVisitorDetails("Bearer "+token, id);
        call.enqueue(new Callback<VisitorDetailsModel>() {
            @Override
            public void onResponse(Call<VisitorDetailsModel> call, Response<VisitorDetailsModel> response) {

                VisitorDetailsModel visitorDetailsModel = response.body();
                Log.d("LOGIN USER","Login User Name"+visitorDetailsModel);


                name_et.setText(visitorDetailsModel.name);
                number_et.setText(visitorDetailsModel.mobile);
//                email_tv.setText(visitorDetailsModel.email);
                society_et.setText(visitorDetailsModel.society);

                if(visitorDetailsModel.picture != "" && visitorDetailsModel.picture != null){
                    Picasso.get().load(visitorDetailsModel.picture).into(click_image_id);
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

}