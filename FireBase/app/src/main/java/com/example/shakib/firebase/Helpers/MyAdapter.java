package com.example.shakib.firebase.Helpers;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shakib.firebase.Activity.ShowFullDetails;
import com.example.shakib.firebase.Model.Users;
import com.example.shakib.firebase.R;

import java.util.ArrayList;
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context mContext;

    ArrayList<Users> arUsers;

    public MyAdapter(Context mContext, ArrayList<Users> arUsers) {
        this.mContext=mContext;
        this.arUsers=arUsers;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView FirstName,Age,Blood_Group,Email;
        public MyViewHolder(View itemView) {
            super(itemView);

            FirstName=itemView.findViewById(R.id.textView);
            /*Age=itemView.findViewById(R.id.textView3);
            Blood_Group=itemView.findViewById(R.id.textView4);
            Email=itemView.findViewById(R.id.textView5);*/
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final String Name =arUsers.get(position).getFirst_Name()+" "+arUsers.get(position).getLast_Name();

        holder.FirstName.setText(Name);
      //  holder.LastName.setText(arUsers.get(position).getLast_Name());
       /* holder.Age.setText(arUsers.get(position).getAge());
        holder.Blood_Group.setText(arUsers.get(position).getBG());
        holder.Email.setText(arUsers.get(position).getEmail());*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(mContext,ShowFullDetails.class);
                i.putExtra("Name", ""+Name.toString());
                i.putExtra("Age", ""+arUsers.get(position).getAge().toString());
                i.putExtra("Blood_Group", ""+arUsers.get(position).getBG().toString());
                i.putExtra("Email", ""+arUsers.get(position).getEmail().toString());
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arUsers.size();
    }
}
