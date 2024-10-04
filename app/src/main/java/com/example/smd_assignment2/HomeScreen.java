package com.example.smd_assignment2;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity implements AddNewFragment.OnNewItemAddedListener {

    ListView lvTodo_list;
    ListView lvDone_list;
    ArrayList<Todo> todos, dones;

    TextView tvDone;
    TextView tvTodo;
    ExtendedFloatingActionButton btnAddNew;

    // hooks of fragments
    Fragement_todo_list todo_frag;
    Fragement_done_list done_frag;
    AddNewFragment new_frag;
    FragmentManager fragmentManager;
    TodoAdapter todoadapter;
    DoneAdapter doneadapter;
    FrameLayout add_new_page;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        fragmentManager = getSupportFragmentManager();
        todo_frag=(Fragement_todo_list) fragmentManager.findFragmentById(R.id.todo_list_frag);
        done_frag=(Fragement_done_list) fragmentManager.findFragmentById(R.id.done_list_frag);
        new_frag = (AddNewFragment) fragmentManager.findFragmentById(R.id.add_todo_frag);

        tvDone = findViewById(R.id.tvDone);
        tvTodo = findViewById(R.id.tvTodo);
        btnAddNew = findViewById(R.id.fbtnAddTodo);

        add_new_page=new_frag.getView().findViewById(R.id.add_new_page);

//        if(add_new_page==null){
//            btnAddNew.hide();
//        }
//        else{
//            btnAddNew.show();
//        }

        todos = MyApplication.todos;
        dones = MyApplication.dones;


        fragmentManager.beginTransaction().show(todo_frag).hide((done_frag)).hide(new_frag).commit();

        tvTodo.setTextColor(getResources().getColor(R.color.red));


        if (done_frag != null && done_frag.getView() != null) {
            lvDone_list = done_frag.getView().findViewById(R.id.done_list);
            doneadapter = new DoneAdapter(this, R.layout.single_done_design, dones);
            lvDone_list.setAdapter(doneadapter);
        }


        if (todo_frag != null && todo_frag.getView() != null) {
            lvTodo_list = todo_frag.getView().findViewById(R.id.todo_list);
            todoadapter = new TodoAdapter(this, R.layout.single_todo_design, todos, dones, doneadapter);
            lvTodo_list.setAdapter(todoadapter);
        }



        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().show(done_frag).hide((todo_frag)).hide(new_frag).addToBackStack(null).commit();
                tvDone.setTextColor(getResources().getColor(R.color.red));
                tvTodo.setTextColor(getResources().getColor(R.color.white));
            }
        });

        tvTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().show(todo_frag).hide((done_frag)).hide(new_frag).addToBackStack(null).commit();
                tvTodo.setTextColor(getResources().getColor(R.color.red));
                tvDone.setTextColor(getResources().getColor(R.color.white));
            }
        });

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().hide(todo_frag).hide((done_frag)).show(new_frag).addToBackStack(null).commit();
            }
        });


    }

    public void onNewItemAdded(String heading, String description) {
        MyApplication.todos.add(new Todo(heading, description));
        todoadapter.notifyDataSetChanged();
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction().show(todo_frag).hide(done_frag).hide(new_frag).commit();
    }

}