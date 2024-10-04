package com.example.smd_assignment2;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;

public class MyApplication extends Application {
    public static ArrayList<Todo> todos;
    public static ArrayList<Todo> dones;


    @Override
    public void onCreate() {
        super.onCreate();
        todos = new ArrayList<>();
        dones = new ArrayList<>();

        todos.add(new Todo("Give Evaluation", "Prepare evaluation for 30 Sep 2024"));
        todos.add(new Todo("Submit Project Report", "Project report due by 1 Oct 2024"));
    }
}
