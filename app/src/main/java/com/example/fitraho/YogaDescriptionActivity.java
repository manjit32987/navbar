package com.example.fitraho;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitraho.R;

public class YogaDescriptionActivity extends AppCompatActivity {

    TextView yogaNameTextView, yogaDescriptionTextView;
    ImageView yogaImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_description);

        // Get references to the views
        yogaNameTextView = findViewById(R.id.yogaName);
        yogaDescriptionTextView = findViewById(R.id.yogaDescription);
        yogaImageView = findViewById(R.id.yogaImage);

        // Retrieve the yoga details from the intent
        String yogaName = getIntent().getStringExtra("YOGA_NAME");
        String yogaDescription = getIntent().getStringExtra("YOGA_DESCRIPTION");
        int yogaImageResId = getIntent().getIntExtra("YOGA_IMAGE", R.drawable.sample_yoga); // Default image

        yogaNameTextView.setText(yogaName);
        yogaDescriptionTextView.setText(yogaDescription);
        yogaImageView.setImageResource(yogaImageResId);
    }
}
