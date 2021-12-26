package com.gdsciiita.ontimepro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.gdsciiita.ontimepro.fragments.AssignmentsFragment;
import com.gdsciiita.ontimepro.fragments.EmailAttachFragment;
import com.gdsciiita.ontimepro.fragments.MainFragment;
import com.gdsciiita.ontimepro.fragments.ScheduleFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    NavController navController;
    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        for login testing
//        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//        startActivity(intent);

        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        //NavigationUI.setupActionBarWithNavController(this, navController);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    Fragment fragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.Classroom:
                            fragment = new MainFragment();
                            break;

                        case R.id.Schedule:
                            fragment = new ScheduleFragment();
                            break;

                        case R.id.Assignments:
                            fragment = new AssignmentsFragment();
                            break;

                        case R.id.Email:
                            fragment = new EmailAttachFragment();
                            break;

                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.Nav, fragment).commit();
                    return true;
                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topmenu, menu);

        return true;
    }
}