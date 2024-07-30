package com.example.fitraho;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class waterdrink extends Fragment {

    private CircularProgressBar circularProgressBar;
    private TextView tvWaterIntake;
    private Button btnAddWater;
    private ListView lvWaterIntakeHistory;
    private WaterIntakeAdapter adapter;
    private List<WaterIntakeRecord> records;
    private int waterIntake = 0;
    private int goal = 2000; // Default goal
    private int containerSize = 250; // Default container size

    public waterdrink() {
        super(R.layout.fragment_waterdrink);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        circularProgressBar = view.findViewById(R.id.circularProgressBar);
        tvWaterIntake = view.findViewById(R.id.tv_waterIntake);
        btnAddWater = view.findViewById(R.id.btn_addWater);
        lvWaterIntakeHistory = view.findViewById(R.id.lv_waterIntakeHistory);

        records = new ArrayList<>();
        adapter = new WaterIntakeAdapter(getContext(), records);
        lvWaterIntakeHistory.setAdapter(adapter);

        btnAddWater.setOnClickListener(v -> {
            // Track water intake
            waterIntake += containerSize;
            if (waterIntake > goal) {
                waterIntake = goal; // Cap the intake to the goal
            }
            addWaterIntakeRecord();
            updateProgressBar();
        });

        btnAddWater.setOnLongClickListener(v -> {
            showSettingsDialog();
            return true;
        });

        // Initialize the circular progress bar and other UI elements
        updateProgressBar();
    }

    private void showSettingsDialog() {
        // Inflate the dialog layout
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.dialog_option, null);

        // Find buttons in the dialog
        Button btnSetGoal = dialogView.findViewById(R.id.btn_setGoal);
        Button btnSetContainerSize = dialogView.findViewById(R.id.btn_setContainerSize);

        // Create and show the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.show();

        // Set up button click listeners
        btnSetGoal.setOnClickListener(v -> {
            dialog.dismiss();
            showGoalInputDialog();
        });

        btnSetContainerSize.setOnClickListener(v -> {
            dialog.dismiss();
            showContainerSizeInputDialog();
        });
    }

    private void showGoalInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Set Water Intake Goal");

        final EditText input = new EditText(getContext());
        input.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String inputText = input.getText().toString();
            if (!TextUtils.isEmpty(inputText)) {
                goal = Integer.parseInt(inputText);
                updateProgressBar();
            } else {
                Toast.makeText(getContext(), "Please enter a valid goal", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void showContainerSizeInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Set Container Size");

        final EditText input = new EditText(getContext());
        input.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String inputText = input.getText().toString();
            if (!TextUtils.isEmpty(inputText)) {
                containerSize = Integer.parseInt(inputText);
                Toast.makeText(getContext(), "Container size set to: " + containerSize + " ml", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Please enter a valid size", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void addWaterIntakeRecord() {
        // Use 12-hour format with AM/PM
        String currentTime = new SimpleDateFormat("hh:mm:ss a", Locale.getDefault()).format(new Date());
        records.add(0, new WaterIntakeRecord(currentTime, containerSize)); // Add new record at the top
        adapter.notifyDataSetChanged();
    }

    private void updateProgressBar() {
        // Calculate the progress as a percentage of the goal
        float progressPercentage = ((float) waterIntake / goal) * 100;
        circularProgressBar.setProgress(progressPercentage);
        // Update the text view with current intake and goal
        tvWaterIntake.setText(waterIntake + " ml / " + goal + " ml");
    }
}
