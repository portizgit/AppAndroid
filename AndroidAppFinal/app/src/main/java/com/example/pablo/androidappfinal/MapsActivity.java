package com.example.pablo.androidappfinal;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public GoogleMap mMap;
    public MarkerOptions markerOptions;
    public MarkerOptions minicio;
    public boolean existe;
    public String city;
    ArrayList<Dia>dias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }





    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        minicio=new MarkerOptions();

        LatLng inicial = new LatLng(40.4168, -3.7038);
        minicio.position(inicial).title("Madrid").snippet("Pulsa para consultar meteorología");
        mMap.addMarker(minicio);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom((inicial), 5));
        setUpMapIfNeeded();


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {

                existe=true;
                
                Location location = new Location("Test");
                location.setLatitude(point.latitude);
                location.setLongitude(point.longitude);

                city="";
                LatLng newLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                try {
                    List<Address> addresses = geocoder.getFromLocation(point.latitude, point.longitude, 1);
                    if (addresses!=null) {

                        city = addresses.get(0).getLocality();
                        if(!city.equals("")) {

                            Toast.makeText(getApplicationContext(), city,
                                    Toast.LENGTH_SHORT).show();
                            markerOptions = new MarkerOptions()
                                    .position(newLatLng)
                                    .title(city).snippet("Pulsa para consultar meteorología");

                            mMap.addMarker(markerOptions);
                        }


                    } else {
                        Log.w("My Current address", "No Address!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.w("My Current", "Canont get Address!");
                    Toast.makeText(getApplicationContext(), "No se ha podido obtener ninguna localidad en ese punto, inténtelo en otro",
                            Toast.LENGTH_LONG).show();
                }


            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
              String titulo=marker.getTitle();
                AdminSQLiteOpenHelper ad=new AdminSQLiteOpenHelper(getApplicationContext());
                SQLiteDatabase db=ad.getReadableDatabase();

                String[] args = new String[] {titulo};
                Cursor c = db.rawQuery(" SELECT id FROM municipios WHERE nombre=? ", args);


                String id;
                String url;
                if (c.moveToFirst()) {
                    do {
                        id = c.getString(0);
                        Log.v("RESULTADO CONSULTA", "-");
                        Log.v("Resultado"," Id: " + id);
                    } while (c.moveToNext());
                    url="http://api.tiempo.com/index.php?api_lang=es&localidad="+id+"&affiliate_id=51ltbazhjl94&v=2&h=1";
                    DiaParser dia=new DiaParser(url);

                    try {
                        dias=(ArrayList<Dia>) dia.execute().get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }


                    Intent intent = new Intent().setClass(
                      MapsActivity.this, PrediccionActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("arraylist",dias);
                            bundle.putString("ciudad",titulo);
                            intent.putExtras(bundle);

                            startActivity(intent);



                }else{
                    Toast.makeText(getApplicationContext(),"No se puede obtener la previsión de esa localidad, pruebe con otra",
                            Toast.LENGTH_LONG).show();
                }

            }

        });

    }






    private void setUpMapIfNeeded() {
        if (mMap == null) {
            MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
    }







}
