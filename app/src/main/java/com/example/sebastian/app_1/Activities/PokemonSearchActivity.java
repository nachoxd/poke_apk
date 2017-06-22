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
                //REVISAR ACAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA PORQUE NO ESTA AGREGANDO A LA BD
                db.addPokemon(x.getText().toString(),type1,type2, AsyncPokemonSearch.type1, AsyncPokemonSearch.type2,"levitate",team_id, AsyncPokemonSearch.image);
                //REVISAR ACAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA PORQUE NO ESTA AGREGANDO A LA BD
                Toast.makeText(getApplicationContext(),"El poke se agrego a la bd",Toast.LENGTH_SHORT).show();
                //INTENT AND CHANGE TO ANOTHER ACTIVITY
                Intent intent = new Intent(PokemonSearchActivity.this,TeamActivity.class);
                intent.putExtra("TEAM_ID",team_id);
                //startActivity(intent);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
