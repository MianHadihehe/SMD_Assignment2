package com.example.smd_assignment2;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class DoneAdapter extends ArrayAdapter<Todo> {

    private Context context;
    private ArrayList<Todo> dones;
    private int resourceLayout;

    public DoneAdapter(@NonNull Context context, int resource, ArrayList<Todo> dones) {
        super(context, resource, dones);
        this.context = context;
        this.dones = dones;
        this.resourceLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(resourceLayout, null);
        }

        // Find the TextViews for title and description
        TextView doneTitle = view.findViewById(R.id.tvHeading);
        TextView doneDescription = view.findViewById(R.id.tvDescription);


        Todo currentDone = dones.get(position);

        // Set the title and description text
        doneTitle.setText(currentDone.getTitle());
        doneDescription.setText(currentDone.getDescription());

        return view;
    }
}
