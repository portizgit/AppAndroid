package com.example.pablo.androidappfinal;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Pablo on 14/02/2017.
 */
public class Dia implements Serializable{
    String resumen,tempMax,tempMin,viento,lluvia,aman,atard,nombre,idresumen,idviento;
    ArrayList<Hora>horas;

    public Dia(String resumen,String tempMax,String tempMin,String viento,String lluvia,String aman,String atard,String nombre,String idresumen,String idviento){
        this.resumen=resumen;
        this.tempMax=tempMax;
        this.tempMin=tempMin;
        this.viento=viento;
        this.lluvia=lluvia;
        this.aman=aman;
        this.atard=atard;
        this.nombre=nombre;
        this.idresumen=idresumen;
        this.idviento=idviento;

    }


    public void setHoras(ArrayList<Hora> horas) {
        this.horas = horas;
    }

    public ArrayList<Hora> getHoras() {
        return horas;
    }

    public String getIdresumen() {
        return idresumen;
    }

    public String getIdviento() {
        return idviento;
    }

    public String getNombre(){
        return nombre;
    }
    public String getResumen(){
        return resumen;
    }
    public String getTempMax(){
        return tempMax;
    }
    public String getTempMin(){
        return tempMin;
    }
    public String getViento(){
        return viento;
    }
    public String getLluvia(){
        return lluvia;
    }
    public String getAman(){
        return aman;
    }
    public String getAtard(){
        return atard;
    }


}
