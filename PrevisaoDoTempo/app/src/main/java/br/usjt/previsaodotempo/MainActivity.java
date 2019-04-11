package br.usjt.previsaodotempo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.joe.ciclo_de_vida_gps_e_mapas.R;

public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private LocationListener locationListener;
    private static final int REQUEST_CODE_GPS = 1001;
    private boolean listVazia = false;
    private TextView localizacaoTextView;
    private ListView coordenadasListView;
    private Location localizacaoAtual;

    private int i= 0;
    private int j= 0;

    List<String> chamados = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordenadasListView = findViewById(R.id.coordenadasListView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ArrayAdapter<String> adapter =new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                chamados);coordenadasListView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double lat = localizacaoAtual.getLatitude();
                double lon = localizacaoAtual.getLongitude();
                Uri uri = Uri.parse(String.format("geo:%f,%f?q=restaurantes", lat, lon));
                Intent intent = new Intent (Intent.ACTION_VIEW, uri);
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        });
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                localizacaoAtual = location;
                double lat = location.getLatitude();
                double lon = location.getLongitude();
                if(listVazia == false) {
                    chamados.add(i, "POS" + j +"Lat: " + lat + "Long: " + lon);
                    adapter.notifyDataSetChanged();
                    i  = i+1;
                    j = j+1;
                    if(i == 50){
                        listVazia = true;
                        i = 0;

                    }
                }
                if(listVazia == true){
                    chamados.set(i, "POS:"+j + "Lat: " + lat + "Long: " + lon);
                    adapter.notifyDataSetChanged();
                    j = j+1;
                    i = i+1;
                    if(i == 50){
                        i = 0;
                    }
                }
            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
    }
    @Override
    protected void onStart(){
        super.onStart();
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 120,200,locationListener);

        }else{
            ActivityCompat.requestPermissions( this,
                    new String []{Manifest.permission.ACCESS_FINE_LOCATION},
                    1001);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1001){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,120,200,locationListener);
                }
            }else{
                Toast.makeText(this, getString(R.string.no_gps_no_app),Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        locationManager.removeUpdates(locationListener);
    }
}