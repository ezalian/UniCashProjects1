package com.example.nitro5.myfirstapp;

/**main activity (registration)**/
/**textlayout.xml**/

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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

   private EditText edit_name,edit_birthdate,edit_number,edit_password,edit_pin,edit_email;
   private Button button_add,b2;
    private  ProgressBar progressBar;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testlayout);

        edit_name = findViewById(R.id.edit_name);
        edit_email = findViewById(R.id.edit_email);
        edit_birthdate = findViewById(R.id.edit_birthdate);
        edit_number = findViewById(R.id.edit_number);
        edit_password = findViewById(R.id.edit_password);
        edit_pin = findViewById(R.id.edit_pin);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.button_add).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() != null){
            /**handle login user**/
        }
    }

    private void registerUser() {
        final String username = edit_name.getText().toString().trim();
        final String email = edit_email.getText().toString().trim();
        final String password = edit_password.getText().toString().trim();
        final String phone = edit_number.getText().toString().trim();



        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Please enter username", Toast.LENGTH_LONG).show();
            return;
        }
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
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please enter phone number", Toast.LENGTH_LONG).show();
            return;
        }

        if (phone.length() != 7) {
            Toast.makeText(this, "Please Enter valid phone number", Toast.LENGTH_LONG).show();
            return;
        }




        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            User user = new User(
                                    username,
                                    email,
                                    phone,
                                    password
                            );

                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, "Registration Succesful!", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(MainActivity.this, Login.class);
                                    } else {
                                        //display a failure message
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add:
                registerUser();
                break;

            case R.id.button2:
                finish();
                startActivity(new Intent(this,Login.class));

        }
    }
}