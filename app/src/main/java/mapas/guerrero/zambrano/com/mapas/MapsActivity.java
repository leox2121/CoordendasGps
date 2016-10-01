package mapas.guerrero.zambrano.com.mapas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends Activity {

    GoogleMap googleMap;
    MapView mapView;
    int orientacion,vista;
    TextView latitud,longitud;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mapView =(MapView)findViewById(R.id.mi_mapa);
        mapView.onCreate(savedInstanceState);

        googleMap=mapView.getMap();
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);//mapa normal
        googleMap.setMyLocationEnabled(true);


        googleMap.addMarker(new MarkerOptions().position(new LatLng(0.0, 00))
                .title("Marcador").draggable(true));



        CargarConfiguracion();
        CargaText();

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

    public void CargaText(){

        SharedPreferences micordenada=getSharedPreferences("Coordenadas", Context.MODE_PRIVATE);
        String Slatitud = micordenada.getString("latitud","");
        String Slongitud = micordenada.getString("longitud", "");
        latitud=(TextView)findViewById(R.id.tlatitud);
        longitud=(TextView)findViewById(R.id.tlongitud);
        latitud.setText(Slatitud);
        longitud.setText(Slongitud);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuAbout:
                startActivity(new Intent(this, About.class));
                return true;
            case R.id.confi:
                startActivity(new Intent(this, Configuracion.class));
                return true;
            case R.id.corde:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.mostrar:
                startActivity(new Intent(this, MostrarActivity.class));
                return true;
            case R.id.eliminar:
                startActivity(new Intent(this, EliminarActivity.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
