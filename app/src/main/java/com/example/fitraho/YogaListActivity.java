package com.example.fitraho;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class YogaListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_list); // Make sure this layout matches your file name

        // Define a click listener for each yoga item
        setYogaItemClickListener(R.id.yogaItem1, "Yoga Pose 1", "This is the description of Yoga Pose 1. It helps improve flexibility and reduce stress.", R.drawable.removebgdown);
        setYogaItemClickListener(R.id.yogaItem2, "Yoga Pose 2", "This is the description of Yoga Pose 2. It strengthens the core and improves posture.", R.drawable.treeyoga);
        setYogaItemClickListener(R.id.yogaItem3, "Yoga Pose 3", "This is the description of Yoga Pose 3. It enhances breathing and increases focus.", R.drawable.cobrapose);
        setYogaItemClickListener(R.id.yogaItem4, "Yoga Pose 4", "This is the description of Yoga Pose 4. It boosts energy and relieves tension.", R.drawable.childpose);
    }

    // Method to set click listener on each yoga item
    private void setYogaItemClickListener(int layoutId, String yogaName, String yogaDescription, int yogaImageResId) {
        LinearLayout yogaItem = findViewById(layoutId);
        yogaItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open YogaDescriptionActivity and pass yoga details
                Intent intent = new Intent(YogaListActivity.this, YogaDescriptionActivity.class);
                intent.putExtra("YOGA_NAME", yogaName);
                intent.putExtra("YOGA_DESCRIPTION", yogaDescription);
                intent.putExtra("YOGA_IMAGE", yogaImageResId);
                startActivity(intent);
            }
        });
    }
}
