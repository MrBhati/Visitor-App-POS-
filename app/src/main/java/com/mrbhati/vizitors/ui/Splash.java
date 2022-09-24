package com.mrbhati.vizitors.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.mrbhati.vizitors.MainActivity;
import com.mrbhati.vizitors.Model.LoginResponse;
import com.mrbhati.vizitors.Model.UserRequest;
import com.mrbhati.vizitors.R;
import com.mrbhati.vizitors.services.RetrofitClient;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Splash extends AppCompatActivity {


    private static int SPLASH_SCREEN_TIME_OUT=2000;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
       getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        pref = Splash.this.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String token =pref.getString("token", "");


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("TOKEN","Token:" +token);
                if(token != ""){
                    Intent i=new Intent(Splash.this,
                            MainActivity.class);
                    startActivity(i);
                }else{
                    Intent i=new Intent(Splash.this,
                            Login.class);
                    startActivity(i);
                }

                finish();
            }
        }, SPLASH_SCREEN_TIME_OUT);


    }
}