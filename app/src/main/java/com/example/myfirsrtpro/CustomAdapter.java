package com.example.myfirsrtpro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Item> {

    private Context context;
    private int resource;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null)
            view = LayoutInflater.from(context).inflate(resource, parent,false);

        Item item = getItem(position);//method from the android studio, not related to the item object(the class we created)
        if(item!=null){
            ImageView imageView = view.findViewById(R.id.imageItem);
            TextView textViewDescription = view.findViewById(R.id.textView1);
            Button itemButton = view.findViewById(R.id.itemButton);
            itemButton.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                          Toast.makeText(context,"this item was added to shopping cart",Toast.LENGTH_LONG).show();
                      }
                  });
            imageView.setImageResource(item.getResid());
            textViewDescription.setText(item.getDescription());
        }
        return view;
    }
}

//todo dlete this or keep it as an example