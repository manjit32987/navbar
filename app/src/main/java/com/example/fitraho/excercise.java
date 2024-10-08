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
        ImageView tipscall = view.findViewById(R.id.tips147);
        ImageView yogacall = view.findViewById(R.id.yoga);

        yogacall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getActivity(),YogaListActivity    .class);
                startActivity(j);
            }
        });

        tipscall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),NonVegActivity.class);
                startActivity(i);
            }
        });


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

