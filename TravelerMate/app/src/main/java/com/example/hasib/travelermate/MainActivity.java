package com.example.hasib.travelermate;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RadioButton hp, ps, bk, at;
    Button Find;
    Double lat, lon;
    Sub_URL sub_url;
    List<PlacesResponse.Result> myResult;
    int Size;
    int flag;
    EditText et;
    String inputLocation;
    String place;


    List<Address> addresses;
    double latitude[];
    double longitude[];
    String[] Name;
    String[] poly;
    PlacesResponse body;
    Intent in;
    String markerInterest = "all";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        hp = (RadioButton) findViewById(R.id.hospital);
        ps = (RadioButton) findViewById(R.id.police);
        bk = (RadioButton) findViewById(R.id.bank);
        at = (RadioButton) findViewById(R.id.atm);
        Find = (Button) findViewById(R.id.find);
        // et =(EditText) findViewById(R.id.input);
        LibraryInitializer();
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                inputLocation = place.getName().toString();
                // TODO: Get info about the selected place.
                //Log.i(TAG, "Place: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                // Log.i(TAG, "An error occurred: " + status);
            }
        });


    }

    public void Hospital(View view) {
        hp.setChecked(true);
        ps.setChecked(false);
        bk.setChecked(false);
        at.setChecked(false);
        flag = 0;
    }

    public void Police(View view) {
        hp.setChecked(false);
        ps.setChecked(true);
        bk.setChecked(false);
        at.setChecked(false);
        flag = 1;
    }

    public void Bank(View view) {
        hp.setChecked(false);
        ps.setChecked(false);
        bk.setChecked(true);
        at.setChecked(false);
        flag = 2;
    }

    public void ATM(View view) {
        hp.setChecked(false);
        ps.setChecked(false);
        bk.setChecked(false);
        at.setChecked(true);
        flag = 3;
    }

    public void LibraryInitializer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Main_URL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        sub_url = retrofit.create(Sub_URL.class);
    }

    public void getMyLocationAddress() {


        // inputLocation= et.getText().toString();
        // Toast.makeText(getApplicationContext(),inputLocation, Toast.LENGTH_SHORT).show();
        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);


        try {

            //Place your latitude and longitude
            addresses = geocoder.getFromLocationName(inputLocation, 1);

            if (addresses != null) {

                Address fetchedAddress = addresses.get(0);

                StringBuilder strAddress = new StringBuilder();

                for (int i = 0; i < fetchedAddress.getMaxAddressLineIndex(); i++) {
                    strAddress.append(fetchedAddress.getAddressLine(i)).append("\n");
                }

                lat = addresses.get(0).getLatitude();
                lon = addresses.get(0).getLongitude();

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Could not get address..!", Toast.LENGTH_LONG).show();
        }

    }


    public void GetData() {


        switch (flag) {
            case 0:
                place = "hospital";
                markerInterest = "hospital";

                break;
            case 1:
                place = "police";
                markerInterest = "police";
                break;
            case 2:
                place = "bank";
                markerInterest = "bank";
                break;
            case 3:
                place = "atm";
                markerInterest = "atm";
                break;
            default:
                markerInterest = "all";
        }

//
        //  Toast.makeText(getApplicationContext(),lat.toString()+"\n"+lon.toString(), Toast.LENGTH_SHORT).show();
        //  Toast.makeText(getApplicationContext(),place, Toast.LENGTH_SHORT).show();

        String urls = "maps/api/place/nearbysearch/json?location=" + lat + "," + lon + "&radius=1500&type=" + place + "&key=AIzaSyD37KOcvnZN74V5HCk0J-UKP9mOHHqHGis";
        Log.e("urls", urls);
        Call<PlacesResponse> MainResponseCall = sub_url.getPlaces(urls);


        MainResponseCall.enqueue(new Callback<PlacesResponse>() {
            @Override
            public void onResponse(Call<PlacesResponse> call, Response<PlacesResponse> response) {
                body = response.body();
                myResult = body.getResults();
                Size = myResult.size();
                latitude = new double[myResult.size()];
                longitude = new double[myResult.size()];
                Name = new String[myResult.size()];
                poly = new String[myResult.size()];


                in = new Intent(MainActivity.this, MapsActivity.class);
                //     //Details
                for (int i = 0; i < Size; i++) {
                    poly[i] = myResult.get(i).getVicinity().toString();
                    latitude[i] = myResult.get(i).getGeometry().getLocation().getLat();
                    longitude[i] = myResult.get(i).getGeometry().getLocation().getLng();
                    Name[i] = myResult.get(i).getName().toString();


                    // Toast.makeText(MainActivity.this,test1.toString(), Toast.LENGTH_SHORT).show();
                }
                Log.e("Size : ", latitude.length + "");
                in.putExtra(Constant.MARKER_INTEREST, markerInterest);
                in.putExtra(Constant.Lattitude, latitude);
                in.putExtra(Constant.Longitude, longitude);
                in.putExtra(Constant.Name, poly);
                in.putExtra(Constant.polygon, Name);
                in.putExtra(Constant.Main_Lat, lat);
                in.putExtra(Constant.Main_Lon, lon);

                //ArrayList<String> myList = (ArrayList<String>) getIntent().getSerializableExtra("mylist");

                //  in.putExtra(Constant.Longitude,myResult.get(i).getGeometry().getLocation().getLng());
                //  in.putExtra(Constant.Name,myResult.get(i).getName());
                startActivity(in);

                //  Log.e("urls", String.valueOf(myResult.get(0).getGeometry().getLocation().getLat()));


            }

            @Override
            public void onFailure(Call<PlacesResponse> call, Throwable t) {

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
//        getMyLocationAddress();
//        GetData();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void Find(View view) {
        findLocation();
    }

    void findLocation()
    {
        if(inputLocation==null)
        {
            Toast.makeText(this, "Please Enter a Location First!!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            getMyLocationAddress();
            GetData();
        }
    }


}
