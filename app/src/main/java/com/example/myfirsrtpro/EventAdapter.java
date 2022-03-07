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

/*
public class EventAdapter extends ArrayAdapter<Event> {


    private  Context context;
    private  int resource;

    public EventAdapter(@NonNull Context context, int resource, List<Event> events ) {
        super(context, resource, events);
        this.context = context;
        this.resource = resource;
    }

    public EventAdapter(@NonNull Context context, List<Event> events ) {
        super(context, 0, events);
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Event event  = (Event) getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell,parent,false);
        }

        TextView eventCellTV = convertView.findViewById(R.id.eventCellTV);

        String eventTitle = event.getName()+" "+CalendarUtils.formattedTime(event.getTime());
        eventCellTV.setText(eventTitle);

        return convertView;
    }
}
*/
//todo delete this