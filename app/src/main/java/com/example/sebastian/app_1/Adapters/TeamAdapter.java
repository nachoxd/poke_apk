package com.example.sebastian.app_1.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sebastian.app_1.R;
import com.example.sebastian.app_1.Utils.Pokemon;

import java.util.List;

/**
 * Created by Sebastian on 22-05-2017.
 */

public class TeamAdapter extends ArrayAdapter<Pokemon> {
    Context context;
    int layoutresourceid;
    List<Pokemon> datos;
    public TeamAdapter(Context context, int layoutresourceid, List<Pokemon> data){
        super(context, layoutresourceid,data);
        this.context = context;
        this.layoutresourceid = layoutresourceid;
        this.datos = data;

    }
    public View getView(int position, View convertview, ViewGroup parent){
        View row = convertview;
        pklist_holder holder = null;
        if(holder==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutresourceid,parent,false);
            holder = new pklist_holder();
            holder.icon = (ImageView) row.findViewById(R.id.elem_icon);
            holder.name = (TextView) row.findViewById(R.id.elem_tv);
            holder.type1 = (ImageView) row.findViewById(R.id.type_int_1);
            holder.type2 = (ImageView) row.findViewById(R.id.type_int_2);
            holder.ability = (TextView) row.findViewById(R.id.ability_dinamic);
            row.setTag(holder);
        }
        else{
            holder = (pklist_holder)row.getTag();
        }

        Pokemon pokemon = datos.get(position);
        holder.icon.setImageBitmap(pokemon.image);
        holder.name.setText(pokemon.name);
        holder.type1.setImageResource(pokemon.type_int_1);
        holder.type2.setImageResource(pokemon.type_int_2);
        holder.ability.setText(pokemon.ability);
        return row;
    }

    static class pklist_holder{
        ImageView icon;
        TextView name;
        ImageView type1;
        ImageView type2;
        TextView ability;
    }
}
