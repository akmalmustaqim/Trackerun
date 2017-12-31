package com.muza.trackerun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class eventdetails extends AppCompatActivity {

    Button btnJoin;
    TextView txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventdetails);

        txtDescription = (TextView) findViewById(R.id.txtDescription);

        String para = "This event is organized by Faculty of Computer & Mathematical Sciences. The main objective" +
                "is to find young talent. This event will be held on 1/1/2018 (Saturday).";

        txtDescription.setText(para);

        btnJoin = (Button)findViewById(R.id.btnJoin);

    }

    public void toMapsActivity(View v)
    {
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(intent);
    }
}
