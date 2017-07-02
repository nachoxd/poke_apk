package com.example.sebastian.app_1.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sebastian.app_1.AsyncTasks.AsyncPokemonSearch;
import com.example.sebastian.app_1.Adapters.PokemonSearchAdapter;
import com.example.sebastian.app_1.R;
import com.example.sebastian.app_1.Utils.Attack;
import com.example.sebastian.app_1.Utils.Converter;
import com.example.sebastian.app_1.Utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class PokemonSearchActivity extends AppCompatActivity {


    private int team_id;
    public Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //INIT
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.context = this;

        //INTENT
        Intent intent = getIntent();
        Bundle datos = intent.getExtras();
        if(datos != null){

            team_id = datos.getInt("TEAM_ID");
        }

        //LOAD ADAPTER FOR POKEMON LIST
        List<String> poke_list = new ArrayList<String>();
        final PokemonSearchAdapter adapter = new PokemonSearchAdapter(this, poke_list);
        ListView lista = (ListView) findViewById(R.id.listView);
        lista.setAdapter(adapter);

        //SEARCH BUTTON TOUCHED
        Button pokemon = (Button) findViewById(R.id.button);
        pokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text = (EditText) findViewById(R.id.editText);

                //EXECUTE TASK TO GET DATA FROM POKEMONDB.NET
                new AsyncPokemonSearch(adapter).execute(text.getText().toString());

                //CLOSE KEYBOARD AND CLEAN TEXT INPUT
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                text.setText("");


            }
        });

        //RESULT ELEMENT TOUCHED
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TextView x = (TextView)view.findViewById(R.id.search_poke_name);

                //ADD pokemon to DB
                DBHelper db = new DBHelper(context);
                Converter converter = new Converter();
                int type1 = converter.StringToIntType(context,AsyncPokemonSearch.type1);
                int type2 = converter.StringToIntType(context,AsyncPokemonSearch.type2);

                //ADD POKEMON TO DB
                int poke_id = db.addPokemon(x.getText().toString(),type1,type2, AsyncPokemonSearch.type1, AsyncPokemonSearch.type2,adapter.getAbility(position,1).split("#")[0],team_id, AsyncPokemonSearch.image);

                //ADD ABILITIES TO DB

                String a1 = adapter.getAbility(position,1);
                String a2 = adapter.getAbility(position,2);
                String a3 = adapter.getAbility(position,3);

                db.addAbility(poke_id,a1.split("#")[0],a1.split("#")[1]);
                if(a2 != null){
                    db.addAbility(poke_id,a2.split("#")[0],a2.split("#")[1]);
                }
                if(a3 != null){
                    db.addAbility(poke_id,a3.split("#")[0],a3.split("#")[1]);
                }

                //ADD ATTACKS TO DB

                for(Attack atk : AsyncPokemonSearch.attacks){
                    db.addAttack(poke_id,atk.name,atk.description,atk.type_string,atk.type_int,atk.category,atk.power,atk.accuracy);
                }

                //TEST CHECK

                db.getAttacks(poke_id);


                Toast.makeText(getApplicationContext(),"Pokemon ha sido agregado al Team",Toast.LENGTH_SHORT).show();
                //INTENT AND BACK TO TEAM ACTIVITY
                Intent intent = new Intent(PokemonSearchActivity.this,TeamActivity.class);
                intent.putExtra("TEAM_ID",team_id);
                startActivityForResult(intent,1);
                //setResult(1,intent);
                finish();
            }
        });
    }
}
