package com.mrbhati.vizitors.ui.dashboard;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mrbhati.vizitors.Model.Datum;
import com.mrbhati.vizitors.Model.LoginResponse;
import com.mrbhati.vizitors.Model.UserRequest;
import com.mrbhati.vizitors.Model.Visit;
import com.mrbhati.vizitors.Model.VisitModel;
import com.mrbhati.vizitors.R;
import com.mrbhati.vizitors.adapters.VisitsAdapter;
import com.mrbhati.vizitors.adapters.myadapter;
import com.mrbhati.vizitors.services.RetrofitClient;
import com.mrbhati.vizitors.ui.AddVisit;
import com.mrbhati.vizitors.ui.VisitDestails;
import com.mrbhati.vizitors.ui.VisitorDetails;
import com.mrbhati.vizitors.utils.VistClickListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashboardFragment extends Fragment implements VistClickListener {

    RecyclerView recyclerView;
    ArrayList<Visit> dataholder;
    VisitsAdapter visitsAdapter;
    ProgressBar progressBar;
    SharedPreferences pref;
    String token;
    ArrayList<String> visitIdList = new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerView=view.findViewById(R.id.recview);
        progressBar = view.findViewById(R.id.progressBar_dashboard);
        recyclerView.setVisibility(View.GONE);
        pref = this.getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        token  =pref.getString("token", "16|HIxSsdnc3u9pT1OYqacJ7a3HERGjdnTNnTPlszUK");


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataholder=new ArrayList<>();
        getVisits(token);





        return view;
    }

    public void onResume() {
        super.onResume();
      Log.d("RESUME ", "FREGEMENT REFRASH");
       progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        getVisits(token);
    }

//    public void openNewActivity(){
//        Intent intent = new Intent(this, AddVisit.class);
//        startActivity(intent);
 //   }


   private void getVisits(String token){
       Call<VisitModel> call = RetrofitClient.getInstance().getMyApi().getVisits("Bearer "+token);
       call.enqueue(new Callback<VisitModel>() {
           @Override
           public void onResponse(Call<VisitModel> call, Response<VisitModel> response) {

               VisitModel visitModel = response.body();
               Log.d("Visits","TotalVisit Count"+visitModel.data.size());

               for(int i=0; visitModel.data.size() > i; i++){
                   Log.d("LOGIN USER","Count : "+i+"");
                   visitIdList.add(visitModel.data.get(i).getId());
                   Log.d("LOGIN USER","LIST : "+visitIdList+"");
               }

               visitsAdapter = new VisitsAdapter(visitModel.data);
               recyclerView.setAdapter(visitsAdapter);
               visitsAdapter.setClickListener(DashboardFragment.this);
               recyclerView.setVisibility(View.VISIBLE);
               progressBar.setVisibility(View.GONE);
           }

           @Override
           public void onFailure(Call<VisitModel> call, Throwable t) {
               Log.d("LOGIN USER","Login User Name"+t.getMessage());
              // Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
           }
       });




   }
    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(getContext(), VisitDestails.class);
        intent.putExtra("VISIT_ID", visitIdList.get(position));
        startActivity(intent);
    }
}