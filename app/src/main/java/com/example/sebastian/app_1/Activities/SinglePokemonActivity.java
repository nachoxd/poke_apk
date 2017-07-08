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

import com.example.sebastian.app_1.R;
import com.example.sebastian.app_1.Utils.DBHelper;
import com.example.sebastian.app_1.Utils.Pokemon;

/**
 * Created by Sebastian on 25-05-2017.
 */

public class SinglePokemonActivity extends AppCompatActivity implements View.OnClickListener{
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
    Button btn;
    int poke_id;
    int team_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_poke_main);

        //FILL SPACES WITH INTENT INFO
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras!=null){

            poke_id = extras.getInt("POKE_ID");
            team_id = extras.getInt("TEAM_ID");

            DBHelper db = new DBHelper(this);
            Pokemon pokemon = db.getPokemon(poke_id);

            //ELEMENTS OF SINGLE POKEMON VIEW
            icon = (ImageView) findViewById(R.id.elem_icon);
            icon.setImageBitmap(pokemon.image);
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
            btn = (Button) findViewById(R.id.button_delete_poke);
            btn.setOnClickListener(this);
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
    }
}
