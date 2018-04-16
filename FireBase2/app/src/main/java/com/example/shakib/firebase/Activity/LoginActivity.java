package com.example.shakib.firebase.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shakib.firebase.Helpers.Helper;
import com.example.shakib.firebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends Activity {

    private FirebaseAuth fauth;

    EditText em,ps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        fauth=FirebaseAuth.getInstance();

        em=findViewById(R.id.l_email);
        ps=findViewById(R.id.l_pass);

    }

    public void logins(View view) {
        Helper helper=new Helper();

        String Email,Pass;

        Email=em.getText().toString();
        Pass=ps.getText().toString();

        final boolean field_check = helper.IsEmpty(Email,Pass);
        final boolean email_check = helper.isValidEmail(Email.trim());

        if(field_check)
        {
            Toast.makeText(this, "Fill all the Places", Toast.LENGTH_SHORT).show();
        }

        else
        {
            if(!email_check)
            {
                Toast.makeText(this, "Invalid Email Format", Toast.LENGTH_SHORT).show();
            }

            else
            {
                signIn(Email,Pass);
            }
        }


    }

    private void signIn(String email,String pass)
    {
        fauth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(LoginActivity.this,UserDetails.class);
                            startActivity(intent);
                        }

                        else
                        {
                            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}
