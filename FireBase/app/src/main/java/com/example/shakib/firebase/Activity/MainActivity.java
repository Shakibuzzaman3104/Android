package com.example.shakib.firebase.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shakib.firebase.Helpers.Helper;
import com.example.shakib.firebase.Model.Users;
import com.example.shakib.firebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Helper helper= new Helper();
    EditText firstname,lastname,email,age,bg,pass;
    private FirebaseUser user;
    FirebaseDatabase database ;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();

        mAuth = FirebaseAuth.getInstance();  //FireBase Initialization

        firstname=(EditText) findViewById(R.id.fn);
        lastname=(EditText) findViewById(R.id.ln);
        email=(EditText) findViewById(R.id.email);
        age=(EditText) findViewById(R.id.age);
        bg=(EditText) findViewById(R.id.bg);
        pass=(EditText) findViewById(R.id.st_pass);

    }
    @Override
    public void onStart()
    {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

       /* if(currentUser!=null)
        {
            final List<Users> userList = new ArrayList<>();
            myRef.child(getUID()).addValueEventListener(new ValueEventListener() {
                public static final String TAG ="Checker" ;

                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    userList.clear();
                    for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                        Users user = postSnapshot.getValue(Users.class);
                        userList.add(user);

                        // here you can access to name property like university.name

                    }

                    Toast.makeText(MainActivity.this, ""+userList.size(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.w(TAG, "Failed to read value.",databaseError.toException());
                }
            });
        }*/
    }



    public void register(View view) {

        final String FirstName,LastName,Email,Age,BG,Pass;

        FirstName=firstname.getText().toString();
        LastName=lastname.getText().toString();
        Age=age.getText().toString();
        Email=email.getText().toString().trim();
        BG=bg.getText().toString();
        Pass=pass.getText().toString();

       final boolean res= helper.isValidEmail(Email);

       final boolean empty=helper.IsEmpty(FirstName,LastName,Age,Email,BG,Pass);

       if(empty)
       {
           Toast.makeText(getApplicationContext(), "Fill All The Places", Toast.LENGTH_SHORT).show();
       }
       else
       {
           if(!res)
           {
               Toast.makeText(getApplicationContext(), "Invalid Email Address!!", Toast.LENGTH_SHORT).show();
           }

           else
           {
              createAccount(Email,Pass,FirstName,LastName,Age,BG);

           }
       }

    }

    private void writeDatabase(String FirstName,String LastName,String Age,String Email,String BG)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final String uid=getUID();
        DatabaseReference myRef = database.getReference("users");

        Users user = new Users(Age,BG,Email,FirstName,LastName);

        myRef.child(uid).setValue(user);
        /*myRef.child(uid).child("First_Name").setValue(FirstName);

        myRef.child(uid).child("Last_Name").setValue(LastName);
        myRef.child(uid).child("Age").setValue(Age);
        myRef.child(uid).child("Email").setValue(Email);
        myRef.child(uid).child("BG").setValue(BG);*/

    }


    private String getUID()
    {
        FirebaseUser currentFirebaseUser = mAuth.getCurrentUser();
        return currentFirebaseUser.getUid();
    }


    private void createAccount(final String email, String password, final String FirstName, final String LastName, final String Age, final String BG)
    {
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    // user = mAuth.getCurrentUser();
                    writeDatabase(FirstName,LastName,Age,email,BG);
                    Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();


                }
                else
                {
                    Log.e("failed : ",""+task.getException());
                    Toast.makeText(MainActivity.this, "Registration Failed : "+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void login(View view) {

        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}

