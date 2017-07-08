package com.example.sebastian.app_1.AsyncTasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.example.sebastian.app_1.Adapters.PokemonSearchAdapter;
import com.example.sebastian.app_1.Utils.Attack;
import com.example.sebastian.app_1.Utils.Converter;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by victor on 5/27/17.
 */

public class AsyncPokemonSearch extends AsyncTask<String, Integer, Bitmap> {


    PokemonSearchAdapter adapter;
    public static String type1 = "", type2 = "";
    public static Bitmap image;
    public static ArrayList<Attack> attacks;
    public AsyncPokemonSearch(PokemonSearchAdapter adapter){
        this.adapter = adapter;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {

            //RESET VARIABLES
            type1 = "";
            type2 = "";
            image = null;
            attacks = new ArrayList<Attack>();
            ArrayList<String> nameHelper = new ArrayList<String>();
            // REQUEST
            Document doc = Jsoup.connect("https://pokemondb.net/pokedex/" + params[0]).get();


            // GET POKEMON'S ATTACKS
            Converter converter = new Converter();

            Element container_atk = doc.getElementsByClass("tabset-moves-game").first();

            Iterator<Element> ite = container_atk.select("tbody").iterator();
            Iterator<Element> ite2;
            String[] text,helper;
            String name = null,type = null,category = null;
            int power = 0,accuracy = 0;
            Element current;
            Attack new_atk;
            while(ite.hasNext()){
                ite2 = ite.next().select("tr").iterator();
                while(ite2.hasNext()){
                    current = ite2.next();
                    text = current.text().split(" ");
                    category = current.getElementsByClass("cell-icon").get(1).attr("data-sort-value");

                    if(text.length ==6){
                        name = text[1]+ " " + text[2];
                        type = text[3];
                        try{
                            power = Integer.getInteger(text[4]);
                        }catch (Exception e){
                            power = 0;
                        }
                        try{
                            accuracy = Integer.getInteger(text[5]);
                        }catch (Exception e){
                            accuracy = 0;
                        }
                    }
                    Log.d("","");
                    if(text.length == 5){
                        name = text[0] + " " + text[1];
                        type = text[2];
                        try{
                            power = Integer.getInteger(text[3]);
                        }catch (Exception e2){
                            power = 0;
                        }
                        try{
                            accuracy = Integer.getInteger(text[4]);
                        }catch (Exception e2){
                            accuracy = 0;
                        }
                    }
                    if(text.length == 4){
                        name = text[0];
                        type = text[1];
                        try{
                            power = Integer.getInteger(text[2]);
                        }catch (Exception e){
                            power = 0;
                        }
                        try{
                            accuracy = Integer.getInteger(text[3]);
                        }catch (Exception e){
                            accuracy = 0;
                        }
                    }

                    helper = name.split(" ");
                    if(StringUtil.isNumeric(helper[0])){
                        name = helper[1];
                    }

                    if(!nameHelper.contains(name)){
                        new_atk = new Attack(name,"description",type,1,category,power,accuracy);
                        //Log.d("NEW ATK",new_atk.name+" "+new_atk.description+" "+new_atk.type_string+" "+new_atk.type_int+ " "+new_atk.category+" "+ new_atk.power+ " "+ new_atk.accuracy);
                        attacks.add(new_atk);
                        nameHelper.add(name);
                    }

                }
            }


            //GET POKEMON'S ABILITIES

            Element table = doc.getElementsByClass("vitals-table").first();
            Element abilities = table.select("tr").get(5);
            String a1,a2=null,a3=null;

            a1 = abilities.select("a").get(0).text() + "#" + abilities.select("a").get(0).attr("title");
            if(abilities.select("a").size() >= 2){
                a2 = abilities.select("a").get(1).text() + "#" + abilities.select("a").get(1).attr("title");
            }
            if(abilities.select("a").size() >= 3){
                a3 = abilities.select("a").get(2).text() + "#" + abilities.select("a").get(2).attr("title");
            }

            //Log.d("a1",a1);
            //Log.d("a2",""+a2);
            //Log.d("a3",""+a3);


            // GET POKEMON'S TYPYING
            Element tipo1 = table.getElementsByClass("type-icon").first();
            Element tipo2 = null;
            if(table.getElementsByClass("type-icon").size() != 1){
                tipo2 = table.getElementsByClass("type-icon").get(1);
            }
            String stringTipo1 = tipo1.text();
            type1 = tipo1.text().trim().toLowerCase();
            String stringTipo2 = "nulo";
            if(tipo2 != null){
                stringTipo2 = tipo2.text();
                type2 = tipo2.text().trim().toLowerCase();
            }

            //LOAD POKEMON IMAGE
            URL url = new URL("http://play.pokemonshowdown.com/sprites/bw/"+params[0]+".png");
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            //SEND DATA TO POKEMON SEARCH ADAPTER
            adapter.addPokemon(params[0].substring(0,1).toUpperCase()+params[0].substring(1), image, stringTipo1,stringTipo2,a1,a2,a3);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    protected Bitmap loadFromURL(String url){
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;

    }
    protected void onPostExecute(Bitmap result){
        //UPDATE ADAPTER
        adapter.notifyDataSetChanged();
    }
}

