package com.example.shakibuzzaman.retrologin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shakibuzzaman.retrologin.Model.User;
import com.example.shakibuzzaman.retrologin.Model.UserResponse;

import java.util.ArrayList;
import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.MyViewHolder> {


    Context mContext;
    List<UserResponse.User> userDetails;

    public myAdapter(Context mContext, ArrayList<UserResponse.User> userDetails)
    {
        this.mContext = mContext;
        this.userDetails = userDetails;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView etName, age,blood,email;

        public MyViewHolder(View itemView) {
            super(itemView);
            etName = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            blood=itemView.findViewById(R.id.bg);
            email=itemView.findViewById(R.id.em);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_recyclerview, null);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.etName.setText(userDetails.get(position).getName());
        holder.age.setText(userDetails.get(position).getAge());
        holder.blood.setText(userDetails.get(position).getBloodGroup());
        holder.email.setText(userDetails.get(position).getEmail());

    }

    @Override
    public int getItemCount() {
        return userDetails.size();
    }
}
