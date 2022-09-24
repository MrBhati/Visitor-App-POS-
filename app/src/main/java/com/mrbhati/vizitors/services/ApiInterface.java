package com.mrbhati.vizitors.services;

import com.mrbhati.vizitors.Model.LoginResponse;
import com.mrbhati.vizitors.Model.UserRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiInterface {

    @POST("/login")
    Call<LoginResponse> login(@Body UserRequest userRequest);
}
