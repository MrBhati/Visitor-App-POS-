package com.mrbhati.vizitors.services;

import com.mrbhati.vizitors.Model.AddVisitModel;
import com.mrbhati.vizitors.Model.AddVisitResponse;
import com.mrbhati.vizitors.Model.DepartmentsModel;
import com.mrbhati.vizitors.Model.Hero;
import com.mrbhati.vizitors.Model.LoginResponse;
import com.mrbhati.vizitors.Model.UpdateVisitorRequest;
import com.mrbhati.vizitors.Model.UserRequest;
import com.mrbhati.vizitors.Model.VisitDetailsModel;
import com.mrbhati.vizitors.Model.VisitModel;
import com.mrbhati.vizitors.Model.VisitSearchResponse;
import com.mrbhati.vizitors.Model.VisitorDetailsModel;
import com.mrbhati.vizitors.Model.VisitorModel;
import com.mrbhati.vizitors.Model.dashboardModel;
import com.mrbhati.vizitors.Model.VisitDetailsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {



    /**
     * The return type is important here
     * The class structure that you've defined in Call<T>
     * should exactly match with your json response
     * If you are not using another api, and using the same as mine
     * then no need to worry, but if you have your own API, make sure
     * you change the return type appropriately
     **/
    @GET("marvel")
    Call<List<Hero>> getHeroes();

    @POST("login")
    @Headers({"Content-Type: application/json","Accept: application/json"})
    Call<LoginResponse> login( @Body UserRequest userRequest);

    @GET("visits")
    @Headers({"Content-Type: application/json","Accept: application/json"})
    Call<VisitModel> getVisits(@Header("Authorization") String token,@Query("page") int currentPage);


    @GET("visitors")
    @Headers({"Content-Type: application/json","Accept: application/json"})
    Call<VisitorModel> getVisitors(@Header("Authorization") String token,@Query("page") int currentPage);

    @GET("dashboard")
    @Headers({"Content-Type: application/json","Accept: application/json"})
    Call<dashboardModel> getDashboard(@Header("Authorization") String token);


    @GET("departments")
    @Headers({"Content-Type: application/json","Accept: application/json"})
    Call<List<DepartmentsModel>> getDepartments(@Header("Authorization") String token);


    @GET("visits/{id}")
    @Headers({"Content-Type: application/json","Accept: application/json"})
    Call<VisitDetailsModel> getVisitDetails(@Header("Authorization") String token, @Path("id") String id);

    @GET("visitor/{id}")
    @Headers({"Content-Type: application/json","Accept: application/json"})
    Call<VisitorDetailsModel> getVisitorDetails(@Header("Authorization") String token, @Path("id") String id);


    @POST("visit")
    @Headers({"Content-Type: application/json","Accept: application/json"})
    Call<AddVisitResponse> addVisit(@Body AddVisitModel addVisitModel,@Header("Authorization") String token);

    @POST("visitor/{id}")
    @Headers({"Content-Type: application/json","Accept: application/json"})
    Call<AddVisitResponse> updateVisitor(@Body UpdateVisitorRequest updateVisitorRequest, @Header("Authorization") String token, @Path("id") String id);


    @GET("visitor/search/{number}")
    @Headers({"Content-Type: application/json","Accept: application/json"})
    Call<VisitSearchResponse> getVisitorSearchResult(@Header("Authorization") String token, @Path("number") String id);

}