package com.sky.todoodle2;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView titlepage, subtitlepage, endpage, btnAddNew;

    DatabaseReference reference;
    RecyclerView yourtodo;
    ArrayList<YourToDo> list;
    ToDoAdapter toDoAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titlepage=findViewById(R.id.titlepage);
        subtitlepage=findViewById(R.id.subtitlepage);
        endpage=findViewById(R.id.endpage);
        btnAddNew=findViewById(R.id.btnAddNew);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(MainActivity.this,NewTask.class);
                startActivity(a);
            }
        });

        //working with data
        yourtodo=findViewById(R.id.yourtodo);
        yourtodo.setLayoutManager(new LinearLayoutManager(this));
        list= new ArrayList<YourToDo>();

        //getting data from fb
        reference= FirebaseDatabase.getInstance().getReference().child("ToDoodle2");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //code to retrieve, replace layout
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    YourToDo p=dataSnapshot1.getValue(YourToDo.class);
                    list.add(p);
                }
                toDoAdapter=new ToDoAdapter(MainActivity.this,list);
                yourtodo.setAdapter(toDoAdapter);
                toDoAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //show error
                Toast.makeText(getApplicationContext(),"no data",Toast.LENGTH_SHORT).show();

            }
        });

    }


}
