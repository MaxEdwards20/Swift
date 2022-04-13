package com.example.swift;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

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

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container, ExercisesFragment.class, null)
                .commit();
    }
}