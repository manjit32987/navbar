package com.example.fitraho;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.fitraho.databinding.ActivityNonveglistBinding;

public class vegnonveg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vegnonveg);

        // Get references to the CardViews
        CardView nonVegCard = findViewById(R.id.cardView4);
        CardView vegCard = findViewById(R.id.cardView5);

        // Set click listeners to open new activities
        nonVegCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start NonVegActivity
                Intent intent = new Intent(vegnonveg.this, nonveglist.class);
                startActivity(intent);
            }
        });

        vegCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start VegActivity
                Intent intent = new Intent(vegnonveg.this, veglist.class);
                startActivity(intent);
            }
        });
    }
}
