package com.example.swift;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.example.swift.fragments.ExercisesFragment;
import com.example.swift.fragments.InformationFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Menu bar setup
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        MaterialToolbar materialToolbar = findViewById(R.id.topAppBar);
        materialToolbar.setNavigationOnClickListener(view -> {
            drawerLayout.open();
        });

        NavigationView navigationView = findViewById(R.id.navigation_view);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container, InformationFragment.class, null)
                    .commit();
        }

        navigationView.setNavigationItemSelectedListener(menuItem -> {
            menuItem.setChecked(true);
            if (menuItem.getItemId() == R.id.profile_item) {
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container, InformationFragment.class, null)
                        .commit();
            }

            else if (menuItem.getItemId() == R.id.exercise_item){
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container, ExercisesFragment.class, null)
                        .commit();
            }
            return true;
        });

    }
}