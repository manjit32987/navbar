package com.example.fitraho;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.fitraho.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        // Check if user's name is saved
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String userName = sharedPreferences.getString("user_name", null);

        if (userName == null) {
            // If user's name is not saved, redirect to NameInputActivity
            Intent intent = new Intent(MainActivity.this, NameInputActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        // Load the default fragment
        replaceFragment(new home());

        // Set up bottom navigation item selection listener
        binding.bottomnavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            Fragment selectedFragment = null;
            if (itemId == R.id.user) {
                selectedFragment = new home();
            } else if (itemId == R.id.screentime) {
                selectedFragment = new screentime();
            } else if (itemId == R.id.water) {
                selectedFragment = new waterdrink();
            } else if (itemId == R.id.Diet) {
                selectedFragment = new diet();
            } else if (itemId == R.id.Excercise) {
                selectedFragment = new excercise();
            }

            if (selectedFragment != null) {
                replaceFragment(selectedFragment);
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
