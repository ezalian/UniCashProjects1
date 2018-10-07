package com.example.nitro5.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

         ImageView imageView;

       /** ImageView img = (ImageView) findViewById(R.id.imageView);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent act2 = new Intent(Homepage.this, Login.class);

                startActivity(act2);
            }
        }); **/

    }

    public void goToPay(View v) {
        startActivity(new Intent(this, Pay.class));
    }


}
