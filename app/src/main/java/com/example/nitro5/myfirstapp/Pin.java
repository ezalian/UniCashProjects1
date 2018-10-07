package com.example.nitro5.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.goodiebag.pinview.Pinview;

public class Pin extends AppCompatActivity{

    Pinview pinview;
Button logbutton;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        pinview = (Pinview)findViewById(R.id.pinview);
        logbutton = (Button)findViewById(R.id.logbutton);

        logbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Pin.this, Profile.class);
                startActivity(i);
                finish();
            }
        });

        pinview.setPinViewEventListener(new Pinview.PinViewEventListener() {
            @Override
            public void onDataEntered(Pinview pinview, boolean b) {
                Intent intent = new Intent(Pin.this, Profile.class);
                Toast.makeText(getApplicationContext(),pinview.getValue(),Toast.LENGTH_SHORT).show();

            }
        });

    }

}