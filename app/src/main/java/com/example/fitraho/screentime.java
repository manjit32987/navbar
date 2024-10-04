package com.example.fitraho;

import android.app.AlertDialog;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class screentime extends Fragment {

    private TextView todayScreenTimeTextView;
    private TextView goalTextView;
    private TextView remainingTimeTextView;
    private Button setGoalButton;

    private static final String PREFS_NAME = "ScreenTimePrefs";
    private static final String PREF_SCREEN_TIME_GOAL = "ScreenTimeGoal";
    private long screenTimeGoal = -1; // Goal in milliseconds, -1 if not set
    private long totalScreenTimeToday = 0;

    public screentime() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_screentime, container, false);

        todayScreenTimeTextView = view.findViewById(R.id.todayScreenTimeTextView);
        goalTextView = view.findViewById(R.id.goalTextView);
        remainingTimeTextView = view.findViewById(R.id.remainingTimeTextView);
        setGoalButton = view.findViewById(R.id.setGoalButton);

        setGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSetGoalDialog();
            }
        });

        if (hasUsageStatsPermission()) {
            displayScreenTime();
        } else {
            requestUsageStatsPermission();
        }

        // Load the saved screen time goal
        loadScreenTimeGoal();

        return view;
    }

    private boolean hasUsageStatsPermission() {
        UsageStatsManager usm = (UsageStatsManager) getContext().getSystemService(Context.USAGE_STATS_SERVICE);
        long currentTime = System.currentTimeMillis();
        List<UsageStats> appList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, currentTime - 1000 * 3600 * 24, currentTime);
        return (appList != null && !appList.isEmpty());
    }

    private void requestUsageStatsPermission() {
        Toast.makeText(getContext(), "Please enable usage access for this app in the settings.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        startActivity(intent);
    }

    private void displayScreenTime() {
        UsageStatsManager usm = (UsageStatsManager) getContext().getSystemService(Context.USAGE_STATS_SERVICE);
        long currentTime = System.currentTimeMillis();

        // Today
        long startTimeToday = currentTime - TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
        List<UsageStats> statsToday = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, startTimeToday, currentTime);
        totalScreenTimeToday = calculateTotalScreenTime(statsToday);
        todayScreenTimeTextView.setText("Today: " + formatTime(totalScreenTimeToday));

        // Update goal and remaining time
        updateGoalAndRemainingTime();
    }

    private long calculateTotalScreenTime(List<UsageStats> stats) {
        long totalScreenTime = 0;
        for (UsageStats usageStats : stats) {
            totalScreenTime += usageStats.getTotalTimeInForeground();
        }
        return totalScreenTime;
    }

    private String formatTime(long millis) {
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis) % 60;
        return String.format("%02d:%02d", hours, minutes);
    }

    private void updateGoalAndRemainingTime() {
        if (screenTimeGoal > 0) {
            goalTextView.setText("Goal: " + formatTime(screenTimeGoal));
            long remainingTime = screenTimeGoal - totalScreenTimeToday;
            if (remainingTime < 0) {
                remainingTime = 0; // Ensure remaining time does not go negative
            }
            remainingTimeTextView.setText("Remaining Time: " + formatTime(remainingTime));
        } else {
            goalTextView.setText("Goal: Not set");
            remainingTimeTextView.setText("Remaining Time: N/A");
        }
    }

    private void showSetGoalDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Set Screen Time Goal");

        View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.dialog_set_goal, (ViewGroup) getView(), false);
        final EditText input = viewInflated.findViewById(R.id.goalEditText);
        builder.setView(viewInflated);

        builder.setPositiveButton("Set", (dialog, which) -> {
            dialog.dismiss();
            String inputText = input.getText().toString();
            try {
                screenTimeGoal = TimeUnit.HOURS.toMillis(Long.parseLong(inputText));
                saveScreenTimeGoal(screenTimeGoal);
                Toast.makeText(getContext(), "Screen time goal set: " + inputText + " hours", Toast.LENGTH_SHORT).show();
                displayScreenTime(); // Refresh screen time and goal
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Invalid input. Please enter a valid number.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void saveScreenTimeGoal(long goal) {
        SharedPreferences preferences = getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(PREF_SCREEN_TIME_GOAL, goal);
        editor.apply();
    }

    private void loadScreenTimeGoal() {
        SharedPreferences preferences = getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        screenTimeGoal = preferences.getLong(PREF_SCREEN_TIME_GOAL, -1);
        updateGoalAndRemainingTime(); // Update display based on the loaded goal
    }
}