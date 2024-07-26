package com.example.fitraho;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class home extends Fragment {

    private TextView helloUserTextView;
    private TextView dateTextView;
    private TextView timeTextView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        helloUserTextView = view.findViewById(R.id.hello_user);
        dateTextView = view.findViewById(R.id.date);
        timeTextView = view.findViewById(R.id.time);

        // Load user's name from SharedPreferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("user_prefs", requireContext().MODE_PRIVATE);
        String userName = sharedPreferences.getString("user_name", "User");

        // Display user's name
        helloUserTextView.setText("Hello, " + userName);

        // Display current date and time
        Calendar calendar = Calendar.getInstance();
        String date = DateFormat.format("dd/MM/yyyy", calendar).toString();
        String time = DateFormat.format("hh:mm a", calendar).toString();

        dateTextView.setText(date);
        timeTextView.setText(time);
    }
}
