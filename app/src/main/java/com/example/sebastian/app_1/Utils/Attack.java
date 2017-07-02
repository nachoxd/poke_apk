package com.example.sebastian.app_1.Utils;

/**
 * Created by victor on 7/1/17.
 */

public class Attack {
    public String name, description, type_string, category;
    public int type_int, power, accuracy,poke_id;
    public Attack(String name, String description, String type_string, int type_int, String category, int power, int accuracy){
        this.name = name;
        this. description = description;
        this.type_string = type_string;
        this.type_int = type_int;
        this.category = category;
        this.power = power;
        this.accuracy = accuracy;
    }
}
