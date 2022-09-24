package com.mrbhati.vizitors;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.mrbhati.vizitors.databinding.ActivityMainBinding;
import com.mrbhati.vizitors.ui.home.HomeFragment;
import com.mswipetech.wisepad.sdk.Print;
import com.socsi.smartposapi.printer.Align;
import com.socsi.smartposapi.printer.FontLattice;

public class MainActivity extends AppCompatActivity {
    private long pressedTime;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
//        Print.init(MainActivity.this);
//        Print.StartPrinting("TITLE <br>", FontLattice.FORTY_EIGHT, true, Align.CENTER, true);
//        Print.StartPrinting("LAST NAME<br>" ,FontLattice.THIRTY_TWO, true, Align.LEFT, true);
//        Print.StartPrinting("ENTRY TIME<br>" ,FontLattice.THIRTY_TWO, true, Align.LEFT, true);
//        Print.StartPrinting("TICKET NO<br>" ,FontLattice.THIRTY_TWO, true, Align.LEFT, true);
//
//        Print.StartPrinting();
//        Print.StartPrinting();
//        Print.StartPrinting();
    }
    @Override
    public void onBackPressed() {

        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
            finishAffinity();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }

}