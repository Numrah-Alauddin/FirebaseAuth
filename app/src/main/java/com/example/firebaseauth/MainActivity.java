package com.example.firebaseauth;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText email_et;
    EditText pass_et;
    Button signup;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=email_et.getText().toString();
                String pass=pass_et.getText().toString();

                signupUser(email,pass);

                email_et.setText("");
                pass_et.setText("");

            }
        });
    }

    private void signupUser(String name,String pass) {

        auth.createUserWithEmailAndPassword(name,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Signup", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void init() {

        email_et=findViewById(R.id.email);
        pass_et=findViewById(R.id.pass);
        signup=findViewById(R.id.signup_btn);
        auth=FirebaseAuth.getInstance();


    }
}
