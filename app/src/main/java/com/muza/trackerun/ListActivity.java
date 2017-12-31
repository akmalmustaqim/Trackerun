package com.muza.trackerun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {

    Button btnView;
    ListView lst;
    String[] eventname = {"FSKM Get Fit 2.0", "Our Run", "Charity Run", "Globo Electro Run", "Zombie Run"};
    String[] desc = {"Run for fun", "Run for fun", "Run for fun", "Run for fun", "Run for fun"};
    Integer[] imgid = {R.drawable.run, R.drawable.run, R.drawable.run, R.drawable.run, R.drawable.run};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lst = (ListView) findViewById(R.id.listview);
        CustomListview customListview = new CustomListview(this, eventname, desc, imgid);
        lst.setAdapter(customListview);

        btnView = (Button) findViewById(R.id.btnView);
    }

    public void toEventDetails(View v)
    {
        Intent intent = new Intent(getApplicationContext(), eventdetails.class);
        startActivity(intent);
    }
}
