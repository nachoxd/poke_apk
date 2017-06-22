package com.example.sebastian.app_1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sebastian.app_1.R;

/**
 * Created by Sebastian on 25-05-2017.
 */

public class SinglePokemonActivity extends AppCompatActivity{
    ImageView icon;
    TextView name;
    ImageView type1;
    ImageView type2;
    TextView ability;
    TextView move1;
    ImageView move1_type;
    TextView move2;
    ImageView move2_type;
    TextView move3;
    ImageView move3_type;
    TextView move4;
    ImageView move4_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_poke_main);
        //ELEMENTS OF SINGLE POKEMON VIEW
        icon = (ImageView) findViewById(R.id.elem_icon);
        name = (TextView) findViewById(R.id.elem_tv);
        type1 = (ImageView) findViewById(R.id.type_int_1);
        type2 = (ImageView) findViewById(R.id.type_int_2);
        ability = (TextView) findViewById(R.id.ability_dinamic);
        move1 = (TextView) findViewById(R.id.move1);
        move1_type = (ImageView) findViewById(R.id.move1_type);
        move2 = (TextView) findViewById(R.id.move2);
        move2_type = (ImageView) findViewById(R.id.move2_type);
        move3 = (TextView) findViewById(R.id.move3);
        move3_type = (ImageView) findViewById(R.id.move3_type);
        move4 = (TextView) findViewById(R.id.move4);
        move4_type = (ImageView) findViewById(R.id.move4_type);
        //FILL SPACES WITH INTENT INFO
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras!=null){
            String dato = extras.getString("NAME");
            if(dato.equals("Gengar")){
                icon.setImageResource(R.drawable.gengar);
                name.setText("Gengar");
                type1.setImageResource(R.drawable.ghost);
                type2.setImageResource(R.drawable.poison);
                ability.setText("Cursed Body");
                move1.setText("Shadow Ball");
                move1_type.setImageResource(R.drawable.ghost);
                move2.setText("Sludge Bomb");
                move2_type.setImageResource(R.drawable.poison);
                move3.setText("Disable");
                move3_type.setImageResource(R.drawable.normal);
                move4.setText("Destiny Bond");
                move4_type.setImageResource(R.drawable.ghost);
            }
            else if(dato.equals("Sceptile")){
                icon.setImageResource(R.drawable.sceptile);
                name.setText("Sceptile");
                type1.setImageResource(R.drawable.grass);
                type2.setImageResource(R.drawable.nulo);
                ability.setText("Unburden");
                move1.setText("Giga Drain");
                move1_type.setImageResource(R.drawable.grass);
                move2.setText("Dragon Pulse");
                move2_type.setImageResource(R.drawable.dragon);
                move3.setText("Hidden Power");
                move3_type.setImageResource(R.drawable.normal);
                move4.setText("Protect");
                move4_type.setImageResource(R.drawable.normal);
            }
            if(dato.equals("Togekiss")){
                icon.setImageResource(R.drawable.togekiss);
                name.setText("Togekiss");
                type1.setImageResource(R.drawable.fairy);
                type2.setImageResource(R.drawable.flying);
                ability.setText("Serene Grace");
                move1.setText("Air Slash");
                move1_type.setImageResource(R.drawable.flying);
                move2.setText("Dazzling Gleam");
                move2_type.setImageResource(R.drawable.fairy);
                move3.setText("Tailwind");
                move3_type.setImageResource(R.drawable.flying);
                move4.setText("Roost");
                move4_type.setImageResource(R.drawable.flying);
            }
            if(dato.equals("Gliscor")){
                icon.setImageResource(R.drawable.gliscor);
                name.setText("Gliscor");
                type1.setImageResource(R.drawable.ground);
                type2.setImageResource(R.drawable.flying);
                ability.setText("Posion Heal");
                move1.setText("Acrobatics");
                move1_type.setImageResource(R.drawable.flying);
                move2.setText("Earthquake");
                move2_type.setImageResource(R.drawable.ground);
                move3.setText("Knock Off");
                move3_type.setImageResource(R.drawable.dark);
                move4.setText("Swords Dance");
                move4_type.setImageResource(R.drawable.normal);
            }
            if(dato.equals("Magnezone")){
                icon.setImageResource(R.drawable.magnezone);
                name.setText("Magnezone");
                type1.setImageResource(R.drawable.electric);
                type2.setImageResource(R.drawable.steel);
                ability.setText("Magnet Pull");
                move1.setText("Discharge");
                move1_type.setImageResource(R.drawable.electric);
                move2.setText("Flash Cannon");
                move2_type.setImageResource(R.drawable.steel);
                move3.setText("Signal Beam");
                move3_type.setImageResource(R.drawable.bug);
                move4.setText("Electric Terrain");
                move4_type.setImageResource(R.drawable.electric);
            }
            if(dato.equals("Froslass")){
                icon.setImageResource(R.drawable.froslass);
                name.setText("Froslass");
                type1.setImageResource(R.drawable.ice);
                type2.setImageResource(R.drawable.ghost);
                ability.setText("Cursed Body");
                move1.setText("Blizzard");
                move1_type.setImageResource(R.drawable.ice);
                move2.setText("Hex");
                move2_type.setImageResource(R.drawable.ghost);
                move3.setText("Thunderbolt");
                move3_type.setImageResource(R.drawable.electric);
                move4.setText("Psychic");
                move4_type.setImageResource(R.drawable.psychic);
            }
            else{
                icon.setImageResource(R.drawable.ditto);
                name.setText("Ditto");
                type1.setImageResource(R.drawable.normal);
                type2.setImageResource(R.drawable.nulo);
                ability.setText("Imposter");
                move1.setText("Transform");
                move1_type.setImageResource(R.drawable.normal);
                move2.setText("");
                move2_type.setImageResource(R.drawable.nulo);
                move3.setText("");
                move3_type.setImageResource(R.drawable.nulo);
                move4.setText("");
                move4_type.setImageResource(R.drawable.nulo);
            }
        }

    }
}
