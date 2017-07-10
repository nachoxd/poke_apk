package com.example.sebastian.app_1.Adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sebastian.app_1.R;
import com.example.sebastian.app_1.Utils.Attack;

import java.util.ArrayList;

/**
 * Created by victor on 7/9/17.
 */

public class MoveListAdapter extends ArrayAdapter<Attack> {
    public ArrayList<Attack> attacks;
    Activity context;
    public MoveListAdapter(Activity context, ArrayList<Attack> attacks) {
        super(context, R.layout.single_move_row, attacks);
        this.attacks = attacks;
        this.context = context;
        Log.d("ATTACK AMOUNT",attacks.size()+"");
    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.single_move_row, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.atk_name);
        TextView power = (TextView) rowView.findViewById(R.id.power);
        TextView accuracy = (TextView) rowView.findViewById(R.id.accuracy);

        ImageView type = (ImageView) rowView.findViewById(R.id.move_type);
        ImageView category = (ImageView) rowView.findViewById(R.id.move_category);
        //
        type.setImageResource(attacks.get(position).type_int);
        if(attacks.get(position).category.equals("physical")){
            category.setImageResource(R.drawable.physical);
        }
        else if(attacks.get(position).category.equals("special")){
            category.setImageResource(R.drawable.special);
        }
        else{
            category.setImageResource(R.drawable.status);
        }
        txtTitle.setText(attacks.get(position).name);
        if(attacks.get(position).power == 0){
            power.setText(" ---");
        }
        else{
            power.setText(""+ attacks.get(position).power);
        }
        if(attacks.get(position).accuracy == 0){
            accuracy.setText(" ---");
        }
        else{
            accuracy.setText(""+ attacks.get(position).accuracy+"%");
        }

        return rowView;

    }
}
