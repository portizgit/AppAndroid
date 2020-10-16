package com.example.pablo.androidappfinal;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PrediccionActivity extends AppCompatActivity {
    Dia d1;
    Dia d2;
    Dia d3;
    ArrayList<Dia>dias;
    ArrayList<Hora>horas;
    String dia;
    TextView txDia;
    TextView txResumen;
    TextView txTempMax;
    TextView txTempMin;
    TextView txLluvia;
    TextView txAman;
    TextView txAtar;
    ImageView imgResumen;
    ImageView imgViento;
    String ciudad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        dias=(ArrayList<Dia>) bundle.getSerializable("arraylist");
        ciudad=bundle.getString("ciudad");
        setTitle(ciudad);
        setContentView(R.layout.prediccion);

        imgResumen=(ImageView)findViewById(R.id.imgResumen);
        txDia = (TextView)findViewById(R.id.txDia);
        txResumen = (TextView)findViewById(R.id.txResumen);
        txTempMax = (TextView)findViewById(R.id.txtempMax);
        txTempMin = (TextView)findViewById(R.id.txtempMin);
        txLluvia = (TextView)findViewById(R.id.txLluvia);
        txAman = (TextView)findViewById(R.id.txAman);
        txAtar = (TextView)findViewById(R.id.txAtard);
        imgViento=(ImageView)findViewById(R.id.imgViento) ;

        d1=dias.get(0);
        d2=dias.get(1);
        d3=dias.get(2);
        dia="1";

        String uri = "@drawable/r"+d1.getIdresumen();


        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        Drawable res = getResources().getDrawable(imageResource);
        imgResumen.setImageDrawable(res);
        txDia.setText(d1.getNombre());
        txResumen.setText(d1.getResumen());
        uri = "@drawable/v"+d1.getIdviento();
        Log.v("------URI---------",uri);
        imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res1 = getResources().getDrawable(imageResource);
        imgViento.setImageDrawable(res1);
        txTempMax.setText(d1.getTempMax()+" º");
        txTempMin.setText(d1.getTempMin()+" º");
        txLluvia.setText(d1.getLluvia()+" mm");
        txAman.setText(d1.getAman());
        txAtar.setText(d1.getAtard());

        Button bdia1=(Button)findViewById(R.id.bdia1);
        bdia1.setText(d1.getNombre());
        Button bdia2=(Button)findViewById(R.id.bdia2);
        bdia2.setText(d2.getNombre());
        Button bdia3=(Button)findViewById(R.id.bdia3);
        bdia3.setText(d3.getNombre());


        bdia1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dia="1";
                String uri = "@drawable/r"+d1.getIdresumen();


                int imageResource = getResources().getIdentifier(uri, null, getPackageName());

                Drawable res = getResources().getDrawable(imageResource);
                imgResumen.setImageDrawable(res);
                txDia.setText(d1.getNombre());
                txResumen.setText(d1.getResumen());
                uri = "@drawable/v"+d1.getIdviento();
                imageResource = getResources().getIdentifier(uri, null, getPackageName());
                Drawable res1 = getResources().getDrawable(imageResource);
                imgViento.setImageDrawable(res1);
                txTempMax.setText(d1.getTempMax()+" º");
                txTempMin.setText(d1.getTempMin()+" º");
                txLluvia.setText(d1.getLluvia()+" mm");
                txAman.setText(d1.getAman());
                txAtar.setText(d1.getAtard());
            }
        });
        bdia2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dia="2";
                String uri = "@drawable/r"+d2.getIdresumen();


                int imageResource = getResources().getIdentifier(uri, null, getPackageName());

                Drawable res = getResources().getDrawable(imageResource);
                imgResumen.setImageDrawable(res);

                txDia.setText(d2.getNombre());
                txResumen.setText(d2.getResumen());
                uri = "@drawable/v"+d2.getIdviento();
                imageResource = getResources().getIdentifier(uri, null, getPackageName());
                Drawable res1 = getResources().getDrawable(imageResource);
                imgViento.setImageDrawable(res1);
                txTempMax.setText(d2.getTempMax()+" º");
                txTempMin.setText(d2.getTempMin()+" º");
                txLluvia.setText(d2.getLluvia()+" mm");
                txAman.setText(d2.getAman());
                txAtar.setText(d2.getAtard());
            }
        });
        bdia3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dia="3";
                String uri = "@drawable/r"+d3.getIdresumen();


                int imageResource = getResources().getIdentifier(uri, null, getPackageName());

                Drawable res = getResources().getDrawable(imageResource);
                imgResumen.setImageDrawable(res);
                txDia.setText(d3.getNombre());
                uri = "@drawable/v"+d3.getIdviento();
                imageResource = getResources().getIdentifier(uri, null, getPackageName());
                Drawable res1 = getResources().getDrawable(imageResource);
                imgViento.setImageDrawable(res1);
                txResumen.setText(d3.getResumen());
                txTempMax.setText(d3.getTempMax()+" º");
                txTempMin.setText(d3.getTempMin()+" º");
                txLluvia.setText(d3.getLluvia()+" mm");
                txAman.setText(d3.getAman());
                txAtar.setText(d3.getAtard());
            }
        });





        Button boton=(Button)findViewById(R.id.button);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(dia.equals("1")){
                    horas=d1.getHoras();

                }
                if(dia.equals("2")){
                    horas=d2.getHoras();

                }
                if(dia.equals("3")){
                    horas=d3.getHoras();

                }
                Intent intent = new Intent().setClass(
                        getBaseContext(),PrevHoras.class);
                Bundle bd = new Bundle();
                bd.putSerializable("arraylist",horas);
                bd.putString("ciudad",ciudad);

                intent.putExtras(bd);

                startActivity(intent);




            }
        });
    }
}
