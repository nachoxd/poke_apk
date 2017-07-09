package com.example.sebastian.app_1.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sebastian.app_1.R;
import com.example.sebastian.app_1.Utils.Attack;
import com.example.sebastian.app_1.Utils.DBHelper;
import com.example.sebastian.app_1.Utils.Pokemon;

import java.util.ArrayList;

/**
 * Created by Sebastian on 25-05-2017.
 */

public class SinglePokemonActivity extends AppCompatActivity implements View.OnClickListener{
    //ELEMENTS OF ACTIVITY
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
    //BUTTONS & IDS
    Button btn;
    Button swap1;
    Button delete1;
    Button swap2;
    Button delete2;
    Button swap3;
    Button delete3;
    Button swap4;
    Button delete4;
    int poke_id;
    int team_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_poke_main);
        //DECLARE ELEMENTS OF SINGLE_POKEMON_ACTIVITY
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
        //BUTTONS
        btn = (Button) findViewById(R.id.button_delete_poke);
        btn.setOnClickListener(this);
        swap1 = (Button) findViewById(R.id.swap1);
        swap1.setOnClickListener(this);
        delete1 = (Button) findViewById(R.id.delete1);
        delete1.setOnClickListener(this);
        swap2 = (Button) findViewById(R.id.swap2);
        swap2.setOnClickListener(this);
        delete2 = (Button) findViewById(R.id.delete2);
        delete2.setOnClickListener(this);
        swap3 = (Button) findViewById(R.id.swap3);
        swap3.setOnClickListener(this);
        delete3 = (Button) findViewById(R.id.delete3);
        delete3.setOnClickListener(this);
        swap4 = (Button) findViewById(R.id.swap4);
        swap4.setOnClickListener(this);
        delete4 = (Button) findViewById(R.id.delete4);
        delete4.setOnClickListener(this);
        //FILL SPACES WITH INTENT INFO
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras!=null){

            poke_id = extras.getInt("POKE_ID");
            team_id = extras.getInt("TEAM_ID");

            DBHelper db = new DBHelper(this);
            Pokemon pokemon = db.getPokemon(poke_id);
            ArrayList<Attack> attacks = db.getAttacks(poke_id);

            //ELEMENTS OF SINGLE POKEMON VIEW

            icon.setImageBitmap(pokemon.image);
            name.setText(pokemon.name);
            type1.setImageResource(pokemon.type_int_1);
            type2.setImageResource(pokemon.type_int_2);
            ability.setText(pokemon.ability);
            move1.setText(attacks.get(0).name);
            move1_type.setImageResource(attacks.get(0).type_int);
            move2.setText(attacks.get(1).name);
            move2_type.setImageResource(attacks.get(1).type_int);
            move3.setText(attacks.get(2).name);
            move3_type.setImageResource(attacks.get(2).type_int);
            move4.setText(attacks.get(3).name);
            move4_type.setImageResource(attacks.get(3).type_int);



        }

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button_delete_poke){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Delete pokemon");
            alert.setMessage("");
            //POSITIVE ANSWER
            alert.setPositiveButton("Delete", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int whichButton) {
                    DBHelper db = new DBHelper(SinglePokemonActivity.this);
                    db.deletePokemon(poke_id);
                    Log.d("POKEMON","DELETED");
                    Intent intent = new Intent(SinglePokemonActivity.this,TeamActivity.class);
                    intent.putExtra("TEAM_ID",team_id);
                    startActivity(intent);
                    finish();

                }
            });
            //NEGATIVE ANSWER
            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    //CANCELLED, DO NOTHING
                }
            });

            alert.show();
        }
        else if(v.getId()==R.id.swap1){
            Toast.makeText(getApplicationContext(),"SWAP 1 pressed, not implemented yet uwu",Toast.LENGTH_SHORT).show();
        }
        else if(v.getId()==R.id.delete1){
            Toast.makeText(getApplicationContext(),"DELETE 1 pressed, not implemented yet uwu",Toast.LENGTH_SHORT).show();
        }
        else if(v.getId()==R.id.swap2){
            Toast.makeText(getApplicationContext(),"SWAP 2 pressed, not implemented yet uwu",Toast.LENGTH_SHORT).show();
        }
        else if(v.getId()==R.id.delete2){
            Toast.makeText(getApplicationContext(),"DELETE 2 pressed, not implemented yet uwu",Toast.LENGTH_SHORT).show();
        }
        else if(v.getId()==R.id.swap3){
            Toast.makeText(getApplicationContext(),"SWAP 3 pressed, not implemented yet uwu",Toast.LENGTH_SHORT).show();
        }
        else if(v.getId()==R.id.delete3){
            Toast.makeText(getApplicationContext(),"DELETE 3 pressed, not implemented yet uwu",Toast.LENGTH_SHORT).show();
        }
        else if(v.getId()==R.id.swap4){
            Toast.makeText(getApplicationContext(),"SWAP 4 pressed, not implemented yet uwu",Toast.LENGTH_SHORT).show();
        }
        else if(v.getId()==R.id.delete4){
            Toast.makeText(getApplicationContext(),"DELETE 4 pressed, not implemented yet uwu",Toast.LENGTH_SHORT).show();
        }

    }
}
