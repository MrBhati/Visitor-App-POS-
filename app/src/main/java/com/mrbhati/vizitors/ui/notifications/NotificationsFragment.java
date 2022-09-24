package com.mrbhati.vizitors.ui.notifications;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mrbhati.vizitors.Model.Datum;
import com.mrbhati.vizitors.Model.VisitorModel;
import com.mrbhati.vizitors.R;
import com.mrbhati.vizitors.adapters.myadapter;
import com.mrbhati.vizitors.services.RetrofitClient;
import com.mrbhati.vizitors.ui.VisitorDetails;
import com.mrbhati.vizitors.utils.VistClickListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationsFragment extends Fragment implements VistClickListener {

    RecyclerView recyclerView;

    ArrayList<Datum> dataholder;
    myadapter myadapter;
    ArrayList<String> visitorIdList = new ArrayList<String>();
    SharedPreferences pref;
    ProgressBar progressBar;
    String token;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_notifications, container, false);
        recyclerView=view.findViewById(R.id.recview);
        progressBar = view.findViewById(R.id.progressBar_notification);
      recyclerView.setVisibility(View.GONE);
        pref = this.getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        token=pref.getString("token", "16|HIxSsdnc3u9pT1OYqacJ7a3HERGjdnTNnTPlszUK");

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataholder=new ArrayList<>();
        getVisits(token);

myadapter = new myadapter(dataholder);
        recyclerView.setAdapter(myadapter);
        myadapter.setClickListener(this);

        return view;
    }
    public void onResume() {
        super.onResume();
        Log.d("RESUME ", "FREGEMENT REFRASH");
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        getVisits(token);
    }



    private void getVisits(String token){
        Call<VisitorModel> call = RetrofitClient.getInstance().getMyApi().getVisitors("Bearer "+token);
        call.enqueue(new Callback<VisitorModel>() {
            @Override
            public void onResponse(Call<VisitorModel> call, Response<VisitorModel> response) {

                VisitorModel visitorModel = response.body();
                Log.d("LOGIN USER","Login User Name"+visitorModel.data.size());

                for(int i=0; visitorModel.data.size() > i; i++){
                    Log.d("LOGIN USER","Count : "+i+"");
                    visitorIdList.add(visitorModel.data.get(i).getId());
                    Log.d("LOGIN USER","LIST : "+visitorIdList+"");
                }
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                myadapter = new myadapter(visitorModel.data);
                recyclerView.setAdapter(myadapter);
                myadapter.setClickListener(NotificationsFragment.this);
            }

            @Override
            public void onFailure(Call<VisitorModel> call, Throwable t) {
                Log.d("LOGIN USER","Login User Name"+t.getMessage());
                // Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }

    @Override
    public void onClick(View view, int position) {
        Log.d("POSOTION","Clicked Card posotion:"+ position);
        Intent intent = new Intent(getContext(), VisitorDetails.class);
        intent.putExtra("VISITOR_ID", visitorIdList.get(position));
        startActivity(intent);

    }


}


//
//public class NotificationsFragment extends Fragment {
//
//
//    private FragmentNotificationsBinding binding;
//
//
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//
//
//        NotificationsViewModel notificationsViewModel =
//                new ViewModelProvider(this).get(NotificationsViewModel.class);
//
//        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        TextView textView = binding.textNotifications;
//        RecyclerView recview = binding.;
//        List<Datum> visitorList;
//        VisitorsAdapter visitorsAdapter;
//        TextView noresfound;
//
////        recview=findViewById(R.id.recview);
////        noresfound=findViewById(R.id.noresult);
////
////        recview.setLayoutManager(new LinearLayoutManager(this));
////        recview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
////
////        adapter=new MovieListAdapter(movielist);
////        recview.setAdapter(adapter);
//
//        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        return root;
//    }
//
//
//}