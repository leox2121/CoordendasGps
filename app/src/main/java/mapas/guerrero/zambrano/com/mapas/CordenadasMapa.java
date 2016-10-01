package mapas.guerrero.zambrano.com.mapas;

import com.orm.SugarRecord;

/**
 * Created by Usuario on 12/01/2016.
 */
public class CordenadasMapa extends SugarRecord {

    public String Fecha;
    public String Hora;
    public String Longitud;
    public String Latitud;

    public CordenadasMapa() {
    }

    public CordenadasMapa(String fecha, String hora,String longitud, String latitud) {
        Fecha=fecha;
        Hora=hora;
        Latitud=latitud;
        Longitud=longitud;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }


    public String getLongitud() {
        return Longitud;
    }

    public void setLongitud(String longitud) {
        Longitud = longitud;
    }

    public String getLatitud() {
        return Latitud;
    }

    public void setLatitud(String latitud) {
        Latitud = latitud;
    }



    @Override
    public String toString() {
        return "Coordenadas:" +"\n"+
                "FECHA : "+Fecha+"\n"+
                "HORA : "+Hora+"\n"+
                "LONGITUD : " + Longitud + "\n" +
                "LATITUD : " + Latitud;
    }



}
