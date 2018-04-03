package com.example.shakib.firebase.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.shakib.firebase.Helpers.MyAdapter;
import com.example.shakib.firebase.Model.Users;
import com.example.shakib.firebase.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class UserDetails extends Activity {

    private DatabaseReference userDatabase;

    ArrayList<Users> arUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        userDetails();
    }

    private void userDetails()
    {
        userDatabase= FirebaseDatabase.getInstance().getReference("users");

        arUsers = new ArrayList<>();

        userDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                arUsers.clear();

                for(DataSnapshot UserSnapShot:dataSnapshot.getChildren())
                {
                    Users user = UserSnapShot.getValue(Users.class);

                    arUsers.add(user);
                }

                RecyclerView recyclerView = findViewById(R.id.myRecyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(UserDetails.this));
                MyAdapter myMyAdapter = new MyAdapter(UserDetails.this,arUsers);
                recyclerView.setAdapter(myMyAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
