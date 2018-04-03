package com.example.shakibuzzaman.retrologin.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shakibuzzaman on 3/28/2018.
 */

public class RetrofitApiClient {

    private final static String base_url="http://192.168.0.108";

    private static Retrofit retrofit=null;

    private static Gson gson=new GsonBuilder().setLenient().create();

    private RetrofitApiClient(){}

    public static synchronized Retrofit getClient(){

        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
