package com.example.fitraho;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class diet extends Fragment {

    private EditText ageEditText, weightEditText, feetEditText, inchesEditText;
    private TextView bmiResultTextView;
    private RadioGroup genderRadioGroup;
    private Button calculateBmiButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diet, container, false);

        ageEditText = view.findViewById(R.id.ageEditText);
        weightEditText = view.findViewById(R.id.weightEditText);
        feetEditText = view.findViewById(R.id.feetEditText);
        inchesEditText = view.findViewById(R.id.inchesEditText);
        genderRadioGroup = view.findViewById(R.id.genderRadioGroup);
        bmiResultTextView = view.findViewById(R.id.bmiResultTextView);
        calculateBmiButton = view.findViewById(R.id.calculateBmiButton);

        calculateBmiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndDisplayBMI();
            }
        });

        return view;
    }

    private void calculateAndDisplayBMI() {
        String ageStr = ageEditText.getText().toString();
        String weightStr = weightEditText.getText().toString();
        String feetStr = feetEditText.getText().toString();
        String inchesStr = inchesEditText.getText().toString();

        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        if (selectedGenderId == -1) {
            Toast.makeText(getContext(), "Please select your gender", Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton selectedGenderButton = getView().findViewById(selectedGenderId);
        String gender = selectedGenderButton.getText().toString();

        if (!ageStr.isEmpty() && !weightStr.isEmpty() && !feetStr.isEmpty() && !inchesStr.isEmpty()) {
            int age = Integer.parseInt(ageStr);
            float weight = Float.parseFloat(weightStr);
            int feet = Integer.parseInt(feetStr);
            int inches = Integer.parseInt(inchesStr);

            float heightInMeters = convertHeightToMeters(feet, inches);
            saveUserData(age, weight, heightInMeters, gender);

            float bmi = weight / (heightInMeters * heightInMeters);
            String category = categorizeBMI(bmi);
            String result = "Your BMI: " + String.format("%.2f", bmi) + "\nCategory: " + category + "\nGender: " + gender;

            bmiResultTextView.setText(result);
        } else {
            Toast.makeText(getContext(), "Please enter all details", Toast.LENGTH_SHORT).show();
        }
    }

    private float convertHeightToMeters(int feet, int inches) {
        float totalInches = (feet * 12) + inches;
        return totalInches * 0.0254f; // Convert inches to meters
    }

    private void saveUserData(int age, float weight, float height, String gender) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Age", age);
        editor.putFloat("Weight", weight);
        editor.putFloat("Height", height);
        editor.putString("Gender", gender);
        editor.apply();
    }

    private String categorizeBMI(float bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal weight";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }
}
