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



public class UnMunicipio extends AsyncTask {

    URL url;
    ArrayList<String> urlS = new ArrayList();
    ArrayList<String> municipios = new ArrayList<>();
    public UnMunicipio(ArrayList<String>url){
        urlS=url;
    }
    @Override
    protected Object doInBackground(Object[] objects) {

        for(int i=0;i<urlS.size();i++) {
            try {
                String nombre = "";
                String id = "";

                url = new URL(urlS.get(i));
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();


                xpp.setInput(getInputStream(url), "UTF_8");

                int eventType = xpp.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {

                    if (eventType == XmlPullParser.START_TAG) {
                        if (xpp.getName().equalsIgnoreCase("name")) {
                            id = xpp.getAttributeValue(null, "id");
                            nombre = xpp.nextText();
                            Log.d("NOMBRE--> ", nombre);

                            Log.d("ID--> ", id);
                        }


                    }

                    if (eventType == XmlPullParser.END_TAG) {
                        if (xpp.getName().equalsIgnoreCase("url")) {
                            municipios.add(nombre+"&"+id);
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
        }

        return municipios;
    }


    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }




}