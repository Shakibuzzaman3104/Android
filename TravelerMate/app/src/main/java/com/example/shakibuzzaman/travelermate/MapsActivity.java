package com.example.shakibuzzaman.travelermate;

import android.content.DialogInterface;
import android.content.pm.PackageManager;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


     GoogleMap mMap;
        int flag=0;
     //String Type="MAP_TYPE_SATELLITE";
     private static final CharSequence[] MAP_TYPE_ITEMS =
             {"Road Map", "Hybrid", "Satellite", "Terrain"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {



        mMap = googleMap;
        double[] lattitudeArray= getIntent().getDoubleArrayExtra(Constant.Lattitude);
        double[] LongitudeArray= getIntent().getDoubleArrayExtra(Constant.Longitude);
        double m_lat = getIntent().getDoubleExtra(Constant.Main_Lat,0.0);
        double m_lon = getIntent().getDoubleExtra(Constant.Main_Lon,0.0);
        String [] placesname = getIntent().getStringArrayExtra(Constant.Name);
        String [] poly = getIntent().getStringArrayExtra(Constant.polygon);


        LatLng SelectedLocation=new LatLng(m_lat,m_lon);


        for (int i=0;i<lattitudeArray.length;i++)
        {
            Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(lattitudeArray[i], LongitudeArray[i])).title("" +poly [i]).snippet(""+placesname[i]));
            setmarker(marker,getIntent().getStringExtra(Constant.MARKER_INTEREST));
           // Log.e("Marker ", getIntent().getStringExtra(Constants.MARKER_INTEREST));

        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SelectedLocation,14));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        showMapTypeSelectorDialog();
       // mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    }
    private void showMapTypeSelectorDialog() {
        // Prepare the dialog by setting up a Builder.
        final String fDialogTitle = "Select Map Type";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(fDialogTitle);

        // Find the current map type to pre-check the item representing the current state.
        int checkItem = mMap.getMapType() - 1;

        // Add an OnClickListener to the dialog, so that the selection will be handled.
        builder.setSingleChoiceItems(
                MAP_TYPE_ITEMS,
                checkItem,
                new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialog, int item) {

                        // Locally create a finalised object.

                        // Perform an action depending on which item was selected.
                        switch (item) {
                            case 1:
                                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                                break;
                            case 2:
                                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                                break;
                            case 3:
                                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                                break;
                            default:
                                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        }
                        dialog.dismiss();
                    }
                }
        );

        // Build the dialog and show it.

        AlertDialog fMapTypeDialog = builder.create();
        fMapTypeDialog.setCanceledOnTouchOutside(true);
        fMapTypeDialog.show();
    }

    public void menu(View view) {
        showMapTypeSelectorDialog();

    }

    private Bitmap getMarkerBitmapFromView(@DrawableRes int resId) {

        View customMarkerView = ((LayoutInflater) getSystemService(this.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_marker, null);
        ImageView markerImageView = (ImageView) customMarkerView.findViewById(R.id.profile_image);
        markerImageView.setImageResource(resId);
        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(3, 3, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return returnedBitmap;
    }

    public void setmarker(Marker marker1, String marker) {
        switch (marker)
        {
            case "atm": marker1.setIcon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(R.drawable.atm1)));
                break ;
            case "hospital": marker1.setIcon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(R.drawable.hospital)));
                break ;
            case "bank": marker1.setIcon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(R.drawable.bank)));
                break ;
            case "police": marker1.setIcon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(R.drawable.police)));
                break ;
            case "all": marker1.setIcon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(R.drawable.location)));
                break ;
        }
    }

    public void Back(View view) {
        onBackPressed();
        finish();
    }

}
