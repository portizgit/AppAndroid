package com.example.pablo.androidappfinal;

import java.io.Serializable;

/**
 * Created by Pablo on 15/02/2017.
 */
public class Hora implements Serializable{
    String hora,resumen,temperatura,viento,lluvia,sensacionterm,idviento;
    public Hora(String hora, String resumen, String temperatura, String viento, String lluvia, String sensacionterm,String idviento){
        this.hora=hora;
        this.resumen=resumen;
        this.temperatura=temperatura;
        this.viento=viento;
        this.lluvia=lluvia;
        this.sensacionterm=sensacionterm;
        this.idviento=idviento;
    }


    public String getIdviento() {
        return idviento;
    }

    public String getHora() {
        return hora;
    }

    public String getLluvia() {
        return lluvia;
    }

    public String getResumen() {
        return resumen;
    }

    public String getSensacionterm() {
        return sensacionterm;
    }

    public String getTemperatura() {
        return temperatura;
    }

}
