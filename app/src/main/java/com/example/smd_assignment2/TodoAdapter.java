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
public class TodoAdapter extends ArrayAdapter<Todo> {

    private Context context;
    private ArrayList<Todo> todos;
    private ArrayList<Todo> dones;  // Reference to the "done" list
    private DoneAdapter doneAdapter; // Reference to the DoneAdapter
    private int resourceLayout;

    public TodoAdapter(@NonNull Context context, int resource, ArrayList<Todo> todos, ArrayList<Todo> dones, DoneAdapter doneAdapter) {
        super(context, resource, todos);
        this.context = context;
        this.todos = todos;
        this.dones = dones;
        this.doneAdapter = doneAdapter;
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
        TextView todoTitle = view.findViewById(R.id.tvHeading);
        TextView todoDescription = view.findViewById(R.id.tvDescription);
        ImageView btnDelete = view.findViewById(R.id.btnDelete);

        Todo currentTodo = todos.get(position);

        // Set the title and description text
        todoTitle.setText(currentTodo.getTitle());
        todoDescription.setText(currentTodo.getDescription());

        // Handle delete button click to move to the "done" list
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Move the item to the done list
                dones.add(currentTodo);

                // Remove the item from the todo list
                todos.remove(position);

                // Notify both adapters that the data has changed
                notifyDataSetChanged();        // Refresh the To-Do list
                doneAdapter.notifyDataSetChanged(); // Refresh the Done list
            }
        });


        return view;
    }
}
