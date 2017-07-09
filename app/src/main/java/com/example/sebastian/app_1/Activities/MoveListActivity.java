package com.example.sebastian.app_1.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sebastian.app_1.Adapters.MoveListAdapter;
import com.example.sebastian.app_1.R;
import com.example.sebastian.app_1.Utils.Attack;
import com.example.sebastian.app_1.Utils.DBHelper;

import java.util.ArrayList;

/**
 * Created by Sebastian on 22-06-2017.
 */

public class MoveListActivity extends AppCompatActivity {

    int poke_id;
    int atk_position;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        //INIT
        super.onCreate(savedInstanceState);
        this.context = MoveListActivity.this;
        setContentView(R.layout.move_list);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        DBHelper db = new DBHelper(this);
        ArrayList<Attack> attacks = new ArrayList<Attack>();
        //INTENT
        Intent intent = getIntent();
        Bundle datos = intent.getExtras();
        if(datos != null){
            poke_id = datos.getInt("POKE_ID");
            atk_position = datos.getInt("POSITION");
            attacks = db.getAttacks(poke_id);

        }
        //LOAD ADAPTER FOR MOVE LIST
        
        final MoveListAdapter adapter = new MoveListAdapter(this, attacks);
        ListView lista = (ListView) findViewById(R.id.moves_lv);

        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Add move to the pokemon?");
                alert.setMessage("");
                alert.setPositiveButton("Add", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int whichButton) {
                        DBHelper db = new DBHelper(context);
                        db.addAttackToPokemon(poke_id,adapter.getItem(position).atk_id,adapter.getItem(position).name,atk_position);

                        Intent intent = new Intent(MoveListActivity.this,SinglePokemonActivity.class);
                        intent.putExtra("POKE_ID",poke_id);
                        startActivity(intent);
                        finish();

                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });

                alert.show();

            }
        });


    }
}
