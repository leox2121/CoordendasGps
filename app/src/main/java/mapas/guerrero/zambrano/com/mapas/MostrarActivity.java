package mapas.guerrero.zambrano.com.mapas;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MostrarActivity extends AppCompatActivity {


    GoogleMap googleMap;
    MapView mapView;
    int orientacion,vista;

    @Override
    protected void onResume(){
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onPause(){
        super.onPause();
        mapView.onPause();
    }

    List<CordenadasMapa> coordenada;
    ArrayAdapter<CordenadasMapa> adapter;

    ListView lvCoordenada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consulta);

        coordenada=CordenadasMapa.listAll(CordenadasMapa.class);
        adapter=new ArrayAdapter<CordenadasMapa>(this, android.R.layout.simple_list_item_1, coordenada);

        lvCoordenada=(ListView)findViewById(R.id.lvCoordenada);
        lvCoordenada.setAdapter(adapter);

        mapView =(MapView)findViewById(R.id.puntosmapa);
        mapView.onCreate(savedInstanceState);

        googleMap=mapView.getMap();
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);//mapa normal
        googleMap.setMyLocationEnabled(true);


        //googleMap.addMarker(new MarkerOptions().position(new LatLng(80.7448496, 0.952631))
          //      .title("Aqui estuve").draggable(false));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(-80.7448496 , -0.952631))
                .title("Marcador").draggable(true));

        CargarConfiguracion();

        if (vista==0)
        {
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            googleMap.setMyLocationEnabled(true);
        }
        else
        {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            googleMap.setMyLocationEnabled(true);
        }
        if (orientacion==0)
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        }
        else
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }


    }
    public void CargarConfiguracion(){
        SharedPreferences miconfiguracion=getSharedPreferences("Datos Mapa", Context.MODE_PRIVATE);
        orientacion=miconfiguracion.getInt("Orientacion",0);
        vista=miconfiguracion.getInt("Vista",0);
    }

}
