package com.example.sebastian.app_1.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sebastian.app_1.Adapters.TeamListAdapter;
import com.example.sebastian.app_1.R;
import com.example.sebastian.app_1.Utils.DBHelper;
import com.example.sebastian.app_1.Utils.TeamList;

import java.util.ArrayList;

//LAUNCHER//
public class TeamListActivity extends AppCompatActivity implements View.OnClickListener {
    ListView tlv;
    public TeamListAdapter adapter;
    Button btn;
    DBHelper db = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_list_main);
        btn = (Button) findViewById(R.id.button_add_team);
        btn.setOnClickListener(this);
        //DBHelper db = new DBHelper(this);
        db.getWritableDatabase();
        //db.dropTables();
        db.createTables();
        //db.addTeam("Team Kawaii");
        //db.addTeam("Team Johto");
        //db.addTeam("Team Sinnoh");
        //db.addTeam("Team Unova");
        //db.addTeam("Team Kanto");
        //db.addTeam("Team Legendario");

        ArrayList<TeamList> team_lists_data = db.getTeams();

        adapter = new TeamListAdapter(this,R.layout.team_list_listview_item_row,team_lists_data);
        tlv = (ListView) findViewById(R.id.team_lv);
        View header = (View) getLayoutInflater().inflate(R.layout.team_list_header_row,null);
        tlv.addHeaderView(header);
        tlv.setAdapter(adapter);
        tlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView x = (TextView)view.findViewById(R.id.team_name);

                Intent intent = new Intent(TeamListActivity.this,TeamActivity.class);
                String v = x.getText().toString();
                TeamList selectedTeam = adapter.data.get(position-1);
                intent.putExtra("TEAM_ID",selectedTeam.id);

                startActivity(intent);
                finish();
            }
        });
    }

    //SETS "ADD TEAM" EMERGENT WINDOW TO ADD A TEAM
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button_add_team){
            //Toast.makeText(getApplicationContext(),"Boton funca xddd",Toast.LENGTH_SHORT).show();
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("New Team");
            alert.setMessage("Choose the name for your team");
            // Set an EditText view to get user input
            final EditText input = new EditText(this);
            alert.setView(input);
            //POSITIVE ANSWER
            alert.setPositiveButton("Save", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int whichButton) {
                    String name = input.getText().toString();
                    if ((name.equals(""))||(name.length()>16)){
                        //IF NAME REQUIREMENTS NOT MET -> DO NOTHING
                        Toast.makeText(getApplicationContext(),"Please input a valid name",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //IF NAME REQUIREMENTS MET -> ADD TEAM + REFRESH VIEW
                        db.addTeam(name);
                        Toast.makeText(getApplicationContext(),name+" has been succesfully added",Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(getIntent());
                    }
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
