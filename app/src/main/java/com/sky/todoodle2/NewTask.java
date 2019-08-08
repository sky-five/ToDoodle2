package com.sky.todoodle2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class NewTask extends AppCompatActivity {

    EditText titletodo, desctodo, datetodo;
    Button btnSaveTask, btnCancel;
    DatabaseReference reference;
    Integer todoNum = new Random().nextInt();
    String keytodo = Integer.toString(todoNum);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        titletodo = findViewById(R.id.titletodo);
        desctodo = findViewById(R.id.desctodo);
        datetodo = findViewById(R.id.datetodo);

        btnSaveTask = findViewById(R.id.btnSaveTask);
        btnCancel = findViewById(R.id.btnCancel);

        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // insert data to database
                reference = FirebaseDatabase.getInstance().getReference().child("ToDoodle2").
                        child("ToDo" + todoNum);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("titletodo").setValue(titletodo.getText().toString());
                        dataSnapshot.getRef().child("desctodo").setValue(desctodo.getText().toString());
                        dataSnapshot.getRef().child("datetodo").setValue(datetodo.getText().toString());
                        dataSnapshot.getRef().child("keytodo").setValue(keytodo);

                        Intent a = new Intent(NewTask.this,MainActivity.class);
                        startActivity(a);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(NewTask.this,MainActivity.class);
                startActivity(a);
            }
        });

    }
}
