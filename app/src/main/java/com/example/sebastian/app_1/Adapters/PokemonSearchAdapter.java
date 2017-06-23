package com.example.sebastian.app_1.Adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sebastian.app_1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 5/27/17.
 */

public class PokemonSearchAdapter extends ArrayAdapter<String> {

    private final Activity context;

    private List<String> itemname;

    private List<Bitmap> icon;

    private List<String> tipo1, tipo2, ab1,ab2,ab3;


    public PokemonSearchAdapter(Activity context, List<String> itemname) {
        super(context, R.layout.pokemon_search_list, itemname);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.itemname = itemname;

        icon = new ArrayList<Bitmap>();
        //type_int_1 = new ArrayList<ImageView>();
        //type_int_2 = new ArrayList<ImageView>();
        tipo1 = new ArrayList<String>();
        tipo2 = new ArrayList<String>();
        ab1 = new ArrayList<String>();
        ab2 = new ArrayList<String>();
        ab3 = new ArrayList<String>();
        
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.pokemon_search_list, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.search_poke_name);
        ImageView icon = (ImageView) rowView.findViewById(R.id.search_icon);
        ImageView type1 = (ImageView) rowView.findViewById(R.id.search_poke_type1);
        ImageView type2 = (ImageView) rowView.findViewById(R.id.search_poke_type2);
        //TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(itemname.get(position));


        //esto es el problema >:V
        //Drawable d = new BitmapDrawable(context.getResources(),this.icon.get(position));
        icon.setImageBitmap(this.icon.get(position));
        //:V


        //Placeholders de tipos temporales para search
        if (tipo1.get(position).equals("Normal")){
            type1.setImageResource(R.drawable.normal);
        }
        else if (tipo1.get(position).equals("Flying")){
            type1.setImageResource(R.drawable.flying);
        }
        else if (tipo1.get(position).equals("Grass")){
            type1.setImageResource(R.drawable.grass);
        }
        else if (tipo1.get(position).equals("Fire")){
            type1.setImageResource(R.drawable.fire);
        }
        else if (tipo1.get(position).equals("Water")){
            type1.setImageResource(R.drawable.water);
        }
        else if (tipo1.get(position).equals("Electric")){
            type1.setImageResource(R.drawable.electric);
        }
        else if (tipo1.get(position).equals("Ground")){
            type1.setImageResource(R.drawable.ground);
        }
        else if (tipo1.get(position).equals("Rock")){
            type1.setImageResource(R.drawable.rock);
        }
        else if (tipo1.get(position).equals("Bug")){
            type1.setImageResource(R.drawable.bug);
        }
        else if (tipo1.get(position).equals("Steel")){
            type1.setImageResource(R.drawable.steel);
        }
        else if (tipo1.get(position).equals("Dark")){
            type1.setImageResource(R.drawable.dark);
        }
        else if (tipo1.get(position).equals("Ghost")){
            type1.setImageResource(R.drawable.ghost);
        }
        else if (tipo1.get(position).equals("Psychic")){
            type1.setImageResource(R.drawable.psychic);
        }
        else if (tipo1.get(position).equals("Fairy")){
            type1.setImageResource(R.drawable.fairy);
        }
        else if (tipo1.get(position).equals("Dragon")){
            type1.setImageResource(R.drawable.dragon);
        }
        else if (tipo1.get(position).equals("Ice")){
            type1.setImageResource(R.drawable.ice);
        }
        else if (tipo1.get(position).equals("Fighting")){
            type1.setImageResource(R.drawable.fighting);
        }
        else if (tipo1.get(position).equals("Poison")){
            type1.setImageResource(R.drawable.poison);
        }
        else if (tipo1.get(position).equals("nulo")){
            type1.setImageResource(R.drawable.nulo);
        }
        // placeholders para type_int_2
        if (tipo2.get(position).equals("Normal")){
            type2.setImageResource(R.drawable.normal);
        }
        else if (tipo2.get(position).equals("Flying")){
            type2.setImageResource(R.drawable.flying);
        }
        else if (tipo2.get(position).equals("Grass")){
            type2.setImageResource(R.drawable.grass);
        }
        else if (tipo2.get(position).equals("Fire")){
            type2.setImageResource(R.drawable.fire);
        }
        else if (tipo2.get(position).equals("Water")){
            type2.setImageResource(R.drawable.water);
        }
        else if (tipo2.get(position).equals("Electric")){
            type2.setImageResource(R.drawable.electric);
        }
        else if (tipo2.get(position).equals("Ground")){
            type2.setImageResource(R.drawable.ground);
        }
        else if (tipo2.get(position).equals("Rock")){
            type2.setImageResource(R.drawable.rock);
        }
        else if (tipo2.get(position).equals("Bug")){
            type2.setImageResource(R.drawable.bug);
        }
        else if (tipo2.get(position).equals("Steel")){
            type2.setImageResource(R.drawable.steel);
        }
        else if (tipo2.get(position).equals("Dark")){
            type2.setImageResource(R.drawable.dark);
        }
        else if (tipo2.get(position).equals("Ghost")){
            type2.setImageResource(R.drawable.ghost);
        }
        else if (tipo2.get(position).equals("Psychic")){
            type2.setImageResource(R.drawable.psychic);
        }
        else if (tipo2.get(position).equals("Fairy")){
            type2.setImageResource(R.drawable.fairy);
        }
        else if (tipo2.get(position).equals("Dragon")){
            type2.setImageResource(R.drawable.dragon);
        }
        else if (tipo2.get(position).equals("Ice")){
            type2.setImageResource(R.drawable.ice);
        }
        else if (tipo2.get(position).equals("Fighting")){
            type2.setImageResource(R.drawable.fighting);
        }
        else if (tipo2.get(position).equals("Poison")){
            type2.setImageResource(R.drawable.poison);
        }
        else if (tipo2.get(position).equals("nulo")){
            type2.setImageResource(R.drawable.nulo);
        }
        //end of placeholders
        //extratxt.setText(tipo1.get(position) + " - " + tipo2.get(position));
        return rowView;

    }

    public void addPokemon(String name, Bitmap image, String tipo1, String tipo2,String a1,String a2, String a3) {

        itemname.add(0, name);
        icon.add(0, image);
        this.tipo1.add(0, tipo1);
        this.tipo2.add(0, tipo2);
        this.ab1.add(0,a1);
        this.ab2.add(0,a2);
        this.ab3.add(0,a3);

    }

    public String getAbility(int position,int order){
        if(order == 1){
            return ab1.get(position);
        }
        else if(order == 2){
            return ab2.get(position);
        }
        else if(order == 3){
            return ab3.get(position);
        }
        return "";
    }
}