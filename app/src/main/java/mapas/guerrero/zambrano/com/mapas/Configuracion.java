package mapas.guerrero.zambrano.com.mapas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class Configuracion extends MapsActivity {

    RadioButton rbhorizontal,rbvertical;
    RadioButton rbnormal,rbsatelite;
    int orientacion,vista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);


        findViewById(R.id.btnGuardar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comparar();
                GuardarConfiguracion();
                startActivity(new Intent(Configuracion.this, MapsActivity.class));
            }
        });

    }
    public void Comparar(){
        rbhorizontal=(RadioButton) findViewById(R.id.rbhorizontal);
        rbvertical=(RadioButton) findViewById(R.id.rbvertical);
        if (rbvertical.isChecked()==true){
            orientacion=0;
        }
        else{
            orientacion=1;
        }
        rbnormal=(RadioButton) findViewById(R.id.rbnormal);
        rbsatelite=(RadioButton) findViewById(R.id.rbsatelite);
        if (rbnormal.isChecked()==true){
            vista=0;
        }
        else{
            vista=1;
        }


    }

    public void GuardarConfiguracion(){
        SharedPreferences miconfiguracion=getSharedPreferences("Datos Mapa", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=miconfiguracion.edit();
        editor.putInt("Orientacion",orientacion);
        editor.putInt("Vista",vista);
        editor.commit();
    }



}
