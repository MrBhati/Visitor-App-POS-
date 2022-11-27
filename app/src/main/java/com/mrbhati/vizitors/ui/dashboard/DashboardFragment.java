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

import androidx.core.widget.NestedScrollView;
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
import com.mrbhati.vizitors.ui.notifications.NotificationsFragment;
import com.mrbhati.vizitors.utils.InternetConnection;
import com.mrbhati.vizitors.utils.VistClickListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashboardFragment extends Fragment implements VistClickListener {
    int count = 1;
    int lastPage;
    RecyclerView recyclerView;
    ArrayList<Visit> dataholder;
    VisitsAdapter visitsAdapter;
    ProgressBar progressBar;
    SharedPreferences pref;
    String token;
    ArrayList<String> visitIdList = new ArrayList<String>();
    private ProgressBar loadingPB;
    private NestedScrollView nestedSV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView=view.findViewById(R.id.recview);
        progressBar = view.findViewById(R.id.progressBar_dashboard);
        recyclerView.setVisibility(View.GONE);

        loadingPB = view.findViewById(R.id.idPBLoading_visit);
        loadingPB.setVisibility(View.GONE);
        nestedSV = view.findViewById(R.id.idNestedSV_visit);
        pref = this.getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        token  =pref.getString("token", "");

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataholder=new ArrayList<>();
        getVisits(token);

        nestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // on scroll change we are checking when users scroll as bottom.
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    // in this method we are incrementing page number,
                    // making progress bar visible and calling get data method.

                    // on below line we are making our progress bar visible.
                    loadingPB.setVisibility(View.VISIBLE);

                    if (InternetConnection.checkConnection(getContext())) {
                       // Toast.makeText(getContext(), "Internet Available...", Toast.LENGTH_SHORT).show();
                        // Internet Available...
                        getVisits(token);
                    } else {
                        Toast.makeText(getContext(), "Internet Not Available", Toast.LENGTH_SHORT).show();
                        // Internet Not Available...
                    }


                }
            }
        });

        visitsAdapter = new VisitsAdapter(dataholder);
        recyclerView.setAdapter(visitsAdapter);
        visitsAdapter.setClickListener(DashboardFragment.this);
        return view;
    }

    public void onResume() {
        super.onResume();
        Log.d("RESUME ", "FREGEMENT REFRASH");
        dataholder.clear();
        lastPage = 0;
        count = 1;
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);


        getVisits(token);
    }

   private void getVisits(String token){
       Log.d("LAST PAGE NUMBER",""+lastPage);
       Log.d("PAGE COUNT",""+count);
       if(lastPage != 0){
           if(count > lastPage){


               Toast.makeText(getActivity(), "No more visitors found!",
                       Toast.LENGTH_SHORT).show();
               loadingPB.setVisibility(View.GONE);
               progressBar.setVisibility(View.GONE);

               return;

           }
       }

       Call<VisitModel> call = RetrofitClient.getInstance().getMyApi().getVisits("Bearer "+token, count);
       call.enqueue(new Callback<VisitModel>() {
           @Override
           public void onResponse(Call<VisitModel> call, Response<VisitModel> response) {
               VisitModel visitModel = response.body();

               lastPage = visitModel.last_page;
               if(visitModel.data.isEmpty()){
                   Log.d("LOGIN USER","Login User Name"+visitModel.data.size());
               }else{
                   count = visitModel.current_page+1;
               }

               Log.d("Visits","TotalVisit Count"+visitModel.data.size());
               for(int i=0; visitModel.data.size() > i; i++){
                   Log.d("LOGIN USER","Count : "+i+"");
                   visitIdList.add(visitModel.data.get(i).getId());
                   dataholder.add(new Visit(visitModel.data.get(i).getId(),visitModel.data.get(i).getPicture(),visitModel.data.get(i).getCode(),visitModel.data.get(i).getPurpose(),visitModel.data.get(i).getVisitor_id(),visitModel.data.get(i).getVisitor(),visitModel.data.get(i).getDepartment(),visitModel.data.get(i).getDesignation(),visitModel.data.get(i).getMobile(),visitModel.data.get(i).getSociety(),visitModel.data.get(i).getCreated_at()));
                   Log.d("LOGIN USER","LIST : "+visitIdList+"");
               }


               recyclerView.setVisibility(View.VISIBLE);
               progressBar.setVisibility(View.GONE);

               recyclerView.setAdapter(visitsAdapter);

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