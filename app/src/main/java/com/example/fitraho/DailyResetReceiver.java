package com.example.fitraho;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class DailyResetReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Reset water intake here
        resetWaterIntake(context);
    }

    private void resetWaterIntake(Context context) {
        // Retrieve SharedPreferences and reset water intake
        SharedPreferences sharedPreferences = context.getSharedPreferences("WaterIntakePrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("waterIntake", 0);
        editor.apply();

        // Optional: Notify the user or update UI
        // For example, you might use a notification or an update to your UI
    }
}
