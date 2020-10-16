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

public class ProvinciasParser extends AsyncTask {

    URL url;
    ArrayList<String> urlS = new ArrayList();

    @Override
    protected Object doInBackground(Object[] objects) {


        try {
            url = new URL("http://api.tiempo.com/index.php?api_lang=es&pais=18&affiliate_id=51ltbazhjl94");

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(getInputStream(url), "UTF_8");


            String url;
            String urlfinal;
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {

                    if (xpp.getName().equalsIgnoreCase("url")) {
                        url=xpp.nextText();
                        urlfinal=url.concat("&affiliate_id=51ltbazhjl94");
                        Log.d("URL -------> ", urlfinal);
                        urlS.add(urlfinal); //extract the headline

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

        return urlS;
    }


    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }



}