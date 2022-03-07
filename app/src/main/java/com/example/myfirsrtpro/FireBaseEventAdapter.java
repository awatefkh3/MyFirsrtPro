package com.example.myfirsrtpro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FireBaseEventAdapter extends ArrayAdapter<FireBaseEvent>{

        private Context context;
        private  int resource;

        public FireBaseEventAdapter(@NonNull Context context, int resource, List<FireBaseEvent> events ) {
            super(context, resource, events);
            this.context = context;
            this.resource = resource;
        }

        public FireBaseEventAdapter(@NonNull Context context, List<FireBaseEvent> events ) {
            super(context, 0, events);
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            FireBaseEvent event  = (FireBaseEvent)getItem(position);

            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell,parent,false);
            }

            TextView eventCellTV = convertView.findViewById(R.id.eventCellTV);

            String eventTitle = event.getName();
            eventCellTV.setText(eventTitle);

            return convertView;
        }
    }


