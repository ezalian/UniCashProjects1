package com.example.nitro5.myfirstapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Profile extends AppCompatActivity {
    TextView textViewWelcome,textView;
    EditText  username;
    Button savebutton;
ProgressBar progressBar;
FirebaseAuth mAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle(R.string.welcome);

        mAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        username = findViewById(R.id.username);
        progressBar = findViewById(R.id.progressBar);
        textViewWelcome = findViewById(R.id.textViewWelcome);
        textViewWelcome.setText(getString(R.string.welcome));

         loadUserInformation();

        findViewById(R.id.savebutton).setOnClickListener(new View.OnClickListener() {
                    public void onClick (View v){
                     saveUserInformation();

                    }
                });
    }


    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,Login.class));
        }

    }

    private void loadUserInformation() {
        final FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {

            if (user.getDisplayName() != null) {
                username.setText(user.getDisplayName());

            }
    }
    }

    private void saveUserInformation(){
        String displayName = username.getText().toString();

        if(displayName.isEmpty()){
            username.setError("Name required");
            username.requestFocus();
            return;
        }
    FirebaseUser user = mAuth.getCurrentUser();

        if(user!=null){
            progressBar.setVisibility(View.VISIBLE);
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder().setDisplayName(displayName).build();
        user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override


            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Profile.this,"Profile updated",Toast.LENGTH_SHORT).show();
                }
            }
        });

        }
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuLogout:
                mAuth.signOut();
                finish();
                startActivity(new Intent(this,Login.class));
                break;

           case R.id.menuProfile:
                startActivity(new Intent(this,Profile.class));
                break;


        }


        return true;


    }
}



    /**retrieve db here manually,use asynctasks approach avoid termination,UI thread,long process(retrieveing data) in threadingasynctasks**/
