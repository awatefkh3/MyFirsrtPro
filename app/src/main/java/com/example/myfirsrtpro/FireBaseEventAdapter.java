package com.example.myfirsrtpro;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FireBaseEventAdapter extends ArrayAdapter<FireBaseEvent>{

        private TextView eventCellTV,eventCellTime,eventCellDate;
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

             eventCellTV = convertView.findViewById(R.id.eventCellTV);
             eventCellTime = convertView.findViewById(R.id.eventCellTime);
             eventCellDate = convertView.findViewById(R.id.eventCellDate);

            eventCellTV.setText(event.getName());
            eventCellTime.setText(event.getTime());
            eventCellDate.setText(event.getDate());


            return convertView;
        }

    public void checkDone(FireBaseEvent fireBaseEvent){
            fireBaseEvent.setDone(true);
            eventCellTV.setPaintFlags(eventCellTV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            eventCellDate.setPaintFlags(eventCellDate.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            eventCellTime.setPaintFlags(eventCellTime.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    public void undoCheckDone(FireBaseEvent fireBaseEvent){
            fireBaseEvent.setDone(false);
            eventCellTV.setPaintFlags(eventCellTV.getPaintFlags() & ( ~ Paint.STRIKE_THRU_TEXT_FLAG));
            eventCellDate.setPaintFlags(eventCellDate.getPaintFlags() & ( ~ Paint.STRIKE_THRU_TEXT_FLAG));
            eventCellTime.setPaintFlags(eventCellTime.getPaintFlags()  & ( ~ Paint.STRIKE_THRU_TEXT_FLAG));
    }
    }


