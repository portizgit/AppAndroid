package com.example.pablo.androidappfinal;


import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Pablo on 22/01/2017.
 */

public class DiaParser extends AsyncTask {

    URL url;
    String resumen,tempMax,tempMin,idviento,lluvia,aman,atard,nombre,idresumen;
    ArrayList<Hora>horas;
    ArrayList<Dia>dias=new ArrayList<>();
    Hora h;
    String hhora,hresumen,htemperatura,hviento,hlluvia,hsensacionterm,hidviento;
    Dia d;
    String surl;
    boolean dhoras=false;
    public DiaParser(String surl){
       this.surl=surl;
    }

    @Override
    protected Object doInBackground(Object[] objects) {


        try {
            url = new URL(surl);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(getInputStream(url), "UTF_8");

            boolean insideItem = false;

            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {

                if (eventType == XmlPullParser.START_TAG) {
                if(dhoras==false) {
                    if (xpp.getName().equalsIgnoreCase("day")) {
                        nombre = xpp.getAttributeValue(null, "name");
                        Log.v("nombre", nombre);
                    }
                    if (xpp.getName().equalsIgnoreCase("symbol")) {
                        idresumen=xpp.getAttributeValue(null,"value");
                        resumen = xpp.getAttributeValue(null, "desc");
                        Log.v("resumen", resumen);
                    }
                    if (xpp.getName().equalsIgnoreCase("tempmin")) {
                        tempMin = xpp.getAttributeValue(null, "value");
                        Log.v("tempmin", tempMin);
                    }
                    if (xpp.getName().equalsIgnoreCase("tempmax")) {
                        tempMax = xpp.getAttributeValue(null, "value");
                        Log.v("tempmax", tempMax);
                    }
                    if (xpp.getName().equalsIgnoreCase("wind")) {
                        idviento = xpp.getAttributeValue(null, "symbol");
                        Log.v("viento", idviento);
                    }
                    if (xpp.getName().equalsIgnoreCase("rain")) {
                        lluvia = xpp.getAttributeValue(null, "value");
                        Log.v("lluvia", lluvia);
                    }
                    if (xpp.getName().equalsIgnoreCase("sun")) {
                        aman = xpp.getAttributeValue(null, "in");
                        Log.v("amanecer", aman);
                        atard = xpp.getAttributeValue(null, "out");
                        Log.v("atardecer", atard);
                    }
                    if (xpp.getName().equalsIgnoreCase("hour")) {
                        dhoras = true;
                        horas=new ArrayList<Hora>();
                        d = new Dia(resumen, tempMax, tempMin, "", lluvia, aman, atard, nombre,idresumen,idviento);
                        hhora = xpp.getAttributeValue(null, "value");

                    }
                }else{

                    if (xpp.getName().equalsIgnoreCase("hour")) {
                        hhora = xpp.getAttributeValue(null, "value");

                    }
                    if (xpp.getName().equalsIgnoreCase("symbol")) {
                        hresumen = xpp.getAttributeValue(null, "desc");
                        Log.v("hresumen", hresumen);
                    }
                    if (xpp.getName().equalsIgnoreCase("temp")) {
                        htemperatura = xpp.getAttributeValue(null, "value");
                        Log.v("htemp", htemperatura);
                    }
                    if (xpp.getName().equalsIgnoreCase("wind")) {
                        hviento = xpp.getAttributeValue(null, "value");
                        hidviento = xpp.getAttributeValue(null, "symbol");
                        Log.v("hviento", hviento);
                    }
                    if (xpp.getName().equalsIgnoreCase("rain")) {
                        hlluvia = xpp.getAttributeValue(null, "value");
                        Log.v("hlluvia", hlluvia);
                    }
                    if (xpp.getName().equalsIgnoreCase("windchill")) {
                        hsensacionterm = xpp.getAttributeValue(null, "value");
                        Log.v("hsensacionterm", hsensacionterm);

                    }
                }


                }
                if (eventType == XmlPullParser.END_TAG) {
                    if (xpp.getName().equalsIgnoreCase("hour")) {
                        h=new Hora(hhora,hresumen,htemperatura,hviento,hlluvia,hsensacionterm,hidviento);
                        horas.add(h);


                    }
                }
                if (eventType == XmlPullParser.END_TAG) {
                    if (xpp.getName().equalsIgnoreCase("day")) {

                        d.setHoras(horas);
                        dias.add(d);
                        dhoras=false;
                    }
                }




                eventType = xpp.next(); //move to next element
            }




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dias;
    }


    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }



    public Dia getDia()
    {
        return d;
    }


}