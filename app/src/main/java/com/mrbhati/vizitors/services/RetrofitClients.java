package com.mrbhati.vizitors.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClients {

    private String BASE_URL = "https://myvizitors.pageinfo.in/api";
    private Retrofit retrofit;
    private static RetrofitClients retrofitClient;
    private ApiInterface apiInterface;
    private RetrofitClients(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static synchronized RetrofitClients getInstance(){
        if(retrofitClient == null){
            retrofitClient = new RetrofitClients();
        }
        return retrofitClient;
    }

    public ApiInterface getMyApi() {
        return apiInterface;
    }

}
