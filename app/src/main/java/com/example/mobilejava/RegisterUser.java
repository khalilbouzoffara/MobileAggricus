package com.example.mobilejava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener{
    private TextView gotosign;
    private EditText name,pass,repass,mail;
    private Button btnRegister,btnReturn ;
    private ProgressBar progressBar;
    private Dialog dialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        mAuth = FirebaseAuth.getInstance();

        btnRegister=findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        mail=findViewById(R.id.mail);
        mail.setOnClickListener(this);

        name=(EditText) findViewById(R.id.Fullname);
        name.setOnClickListener(this);

        pass=findViewById(R.id.pass);
       pass.setOnClickListener(this);

       repass=findViewById(R.id.repass);
        repass.setOnClickListener(this);

        gotosign=findViewById(R.id.gotosign);
        gotosign.setOnClickListener(this);

        progressBar=findViewById(R.id.progressBar2);

        btnReturn=findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterUser.this, Main2Activity.class));
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegister:
                registerUser();
                break;
            case R.id.gotosign:
                Intent intent = new Intent(RegisterUser.this , LogInActivity.class);
                startActivity(intent);
                break;
                
        }

    }

    private void registerUser() {
        String email =mail.getText().toString().trim();
        String Name=name.getText().toString().trim();
        String password =pass.getText().toString().trim();
        String repassword =repass.getText().toString().trim();

        if(Name.isEmpty()){
            name.setError("error");
            name.requestFocus();
            return;
        }

        if(email.isEmpty()){
           mail.setError("error");
           mail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mail.setError("email please");
            mail.requestFocus();
            return;


        }
        if(password.isEmpty()){
            pass.setError("error");
            pass.requestFocus();
            return;
    }
        if(password.length()<6){
            pass.setError("more then 6");
            pass.requestFocus();
        }
        if(repassword.isEmpty()){
            repass.setError("error");
            repass.requestFocus();
            return;
        }
        if(repassword.length()<6){
            repass.setError("more then 6");
            repass.requestFocus();
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User user=new User(Name,email);
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                dialog = new Dialog(RegisterUser.this);
                                dialog.setContentView(R.layout.dialogue_layout);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialogue_background));
                                }
                                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                dialog.setCancelable(false); //Optional
                                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

                                Button Okay = dialog.findViewById(R.id.btn_okay);
                                Button Cancel = dialog.findViewById(R.id.btn_cancel);
                                dialog.show();

                                Okay.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent intent = new Intent(RegisterUser.this , Main2Activity.class);
                                        startActivity(intent);
                                        dialog.dismiss();
                                    }
                                });

                                Cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent intent = new Intent(RegisterUser.this , LogInActivity.class);
                                        startActivity(intent);
                                        dialog.dismiss();
                                    }
                                });

                                progressBar.setVisibility(View.GONE);


                            }
                            else {
                                Toast.makeText(RegisterUser.this, "NotRegistered", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                }
            }


        });

}}