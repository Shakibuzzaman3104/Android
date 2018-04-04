package com.example.shakib.firebase.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shakib.firebase.R;


public class ShowFullDetails extends Activity {

    private TextView name,age,bg,email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_user_details);

        Intent intent = getIntent();

        String Name = intent.getStringExtra("Name");
       // Toast.makeText(this, ""+Name, Toast.LENGTH_SHORT).show();
        String AGE = intent.getStringExtra("Age");
      //  Toast.makeText(this, ""+AGE, Toast.LENGTH_SHORT).show();
        String Blood = intent.getStringExtra("Blood_Group");
        String Mail = intent.getStringExtra("Email");

        name=(TextView)findViewById(R.id.uname);
        age=(TextView)findViewById(R.id.uage);
        bg=(TextView)findViewById(R.id.uBG);
        email=(TextView)findViewById(R.id.uEmail);


        name.setText("Name : "+Name);
        age.setText("Age : "+AGE);
        bg.setText("Blood Group : "+Blood);
        email.setText("Email : "+Mail);

    }
}
