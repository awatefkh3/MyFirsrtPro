package com.example.myfirsrtpro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {

    private TextView eventCellTV, eventCellTime, eventCellDate, yesNo;
    private Context context;
    private int resource;

    public EventAdapter(@NonNull Context context, int resource, List<Event> events) {
        super(context, resource, events);
        this.context = context;
        this.resource = resource;
    }

    public EventAdapter(@NonNull Context context, List<Event> events) {
        super(context, 0, events);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Event event = (Event) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);
        }

        eventCellTV = convertView.findViewById(R.id.eventCellTV);
        eventCellTime = convertView.findViewById(R.id.eventCellTime);
        eventCellDate = convertView.findViewById(R.id.eventCellDate);
        yesNo = convertView.findViewById(R.id.yesNo);

        eventCellTV.setText(event.getName());
        eventCellTime.setText(event.getTime());
        eventCellDate.setText(event.getDate());
        String c = " ";
        if(event.isDone()){
             c = "v";
        }
        else{
            c = "x";
        }
        yesNo.setText(c);

        return convertView;
    }

}

