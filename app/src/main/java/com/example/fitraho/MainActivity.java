package com.example.fitraho;

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
        replaceFragment(new home()); // Ensure the fragment class name is correct
        binding.bottomnavigation.setBackground(null);
        binding.bottomnavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new home()); // Ensure the fragment class name is correct
                    break;
                case R.id.screentime:
                    replaceFragment(new screentime()); // Ensure the fragment class name is correct
                    break;
                case R.id.water:
                    replaceFragment(new waterdrink()); // Ensure the fragment class name is correct
                    break;
                case R.id.Diet:
                    replaceFragment(new diet()); // Ensure the fragment class name is correct
                    break;
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
