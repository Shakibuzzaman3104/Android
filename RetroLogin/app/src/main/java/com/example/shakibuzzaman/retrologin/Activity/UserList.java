package com.example.shakibuzzaman.retrologin.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.shakibuzzaman.retrologin.Interface.ApiInterface;
import com.example.shakibuzzaman.retrologin.Model.User;
import com.example.shakibuzzaman.retrologin.Model.UserResponse;
import com.example.shakibuzzaman.retrologin.R;
import com.example.shakibuzzaman.retrologin.Retrofit.RetrofitApiClient;
import com.example.shakibuzzaman.retrologin.myAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserList extends AppCompatActivity {

    ApiInterface apiInterface;
    private ArrayList<UserList> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);


        /*ArrayList<User> userDetails = new ArrayList<>();

        User user = new User();
        user.setName("Hasib");
        user.setEmail("Hasib824@gmail.com");

        userDetails.add(user);

        User user1 = new User();
        user1.setName("Shakib");
        user1.setEmail("Hasib824@gmail.com");

        userDetails.add(user1);

        User user2 = new User();
        user2.setName("Shakil");
        user2.setEmail("Hasib824@gmail.com");

        userDetails.add(user2);*/
        userdetails();




    }

    private void userdetails()
    {
        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> call = apiInterface.getPeopleDetails();

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                UserResponse userresponse = response.body();

                ArrayList<UserResponse.User> users = new ArrayList<>(userresponse.getUsers());


                Toast.makeText(UserList.this, ""+users.size(), Toast.LENGTH_SHORT).show();



                RecyclerView recyclerView = findViewById(R.id.myRecyclerView);

                recyclerView.setLayoutManager(new LinearLayoutManager(UserList.this));

                myAdapter myAdapter = new myAdapter(UserList.this,users);

                recyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

                Toast.makeText(UserList.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });
    }
}
