package com.mrbhati.vizitors.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.mrbhati.vizitors.MainActivity;
import com.mrbhati.vizitors.Model.Hero;
import com.mrbhati.vizitors.Model.LoginResponse;
import com.mrbhati.vizitors.Model.UserRequest;
;
import com.mrbhati.vizitors.R;
import com.mrbhati.vizitors.services.ApiInterface;
import com.mrbhati.vizitors.services.RetrofitClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login   extends AppCompatActivity {
     TextInputEditText email_et,password_et;
    Button login_btn;

    LoginResponse loginResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        email_et = (TextInputEditText) findViewById(R.id.email_et);
        password_et = (TextInputEditText) findViewById(R.id.password_et);
        login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(isValidEmailId(email_et.getText().toString().trim())){
                    loginUser();
                    //  Toast.makeText(getApplicationContext(), "Valid Email Address.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "InValid Email Address.", Toast.LENGTH_SHORT).show();
                }

        }});

    }
    private void loginUser(){

       //WORKING CODE
        UserRequest userRequest = new UserRequest(email_et.getText().toString(),password_et.getText().toString());
        Call<LoginResponse> call = RetrofitClient.getInstance().getMyApi().login(userRequest);
        call.enqueue(new Callback<LoginResponse>() {
         @Override
         public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
             Log.d("LOGIN USER","Login User Name"+response.code());

             if(response.code() == 200){
                 loginResponse = response.body();
                 Log.d("LOGIN USER","Login User Name"+loginResponse.token);
                 String login_date = getFormatDate(loginResponse.user.last_login_at);
                 String create_date = getFormatDate(loginResponse.user.created_at);
               goToLogin(login_date,create_date);

                 //Log.d("DATE FORMET",""+date);
             }else {
                 Toast.makeText(getApplicationContext(), "The given data was invalid", Toast.LENGTH_SHORT).show();
             }

         }

         @Override
         public void onFailure(Call<LoginResponse> call, Throwable t) {
             Log.d("LOGIN USER","Login User Name"+t.getMessage());
             Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
         }
     });




    }

    private void goToLogin(String last_login_date, String created_date){
        SharedPreferences pref = Login.this.getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("token", loginResponse.token);
        editor.putString("name", loginResponse.user.name);
        editor.putString("email", loginResponse.user.email);
        if(loginResponse.user.mobile != null){
            editor.putString("mobile", loginResponse.user.mobile);
        }
        editor.putString("last_login_at", last_login_date);
        editor.putString("created_at", created_date);
        editor.putString("position", loginResponse.user.position);
        editor.apply(); //
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
    }

    private boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    public static String getFormatDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
        return format.format(date);
    }

}