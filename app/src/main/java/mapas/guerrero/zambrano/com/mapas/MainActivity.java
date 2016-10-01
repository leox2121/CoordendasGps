package mapas.guerrero.zambrano.com.mapas;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.Calendar;


public class MainActivity extends MapsActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    EditText edtLatitud;
    EditText edtLongitud;
    EditText edfecha;
    EditText edhora;
    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    String fechactual,horactual;

    private final String TAG = "TestApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        edtLatitud = (EditText) findViewById(R.id.edtLatitud);
        edtLongitud = (EditText) findViewById(R.id.edtLongitud);
        edfecha=(EditText)findViewById(R.id.edfecha);
        edhora=(EditText)findViewById(R.id.edhora);

        final Calendar c = Calendar.getInstance();
        int anio = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        mes=mes+1;
        String m="";
        int dia = c.get(Calendar.DAY_OF_MONTH);
        if (mes<10){
            m="0";
        }


        fechactual= dia+"/"+m+mes+"/"+anio;


        int hora = c.get(Calendar.HOUR_OF_DAY);
        int minuto = c.get(Calendar.MINUTE);
        int segundos=c.get(Calendar.SECOND);
        String minu="";

        if (minuto<10){
            minu="0";
        }

        horactual= hora+":"+minu+minuto+":"+segundos;
    }

    @Override
    public void onLocationChanged(Location location) {
        edtLatitud.setText("" + location.getLatitude());
        edtLongitud.setText("" + location.getLongitude());
        edfecha.setText(""+fechactual);
        edhora.setText(""+horactual);

        CordenadasMapa cordenada =new CordenadasMapa(edfecha.getText().toString(),edhora.getText().toString(),edtLatitud.getText().toString(),edtLongitud.getText().toString());
        cordenada.save();
        Toast.makeText(MainActivity.this, "Datos Guardados OK", Toast.LENGTH_SHORT).show();

        SharedPreferences micordenada=getSharedPreferences("Coordenadas", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=micordenada.edit();
        editor.putString("Latitud",edtLongitud.getText().toString());
        editor.putString("Longtud", edtLatitud.getText().toString());
        editor.commit();
    }

    public void Guardar(View view){

        CordenadasMapa cordenada =new CordenadasMapa(edfecha.getText().toString(),edhora.getText().toString(),edtLatitud.getText().toString(),edtLongitud.getText().toString());
        cordenada.save();
        Toast.makeText(MainActivity.this, "Datos Guardados OK", Toast.LENGTH_SHORT).show();

        edtLatitud.getText().clear();
        edtLongitud.getText().clear();

    }

    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        mLocationRequest.setInterval(180000); // Actualiza ubicacion cada segundo

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, MainActivity.this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "La conexion de GPS se ha suspendido");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "La conexion de GPS fallo");
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();

    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }
}