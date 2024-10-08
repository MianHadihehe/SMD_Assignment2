package com.example.smd_assignment2;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;


public class AddNewFragment extends Fragment {

    Button btnSubmit;
    Context context;
    TextInputEditText tietHeading;
    TextInputEditText tietDescription;

    public AddNewFragment() {
        // Required empty public constructor
    }


    public interface OnNewItemAddedListener {
        void onNewItemAdded(String heading, String description);
    }

    // Variable to hold the callback instance
    private OnNewItemAddedListener parentRef;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        parentRef = (OnNewItemAddedListener) context;
        Log.d(TAG, "onAttach called for add frag");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate called for add frag");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView called for add frag");
        return inflater.inflate(R.layout.fragment_add_new, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated called for add frag");
        btnSubmit = view.findViewById(R.id.btn_submit);
        tietHeading = view.findViewById(R.id.input_heading);  // Make sure this ID matches your XML
        tietDescription = view.findViewById(R.id.input_description);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String heading=tietHeading.getText().toString();
                String description=tietDescription.getText().toString();
                if(heading.isEmpty() || description.isEmpty()){
                    Toast.makeText(getActivity(), "Must fill in both fields to continue", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (parentRef != null) {
                        parentRef.onNewItemAdded(heading,description);
                        tietHeading.setText("");
                        tietDescription.setText("");
                    }
                }
            }
        });
    }
}