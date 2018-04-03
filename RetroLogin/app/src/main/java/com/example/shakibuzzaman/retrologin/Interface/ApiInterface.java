package com.example.shakibuzzaman.retrologin.Interface;

import com.example.shakibuzzaman.retrologin.Model.ServerResponse;
import com.example.shakibuzzaman.retrologin.Model.User;
import com.example.shakibuzzaman.retrologin.Model.UserResponse;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Shakibuzzaman on 3/28/2018.
 */

public interface ApiInterface {

    @POST("/Webconnect/php_helper.php")
    Call<ServerResponse> getUserValidity(@Body User userLoginCredential);

    @POST("/Webconnect/web_registration.php")
    Call<ServerResponse> UserRegistration(@Body User userRegistrationCredential);

    @GET("/Webconnect/json_parse.php")
    Call<UserResponse> getPeopleDetails();

}
