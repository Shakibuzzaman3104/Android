package com.example.shakibuzzaman.retrologin.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shakibuzzaman.retrologin.Interface.ApiInterface;
import com.example.shakibuzzaman.retrologin.R;
import com.example.shakibuzzaman.retrologin.Retrofit.RetrofitApiClient;
import com.example.shakibuzzaman.retrologin.Model.ServerResponse;
import com.example.shakibuzzaman.retrologin.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {

    EditText u,p;
    private FirebaseAuth mAuth;
    private ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        u=findViewById(R.id.ID);
        p=findViewById(R.id.Pass);
    }

    public void login(View view) {


        final String email;String Password;

        if(u.getText().toString().isEmpty() || p.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Please Fill all the places", Toast.LENGTH_SHORT).show();
        }

        else {
            email = u.getText().toString().trim();
            Password = p.getText().toString();
            User user = new User();
            user.setPass(Password);
            user.setEmail(email);
            if(!isValidEmail(email))
            {
                Toast.makeText(getApplicationContext(), "Invalid Email Format", Toast.LENGTH_SHORT).show();
            }

            else
            {
                checkUserValidity(user);
            }


        }

    }

    private void checkUserValidity(User userCredential)
    {
        Call<ServerResponse> call = apiInterface.getUserValidity(userCredential);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse validity = response.body();
                Toast.makeText(getApplicationContext(),validity.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Log.e("mess",t.getMessage());
                Toast.makeText(getApplicationContext(), "Connection Failed!!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public void Registers(View view) {

        Toast.makeText(getApplicationContext(), "Regitration Clicked", Toast.LENGTH_SHORT).show();
        Intent intent;
        intent = new Intent(MainActivity.this, Registration.class);
        startActivity(intent);
    }

    public void Show(View view) {
        Intent intent;

        intent=new Intent(MainActivity.this,UserList.class);
        startActivity(intent);
    }

    public void fire_Create(View view) {
        final String email,Password;
        email = u.getText().toString().trim();
        Password = p.getText().toString();
        createAccount(email,Password);
    }

    private void createAccount(String email,String password)
    {

        if(!isValidEmail(email))
        {
            Toast.makeText(this, "Invalid Email Address!!", Toast.LENGTH_SHORT).show();
        }
        else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            String TAG = "Email";
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }

                            // ...
                        }
                    });
        }
    }


    public void fire_Login(View view) {
        final String email,Password;
        email = u.getText().toString().trim();
        Password = p.getText().toString();
        signIn(email,Password);
    }

    private void signIn(String email,String password)
    {
        final String TAG = "Email";
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser)
    {
        if(currentUser==null)
        {
            Toast.makeText(this, "User Is Not Logged In", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "User is Already Logged In", Toast.LENGTH_SHORT).show();
    }


}


