package com.example.moneymanager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button btnSignIn;
    private FirebaseAuth firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebase = FirebaseAuth.getInstance(); //point to rootNode
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        //existing users don't have to sign in again
        if(firebase.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), HomePage.class));
        }

        //Sign In Activity
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = email.getText().toString().trim();
                String pw = password.getText().toString().trim();

                //input validation
                if(TextUtils.isEmpty(em)) {
                    email.setError("Please enter Email");
                    return;
                }
                if(TextUtils.isEmpty(pw)) {
                    password.setError("Please enter Password");
                    return;
                }

                //create new user
           /*     firebase.createUserWithEmailAndPassword(em,pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                    }
                }); */

                firebase.signInWithEmailAndPassword(em,pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"Sign In Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), HomePage.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Sign In Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}