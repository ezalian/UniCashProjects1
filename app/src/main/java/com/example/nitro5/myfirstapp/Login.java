package com.example.nitro5.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity implements View.OnClickListener{

     FirebaseAuth mAuth;
EditText  emailog,passwordlog;
 Button  loginbutton;
 ProgressBar progressBar;
TextView Link;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

mAuth = FirebaseAuth.getInstance();
emailog = findViewById(R.id.emailog);
passwordlog = findViewById(R.id.passwordlog);
progressBar = new ProgressBar(this);

findViewById(R.id.loginbutton).setOnClickListener(this);
Link = findViewById(R.id.Link);
Link.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(Login.this, MainActivity.class);
        startActivity(i);
        finish();
    }
});

    }

private void userlogin() {

    String email = emailog.getText().toString().trim();
    String password = passwordlog.getText().toString().trim();

    if (TextUtils.isEmpty(email)) {
        Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
        return;
    }
    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        Toast.makeText(this, "Please enter valid email", Toast.LENGTH_LONG).show();
        return;
    }
    if (TextUtils.isEmpty(password)) {
        Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
        return;
    }
    if (password.length() < 6) {
        Toast.makeText(this, "Maximum password length should be 6", Toast.LENGTH_LONG).show();
        return;
    }

    progressBar.setVisibility(View.VISIBLE);
    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
progressBar.setVisibility(View.GONE);
            if (task.isSuccessful()) {
                finish();
                Intent intent = new Intent(Login.this, Pin.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                emailog.setText("");
                passwordlog.setText("");
            }
        }
    });
}

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() !=null){
            finish();
            startActivity(new Intent(this,Pin.class));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Link:
            finish();
            startActivity(new Intent(this,MainActivity.class));

            break;

            case R.id.loginbutton:
                userlogin();
                break;

        }
    }

}
