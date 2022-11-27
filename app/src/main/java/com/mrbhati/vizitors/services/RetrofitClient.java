package com.mrbhati.vizitors.services;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance = null;
    private Api myApi;

//    private RetrofitClient() {
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        myApi = retrofit.create(Api.class);
//    }



    public RetrofitClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
      //  OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        //
        OkHttpClient client = UnsafeOkHttpClient.getUnsafeOkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://myvizitors.pageinfo.in/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myApi = retrofit.create(Api.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public Api getMyApi() {
        return myApi;
    }
}