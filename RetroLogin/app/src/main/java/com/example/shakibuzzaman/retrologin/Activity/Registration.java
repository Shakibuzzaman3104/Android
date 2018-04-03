package com.example.shakibuzzaman.retrologin.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shakibuzzaman.retrologin.Interface.ApiInterface;
import com.example.shakibuzzaman.retrologin.Model.ServerResponse;
import com.example.shakibuzzaman.retrologin.Model.User;
import com.example.shakibuzzaman.retrologin.R;
import com.example.shakibuzzaman.retrologin.Retrofit.RetrofitApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends Activity {
    private ApiInterface apiInterface;

    EditText FirstName,LastName,Age,BG,Email,Pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        FirstName=(EditText)findViewById(R.id.fn);
        LastName=(EditText)findViewById(R.id.ln);
        Age=(EditText)findViewById(R.id.age);
        BG=(EditText)findViewById(R.id.bg);
        Email=(EditText)findViewById(R.id.email);
        Pass=(EditText)findViewById(R.id.st_pass);
    }
    public void Reg(View view)
    {
        String firstname,lastname,age,blood_group,email,pass;
        firstname=FirstName.getText().toString();
        lastname=LastName.getText().toString();
        age=Age.getText().toString();
        blood_group=BG.getText().toString();
        email=Email.getText().toString().trim();
        pass=Pass.getText().toString();
        if(firstname.isEmpty() || lastname.isEmpty() || age.isEmpty() || blood_group.isEmpty() || email.isEmpty() || pass.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Please Fill all the places", Toast.LENGTH_SHORT).show();
        }

        else
        {
            final String name=firstname+" "+lastname;
            User user=new User();
            user.setAge(age);
            user.setName(name);
            user.setBloodGroup(blood_group);
            user.setEmail(email);
            user.setPass(pass);

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
        Call<ServerResponse> call = apiInterface.UserRegistration(userCredential);
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
}
