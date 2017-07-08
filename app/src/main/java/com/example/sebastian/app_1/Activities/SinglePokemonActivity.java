package com.example.sebastian.app_1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sebastian.app_1.R;
import com.example.sebastian.app_1.Utils.DBHelper;

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

            int poke_id = extras.getInt("POKE_ID");
            Log.d("POKE_ID ES",""+poke_id);
            DBHelper db = new DBHelper(this);
            db.deletePokemon(poke_id);
            Log.d("POKEMON","DELETED");
        }

    }
}
