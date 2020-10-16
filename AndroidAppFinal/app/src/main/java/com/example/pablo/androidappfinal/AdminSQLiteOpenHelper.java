package com.example.pablo.androidappfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class AdminSQLiteOpenHelper  extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="appfinal.db";
    public static final String TABLE_MUN="municipios";
    public ArrayList<String>urlss;
    public ArrayList<String>municipios;
    ContentValues registro;


    ArrayList<String> urlS=new ArrayList<String>();
    public AdminSQLiteOpenHelper(Context context) {
        super(context,DATABASE_NAME, null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        ProvinciasParser getXML = new ProvinciasParser();
        try {
            urlss=(ArrayList<String>)getXML.execute().get();
        UnMunicipio getData=new UnMunicipio(urlss);
            municipios=(ArrayList<String>)getData.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        db.execSQL("create table municipios(nombre varchar,id varchar)");

        String nombre="";
        String id="";
        String[]datos;
        for(int i=0;i<municipios.size();i++) {
            datos=municipios.get(i).split("&");
            
            nombre=datos[0];
            id=datos[1];

                registro=new ContentValues();
                registro.put("nombre", nombre);
                registro.put("id", id);
                db.insert(TABLE_MUN, null, registro);
                Log.d("INSERTADO !",nombre+" "+id);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
