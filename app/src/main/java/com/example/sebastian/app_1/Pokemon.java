package com.example.sebastian.app_1;

/**
 * Created by Sebastian on 21-05-2017.
 */

public class Pokemon {
    public int icon;
    public String name;
    public int type1;
    public int type2;
    public String ability;
    public String mov1,mov2,mov3,mov4;
    public String tipo1,tipo2;

    public Pokemon(){
        super();
    }
    public Pokemon(int icon, String name, int type1, int type2, String ability){
        super();
        this.icon=icon;
        this.name=name;
        this.type1=type1;
        this.type2=type2;
        this.ability=ability;
    }
}