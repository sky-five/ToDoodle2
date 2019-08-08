package com.sky.todoodle2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {

    Context context;
    ArrayList<YourToDo> yourToDos;

    public  ToDoAdapter(Context c, ArrayList<YourToDo> p){
        context=c;
        yourToDos=p;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_todo,viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.titletodo.setText(yourToDos.get(i).getTitletodo());
        myViewHolder.desctodo.setText(yourToDos.get(i).getDesctodo());
        myViewHolder.datetodo.setText(yourToDos.get(i).getDatetodo());

        final String getTitletodo = yourToDos.get(i).getTitletodo();
        final String getDesctodo = yourToDos.get(i).getDesctodo();
        final String getDatetodo = yourToDos.get(i).getDatetodo();
        final String getKeytodo = yourToDos.get(i).getKeytodo();

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aa = new Intent(context,EditTask.class);
                aa.putExtra("titletodo", getTitletodo);
                aa.putExtra("desctodo", getDesctodo);
                aa.putExtra("datetodo", getDatetodo);
                aa.putExtra("keytodo", getKeytodo);
                context.startActivity(aa);
            }
        });

    }

    @Override
    public int getItemCount() {
        return yourToDos.size();
    }

    class MyViewHolder  extends RecyclerView.ViewHolder {

        TextView titletodo,desctodo,datetodo,keytodo;

         public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            titletodo=(TextView) itemView.findViewById(R.id.titletodo);
            desctodo=(TextView) itemView.findViewById(R.id.desctodo);
            datetodo=(TextView) itemView.findViewById(R.id.datetodo);
        }
    }
}
