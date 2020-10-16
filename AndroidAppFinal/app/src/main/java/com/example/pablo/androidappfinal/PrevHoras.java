
package com.example.pablo.androidappfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import java.util.ArrayList;

public class PrevHoras extends AppCompatActivity {
    ArrayList<Hora> horas;
    String ciudad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        horas=(ArrayList<Hora>) bundle.getSerializable("arraylist");
        ciudad=bundle.getString("ciudad");
        setTitle(ciudad);
        setContentView(R.layout.activity_prevision_horas);

        HoraAdapter adapter = new HoraAdapter(getApplicationContext(), horas);


        ListView listView = (ListView) findViewById(R.id.listViewHoras);

        listView.setAdapter(adapter);



    }
}
