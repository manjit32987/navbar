package com.example.fitraho;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        EdgeToEdge.enable(this);

        // Get BMI result and gender from the intent
        double bmi = getIntent().getDoubleExtra("BMI_RESULT", 0.0);
        String gender = getIntent().getStringExtra("GENDER");

        // Find TextViews and Button
        TextView bmiResultTextView = findViewById(R.id.bmiResultTextView);
        TextView bmiCategoryTextView = findViewById(R.id.bmiCategoryTextView);
        TextView genderTextView = findViewById(R.id.genderTextView);
        Button redirectToMenuButton = findViewById(R.id.redirectToFragmentButton);

        // Set the BMI and category
        bmiResultTextView.setText(String.format("Your BMI is: %.2f", bmi));
        bmiCategoryTextView.setText("Category: " + getBmiCategory(bmi));
        genderTextView.setText("Gender: " + gender);

        // Set button click listener
        redirectToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to main activity with a specific menu item selected
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                intent.putExtra("SELECTED_MENU_ITEM", R.id.Diet); // Change to your desired menu ID
                startActivity(intent);
            }
        });
    }

    // Method to determine BMI category
    private String getBmiCategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal weight";
        } else if (bmi >= 25.0 && bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }
}
