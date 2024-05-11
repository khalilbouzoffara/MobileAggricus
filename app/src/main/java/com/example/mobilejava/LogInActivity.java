package com.example.mobilejava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editemail,editpassword;
    private TextView goToRegister;
    private Button signin,btnReturn ;
    private FirebaseAuth mAuth ;
    private ProgressBar progressBar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        signin=(Button)findViewById(R.id.btnlogin);
        signin.setOnClickListener(this);
        editemail=findViewById(R.id.editemail);
        editpassword=findViewById(R.id.editpassw);
        progressBar=findViewById(R.id.progressBar3);
        btnReturn=findViewById(R.id.btnReturn);
        mAuth= FirebaseAuth.getInstance();

        goToRegister=findViewById(R.id.goToRegister);
        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogInActivity.this,RegisterUser.class));
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogInActivity.this,Main2Activity.class));
            }
        });
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnlogin:
                userLogin();
                break;

        }

    }

    private void userLogin() {
        String email=editemail.getText().toString().trim();
        String password=editpassword.getText().toString().trim();
        if (email.isEmpty()) {
            editemail.setError("Email error");
            editemail.requestFocus();
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editemail.setError("Email error");
            editemail.requestFocus();

        }
        if (password.isEmpty()) {
            editpassword.setError("E error");
            editpassword.requestFocus();
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(LogInActivity.this,Main2Activity.class));
                    //redirect to user interface

                }else {
                    Toast.makeText(LogInActivity.this, "Login failed !", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}