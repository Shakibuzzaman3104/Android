package com.example.shakibuzzaman.travelermate;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Hasib on 12/5/2016.
 */

public interface Sub_URL {
    //https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&key=AIzaSyBnMR11fi5wUZUX7sgVy9MVlMgiW2UGVmM

    @GET
    Call<PlacesResponse> getPlaces(@Url String urls);

    // https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=23.7839627,90.3682179&radius=500&type=restaurent&key=AIzaSyBnMR11fi5wUZUX7sgVy9MVlMgiW2UGVmM

    // https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=23.7933187,90.376601&radius=1000&type=hospital&key=AIzaSyD37KOcvnZN74V5HCk0J-UKP9mOHHqHGis


}
