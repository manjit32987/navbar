package com.example.fitraho;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
public class Activity_BMI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        EdgeToEdge.enable(this);

        EditText weightEditText = findViewById(R.id.weightEditText);
        EditText heightEditText = findViewById(R.id.heightEditText);
        RadioGroup genderRadioGroup = findViewById(R.id.genderRadioGroup);
        Button calculateBmiButton = findViewById(R.id.calculateBmiButton);

        calculateBmiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weightStr = weightEditText.getText().toString();
                String heightStr = heightEditText.getText().toString();

                if (weightStr.isEmpty() || heightStr.isEmpty()) {
                    // Handle empty fields
                    return;
                }

                double weight = Double.parseDouble(weightStr);
                double height = Double.parseDouble(heightStr);

                // Convert height from feet to meters
                height = height * 0.3048;

                double bmi = weight / (height * height);

                // Determine gender
                int selectedId = genderRadioGroup.getCheckedRadioButtonId();
                RadioButton selectedGender = findViewById(selectedId);
                String gender = selectedGender != null ? selectedGender.getText().toString() : "Unknown";

                // Start ResultActivity and pass BMI result
                Intent intent = new Intent(Activity_BMI.this, ResultActivity.class);
                intent.putExtra("BMI_RESULT", bmi);
                intent.putExtra("GENDER", gender);
                startActivity(intent);
            }
        });
    }
}
