package com.mrbhati.vizitors.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mrbhati.vizitors.Model.VisitModel;
import com.mrbhati.vizitors.Model.dashboardModel;
import com.mrbhati.vizitors.R;
import com.mrbhati.vizitors.adapters.VisitsAdapter;
import com.mrbhati.vizitors.services.RetrofitClient;
import com.mrbhati.vizitors.ui.AddVisit;
import com.mrbhati.vizitors.ui.dashboard.DashboardFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    Button addVisitBtn;
    ProgressBar progressBar;
    RelativeLayout relativeLayout;
    TextView today_visit_count_tv,week_visit_count_tv,total_visit_count_tv,totol_visitors_count_tv;
    SharedPreferences pref;
    String token;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);

        addVisitBtn = view.findViewById(R.id.addvisits_btn);
        relativeLayout = view.findViewById(R.id.home_ui);
        progressBar = view.findViewById(R.id.progressBar_home);
        today_visit_count_tv = view.findViewById(R.id.today_visit_count_tv);
        week_visit_count_tv = view.findViewById(R.id.week_visit_count_tv);
        total_visit_count_tv = view.findViewById(R.id.total_visit_count_tv);
        totol_visitors_count_tv = view.findViewById(R.id.totol_visitors_count_tv);
        relativeLayout.setVisibility(View.GONE);
        pref = this.getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        token=pref.getString("token", "16|HIxSsdnc3u9pT1OYqacJ7a3HERGjdnTNnTPlszUK");

        getHomeData(token);







        addVisitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddVisit();
            }


        });

        return view;
    }



    public void onResume() {
        super.onResume();
        Log.d("RESUME ", "FREGEMENT REFRASH");
        progressBar.setVisibility(View.VISIBLE);
        relativeLayout.setVisibility(View.GONE);
        getHomeData(token);
    }


    private void getHomeData(String token) {
        Call<dashboardModel> call = RetrofitClient.getInstance().getMyApi().getDashboard("Bearer "+token);
        call.enqueue(new Callback<dashboardModel>() {
            @Override
            public void onResponse(Call<dashboardModel> call, Response<dashboardModel> response) {
                dashboardModel dashboardModel = response.body();
                Log.d("LOGIN USER", "Login User Name" + dashboardModel.totalVisit);
                today_visit_count_tv.setText(dashboardModel.todaysVisit+"");
                week_visit_count_tv.setText(dashboardModel.weeksVisit+"");
                total_visit_count_tv.setText(dashboardModel.totalVisit+"");
                totol_visitors_count_tv.setText(dashboardModel.totalVisitor+"");
                relativeLayout.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }



            @Override
            public void onFailure(Call<dashboardModel> call, Throwable t) {
                Log.d("LOGIN USER", "Login User Name" + t.getMessage());
                // Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



        void openAddVisit() {
        Intent intent = new Intent(getActivity(), AddVisit.class);
        startActivity(intent);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}