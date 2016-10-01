package mapas.guerrero.zambrano.com.mapas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class EliminarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eliminar);

    }

public void Eliminar(View view){

    List<CordenadasMapa> books = CordenadasMapa.listAll(CordenadasMapa.class);
    CordenadasMapa.deleteAll(CordenadasMapa.class);


    Toast.makeText(EliminarActivity.this, "Elementos Eliminados", Toast.LENGTH_SHORT).show();
}

}
