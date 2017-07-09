package com.example.sebastian.app_1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.sebastian.app_1.Adapters.TeamAdapter;
import com.example.sebastian.app_1.R;
import com.example.sebastian.app_1.Utils.DBHelper;
import com.example.sebastian.app_1.Utils.Pokemon;

import java.util.List;


public class TeamActivity extends AppCompatActivity {
    ListView lv;
    private int team_id = 0;
    private TeamAdapter adapter;
    private DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent_in = getIntent();
        Bundle extras = intent_in.getExtras();

        if (extras!=null) {

            //GET TEAM FROM ID
            team_id = extras.getInt("TEAM_ID");
            db = new DBHelper(this);

            //GET POKEMONS FROM TEAM
            List<Pokemon> pokemons = db.getPokemonsFromTeam(team_id);

            //LOAD POKEMONS IN LIST ADAPTER
            adapter = new TeamAdapter(this,R.layout.poke_list_listview_item_row,pokemons);
            lv = (ListView) findViewById(R.id.lv);
            View header = (View) getLayoutInflater().inflate(R.layout.poke_list_header_row,null);
            lv.addHeaderView(header);
            lv.setAdapter(adapter);


        }

        //ELEMENT (POKEMON) OF LIST TOUCHED
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(TeamActivity.this,SinglePokemonActivity.class);

                int poke_id = adapter.getItem(position-1).id;
                Log.d("Abilities",""+db.getAbilities(poke_id));
                intent.putExtra("POKE_ID",poke_id);
                intent.putExtra("TEAM_ID",team_id);
                startActivity(intent);
            }
        });
        Button pokemon = (Button) findViewById(R.id.button_add_poke);
        pokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(TeamActivity.this,PokemonSearchActivity.class);
            intent.putExtra("TEAM_ID",team_id);
            startActivity(intent);
            finish();

            }
        });

    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(TeamActivity.this,TeamListActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

}
