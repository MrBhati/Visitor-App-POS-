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
import android.widget.Toast;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mrbhati.vizitors.Model.Datum;
import com.mrbhati.vizitors.Model.VisitorModel;
import com.mrbhati.vizitors.R;
import com.mrbhati.vizitors.adapters.myadapter;
import com.mrbhati.vizitors.services.RetrofitClient;
import com.mrbhati.vizitors.ui.VisitorDetails;
import com.mrbhati.vizitors.utils.InternetConnection;
import com.mrbhati.vizitors.utils.VistClickListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationsFragment extends Fragment implements VistClickListener {

    RecyclerView recyclerView;
    int count = 1;
    int lastPage;
    ArrayList<Datum> dataholder;
    myadapter myadapter;
    ArrayList<String> visitorIdList = new ArrayList<String>();
    SharedPreferences pref;
    ProgressBar progressBar;
    String token;
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
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_notifications, container, false);
        recyclerView=view.findViewById(R.id.recview);
        progressBar = view.findViewById(R.id.progressBar_notification);
        recyclerView.setVisibility(View.GONE);
        loadingPB = view.findViewById(R.id.idPBLoading);
        loadingPB.setVisibility(View.GONE);
        nestedSV = view.findViewById(R.id.idNestedSV);
        pref = this.getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        token=pref.getString("token", "16|HIxSsdnc3u9pT1OYqacJ7a3HERGjdnTNnTPlszUK");
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

        myadapter = new myadapter(dataholder);
        recyclerView.setAdapter(myadapter);
        myadapter.setClickListener(this);

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


        Call<VisitorModel> call = RetrofitClient.getInstance().getMyApi().getVisitors("Bearer "+token, count);
        call.enqueue(new Callback<VisitorModel>() {
            @Override
            public void onResponse(Call<VisitorModel> call, Response<VisitorModel> response) {

                VisitorModel visitorModel = response.body();
                Log.d("LOGIN USER","Login User Name"+visitorModel.data.size());
                lastPage = visitorModel.last_page;
                if(visitorModel.data.isEmpty()){
                    Log.d("LOGIN USER","Login User Name"+visitorModel.data.size());
                }else{
                    count = visitorModel.current_page+1;
                }


                for(int i=0; visitorModel.data.size() > i; i++){
                    Log.d("LOGIN USER","Count : "+i+"");
                    visitorIdList.add(visitorModel.data.get(i).getId());

                    Log.d("LOGIN USER","LIST : "+visitorIdList+"");
                    dataholder.add(new Datum(visitorModel.data.get(i).getId(),visitorModel.data.get(i).getPicture(),visitorModel.data.get(i).getName(),visitorModel.data.get(i).getMobile(),visitorModel.data.get(i).getSociety(),visitorModel.data.get(i).getTotal(),visitorModel.data.get(i).getCreatedAt(),visitorModel.data.get(i).getLastVisitAt()));
                }

                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

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