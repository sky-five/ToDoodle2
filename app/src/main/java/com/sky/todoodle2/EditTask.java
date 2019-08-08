package com.sky.todoodle2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class EditTask extends AppCompatActivity {

    EditText titletodo,desctodo,datetodo;
    Button btnSaveTask,btnDelTask,btnCancel;
    DatabaseReference reference;
      Integer todoNum = new Random().nextInt();
    String keytodo = Integer.toString(todoNum);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        titletodo = findViewById(R.id.titletodo);
        desctodo = findViewById(R.id.desctodo);
        datetodo = findViewById(R.id.datetodo);

        btnSaveTask = findViewById(R.id.btnSaveTask);
        btnDelTask = findViewById(R.id.btnDelTask);
        btnCancel = findViewById(R.id.btnCancel);

        //get text from main activity
        titletodo.setText(getIntent().getStringExtra("titletodo"));
        desctodo.setText(getIntent().getStringExtra("desctodo"));
        datetodo.setText(getIntent().getStringExtra("datetodo"));

        final String keytodo2 = getIntent().getStringExtra("keytodo");

        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference = FirebaseDatabase.getInstance().getReference().child("ToDoodle2").
                        child("ToDo" + keytodo2);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("titletodo").setValue(titletodo.getText().toString());
                        dataSnapshot.getRef().child("desctodo").setValue(desctodo.getText().toString());
                        dataSnapshot.getRef().child("datetodo").setValue(datetodo.getText().toString());
                        dataSnapshot.getRef().child("keytodo").setValue(keytodo2);

                        Intent a = new Intent(EditTask.this,MainActivity.class);
                        startActivity(a);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        btnDelTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference = FirebaseDatabase.getInstance().getReference().child("ToDoodle2").
                        child("ToDo" + keytodo2);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().removeValue();

                        Intent a = new Intent(EditTask.this,MainActivity.class);
                        startActivity(a);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(EditTask.this,MainActivity.class);
                startActivity(a);
            }
        });
    }

}
