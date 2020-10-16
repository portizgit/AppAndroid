package com.example.pablo.androidappfinal;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Pablo on 16/02/2017.
 */
public class HoraAdapter extends ArrayAdapter<Hora> {
    public HoraAdapter(Context context, ArrayList<Hora> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Hora hora = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_hora, parent, false);
        }
        // Lookup view for data population
        TextView txHora = (TextView)convertView.findViewById(R.id.txHora);
        txHora.setText(hora.getHora());
        TextView txhResumen = (TextView)convertView.findViewById(R.id.txhResumen);
        txhResumen.setText(hora.getResumen());
        TextView txhTemp = (TextView)convertView.findViewById(R.id.txhTemp);
        txhTemp.setText(hora.getTemperatura()+" º");
        TextView txhSen = (TextView)convertView.findViewById(R.id.txhSensacion);
        txhSen.setText(hora.getSensacionterm()+" º");

        TextView txhLluvia = (TextView)convertView.findViewById(R.id.txhLluvia);
        txhLluvia.setText(hora.getLluvia()+" mm");
        String uri = "@drawable/v"+hora.getIdviento();
        ImageView imgViento=(ImageView)convertView.findViewById(R.id.imgViento);

        int imageResource = getContext().getResources().getIdentifier(uri, null, getContext().getPackageName());

        Drawable res = getContext().getResources().getDrawable(imageResource);
        imgViento.setImageDrawable(res);




        return convertView;
    }
}