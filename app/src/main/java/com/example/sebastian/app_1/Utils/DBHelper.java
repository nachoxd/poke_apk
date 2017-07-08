package com.example.sebastian.app_1.Utils;

/**
 * Created by victor on 5/27/17.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "pokemon.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_TEAM = "create table if not exists team"
            + "("
            + "_id" + " integer primary key autoincrement,"
            + "name" + " text not null " + ")";

    private static final String CREATE_TABLE_POKEMON = "create table if not exists pokemon"
            + "("
            + "_id" + " integer primary key autoincrement, "
            + "name" + " text not null, "
            + "type_int_1" + " int not null, "
            + "type_int_2" + " int not null, "
            + "type_string_1" + " text not null, "
            + "type_string_2" + " text not null, "
            + "ability" + " text not null, "
            + "team_id" + " integer,"
            + "image" + " blob,"

            + "move1" + " text not null, "
            + "move2" + " text not null, "
            + "move3" + " text not null, "
            +" move4" + " text not null, "

            + "move1_id" + " int not null, "
            + "move2_id" + " int not null, "
            + "move3_id" + " int not null, "
            + "move4_id" + " int not null, "



            + "FOREIGN KEY(team_id) REFERENCES team(_id), "
            + "FOREIGN KEY(move1_id) REFERENCES attack(_id), "
            + "FOREIGN KEY(move2_id) REFERENCES attack(_id), "
            + "FOREIGN KEY(move3_id) REFERENCES attack(_id), "
            + "FOREIGN KEY(move4_id) REFERENCES attack(_id) "


            + ")";

    private static final String CREATE_TABLE_ABILITY = "create table if not exists ability"
            + "("
            + "_id" + " integer primary key autoincrement, "
            + "name" + " text not null, "
            + "description" + " text not null, "
            + "poke_id" + " integer,"
            + "FOREIGN KEY(poke_id) REFERENCES pokemon(_id) "
            + ")";

    private static final String CREATE_TABLE_ATTACK = "create table if not exists attack"
            + "("
            + "_id" + " integer primary key autoincrement, "
            + "name" + " text not null, "
            + "description" + " text not null, "
            + "type_string" + " text not null, "
            + "type_int" + " int not null, "
            + "category" + " text not null, "
            + "power" + " int not null, "
            + "accuracy" + " int not null, "
            + "poke_id" + " integer,"
            + "FOREIGN KEY(poke_id) REFERENCES pokemon(_id) "
            + ")";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TEAM);
        db.execSQL(CREATE_TABLE_POKEMON);
        db.execSQL(CREATE_TABLE_ABILITY);
        db.execSQL(CREATE_TABLE_ATTACK);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //CREATE DB TABLES
    public void createTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(CREATE_TABLE_TEAM);
        db.execSQL(CREATE_TABLE_POKEMON);
        db.execSQL(CREATE_TABLE_ABILITY);
        db.execSQL(CREATE_TABLE_ATTACK);
    }

    //DROP DB TABLES (ONLY USED FOR TESTING PURPOSES)
    public void dropTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS ability");
        db.execSQL("DROP TABLE IF EXISTS pokemon");
        db.execSQL("DROP TABLE IF EXISTS team");

    }

    //ADD POKEMON TO DB. TEAM ID REQUIRED
    public int addPokemon(String name, int type1,int type2, String typeString1, String typeString2, String ability,int team_id, Bitmap image){
        SQLiteDatabase db = this.getWritableDatabase();
        Converter converter = new Converter();
        String imageString = converter.BitMapToString(image);
        db.execSQL("INSERT INTO pokemon (name,type_int_1,type_int_2,type_string_1,type_string_2,ability,team_id,image,move1,move2,move3,move4,move1_id,move2_id,move3_id,move4_id) VALUES " +
                "(" +
                "'"+name + "',"+
                type1 + "," +
                type2 + "," +

                "'"+ typeString1 + "',"+
                "'"+ typeString2 + "',"+

                "'"+ability + "',"+
                team_id + "," +

                "'"+imageString + "'," +

                " '', " +
                " '', " +
                " '', " +
                " '', " +

                " -1, " +
                " -1, " +
                " -1, " +
                " -1 " +


                ")");

        Cursor cursor = db.query("pokemon",null,null,null,null,null,null);
        cursor.moveToLast();
        return cursor.getInt(0);
    }

    public void deletePokemon(int poke_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM pokemon WHERE _id = " + Integer.toString(poke_id));
        db.execSQL("DELETE FROM ability WHERE poke_id = " + Integer.toString(poke_id));
        db.execSQL("DELETE FROM attack WHERE poke_id = " + Integer.toString(poke_id));
    }


    public void deleteTeam(int team_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM pokemon WHERE team_id = " + Integer.toString(team_id));
        db.execSQL("DELETE FROM team WHERE _id = " + Integer.toString(team_id));
    }

    //ADD TEAM TO DB
    public void addTeam(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(
                "INSERT INTO team (name) VALUES "
                        + "('"
                        + name
                        + "')"
        );
    }

    //GET TEAM LIST FROM DB
    public ArrayList<TeamList> getTeams(){
        ArrayList<TeamList> teams = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM team",null);

        if(cursor.moveToFirst()){
            do{
                TeamList team = new TeamList();
                team.id = cursor.getInt(0);
                team.team_name = cursor.getString(1);
                ArrayList<Pokemon> pokemons = getPokemonsFromTeam(team.id);
                for(int i = 0; i < pokemons.size();i++){
                    team.pokemons.add(pokemons.get(i).image);
                }

                teams.add(team);
            }while (cursor.moveToNext());
        }
        Log.d("TEAMSIZE",""+cursor.getCount());
        return teams;
    }

    //GET SINGLE TEAM WITH ITS POKEMON FROM DB
    public ArrayList<Pokemon> getPokemonsFromTeam(int team_id){
        ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
        SQLiteDatabase db = this.getWritableDatabase();
        Converter converter = new Converter();
        Cursor cursor = db.rawQuery("SELECT * FROM pokemon where team_id = " + Integer.toString(team_id),null);
        if (cursor.moveToFirst()) {
            do {
                Pokemon pokemon = new Pokemon();
                pokemon.id = Integer.parseInt(cursor.getString(0));
                pokemon.name = cursor.getString(1);
                pokemon.type_int_1 = Integer.parseInt(cursor.getString(2));

                pokemon.type_int_2 = Integer.parseInt(cursor.getString(3));
                pokemon.type_string_1 = cursor.getString(4);
                pokemon.type_string_2 = cursor.getString(5);
                pokemon.ability = cursor.getString(6);
                pokemon.team_id = Integer.parseInt(cursor.getString(7));
                pokemon.image = converter.StringToBitMap(cursor.getString(8));
                // Adding contact to list
                pokemons.add(pokemon);
            } while (cursor.moveToNext());
        }
        return pokemons;
    }

    public void addAbility(int poke_id, String name, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        name = name.replace("'","");
        description = description.replace("'","");
        db.execSQL("INSERT INTO ability (name,description,poke_id) VALUES " +
                "(" +
                "'"+name + "',"+

                "'"+ description + "',"+

                poke_id +

                ")");

    }
    public ArrayList<String> getAbilities(int poke_id){
        ArrayList<String> abilities = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ability WHERE poke_id = "+poke_id,null);

        if(cursor.moveToFirst()){
            do{
                abilities.add(cursor.getString(1)+"#"+cursor.getString(2));
            }while (cursor.moveToNext());
        }
        return abilities;
    }


    public void addAttack(int poke_id, String name, String description, String type_string, int type_int, String category, int power, int accuracy){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO attack (name,description,type_string,type_int,category,power,accuracy,poke_id) VALUES " +
                "(" +
                "'"+name + "',"+

                "'"+ description + "'," +
                "'"+ type_string + "'," +
                "'"+ type_int + "'," +
                "'"+ category + "'," +
                "'"+ power + "'," +
                "'"+ accuracy + "'," +
                poke_id +
                ")");
    }
    public ArrayList<Attack> getAttacks(int poke_id){
        ArrayList<Attack> attacks = new ArrayList<Attack>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM attack WHERE poke_id = "+poke_id,null);

        if(cursor.moveToFirst()){
            do{
                attacks.add(new Attack(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4),cursor.getString(5),cursor.getInt(6),cursor.getInt(7)));
            }while (cursor.moveToNext());
        }
        Log.d("ATTACK AMOUNT",attacks.size()+"");
        return attacks;
    }

    public void updateAttack(int atk_id, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery("UPDATE attack SET description = '"+description+"' WHERE id= "+atk_id,null);
    }

    //UPDATES AN ATTACK OF A POKEMON. POSITION INDICATES WHICH MOVE WILL BE ADDED OR REPLACED
    //POSIBLE POSITION VALUES = [1,2,3,4]
    public void addAttackToPokemon(int poke_id, int atk_id, String atk_name, int position){
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery("UPDATE pokemon SET move"+position+" = '"+atk_name+"', move"+position+"_id = "+atk_id+" WHERE id= "+poke_id,null);
    }
}
