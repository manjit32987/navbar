package com.example.fitraho;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class WaterIntakeAdapter extends ArrayAdapter<WaterIntakeRecord> {

    public WaterIntakeAdapter(Context context, List<WaterIntakeRecord> records) {
        super(context, 0, records);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        WaterIntakeRecord record = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_water_intake, parent, false);
        }

        // Lookup view for data population
        TextView tvTime = convertView.findViewById(R.id.tv_time);
        TextView tvContainerSize = convertView.findViewById(R.id.tv_containerSize);

        // Populate the data into the template view using the data object
        tvTime.setText(record.getTime());
        tvContainerSize.setText(record.getContainerSize() + " ml");

        // Return the completed view to render on screen
        return convertView;
    }
}
