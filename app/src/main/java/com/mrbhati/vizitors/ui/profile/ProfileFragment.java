package com.mrbhati.vizitors.ui.profile;

        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.fragment.app.Fragment;
        import androidx.lifecycle.ViewModelProvider;

        import com.mrbhati.vizitors.MainActivity;
        import com.mrbhati.vizitors.R;
        import com.mrbhati.vizitors.databinding.FragmentDashboardBinding;
        import com.mrbhati.vizitors.ui.Login;
        import com.mrbhati.vizitors.utils.InternetConnection;

        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.Date;

public class ProfileFragment extends Fragment {

    TextView name_et, mobile_et, email_et, usertype_et, last_login_et,created_at_et,name_small_et;
    Button log_out_btn;
    SharedPreferences pref;
    Date datetime;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        pref = this.getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String full_name =pref.getString("name", "N/A");
        String mobile =pref.getString("mobile", "N/A");
        String email =pref.getString("email", "N/A");
        String user_type =pref.getString("position", "N/A");
        String last_login =pref.getString("last_login_at", "N/A");
        String careate_at =pref.getString("last_login_at", "N/A");
        log_out_btn = view.findViewById(R.id.logout_btn);
        name_et = view.findViewById(R.id.name_tv);
        mobile_et = view.findViewById(R.id.mobile_tv);
        email_et = view.findViewById(R.id.email_tv);
        usertype_et = view.findViewById(R.id.position_tv);
        last_login_et = view.findViewById(R.id.last_login_at_tv);
        created_at_et = view.findViewById(R.id.created_at_tv);
        name_small_et = view.findViewById(R.id.name_small_tv);
//        try {
//            datetime=new SimpleDateFormat("dd-MM-yyyy hh:mm").parse(last_login);
//            last_login_et.setText(datetime+"");
//        } catch (ParseException e) {
//            e.printStackTrace();
//            last_login_et.setText(last_login+"");
//        }last_login

        last_login_et.setText(last_login);
        name_et.setText(full_name+"");
        mobile_et.setText(mobile+"");
        email_et.setText(email+"");
        email_et.setText(email+"");
        usertype_et.setText(user_type+"");
        name_small_et.setText(full_name+"");
        created_at_et.setText(careate_at+"");


    log_out_btn.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            if (InternetConnection.checkConnection(getContext())) {
             //   Toast.makeText(getContext(), "Internet Available...", Toast.LENGTH_SHORT).show();
                // Internet Available...
                logOut();
            } else {
                Toast.makeText(getContext(), "Internet Not Available", Toast.LENGTH_SHORT).show();
                // Internet Not Available...
            }
            logOut();
        }
    });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    private void logOut(){
        pref = this.getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        pref.edit().clear().commit();
        Intent intent = new Intent(this.getActivity(), Login.class);
        startActivity(intent);
    }

    public static String getFormatDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        return format.format(date);
    }
}