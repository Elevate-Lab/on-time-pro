package com.gdsciiita.ontimepro.activities;

import static androidx.navigation.ui.NavigationUI.setupWithNavController;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.gdsciiita.ontimepro.GoogleSignInObject;
import com.gdsciiita.ontimepro.R;
import com.gdsciiita.ontimepro.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    NavController navController;
    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton fab;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle Toggle;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

//        for login testing
//        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//        startActivity(intent);

        bottomNavigationView = binding.bottomNav;

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        navController = navHostFragment.getNavController();
        setupWithNavController(binding.bottomNav, navController);

        drawerLayout=findViewById(R.id.drawerLayout);
        Toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(Toggle);
        Toggle.syncState();

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        //TODO: Setup actions
        if(item.getItemId() == R.id.Notifications){
            FirebaseAuth.getInstance().signOut();
            GoogleSignInObject.Companion.getSignInClient(this).signOut();
            startActivity(new Intent(this, LoginActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        if(Toggle.onOptionsItemSelected(item)) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topmenu, menu);

        return true;
    }
}