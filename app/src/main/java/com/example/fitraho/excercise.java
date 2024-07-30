package com.example.fitraho;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class excercise extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_excercise, container, false);

        ImageView bmi = view.findViewById(R.id.imgBMI);
        ImageView sensor = view.findViewById(R.id.imgPEDO);

        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity_BMI.class);
                startActivity(intent);
            }

        });
        sensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), Activity_PEDO.class);
                startActivity(intent1);
            }

        });

        return view;
    }
}

