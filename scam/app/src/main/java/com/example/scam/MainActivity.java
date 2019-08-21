package com.example.scam;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static Button button_sb;
    private static Button button_na;
    private static EditText user_name;
    private static EditText password;
    private static TextView attempts,sem;
    private static Button user_login;
    private static FirebaseAuth firebaseAuth;
    private int counter=5;
    private static ProgressDialog progressDialog1;
    private static TextView forgot_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exitButton();
        createAc();


        views();
        progressDialog1 = new ProgressDialog(this);
        attempts.setText("No of attempts remaining = 5");

        forgot_password=(TextView)findViewById(R.id.etforgetpassword);


        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ResetPassword.class));
            }
        });


        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user!=null){
            finish();
            startActivity(new Intent(MainActivity.this,mainlayout.class));
        }



        user_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user_name.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Enter all the details",Toast.LENGTH_SHORT).show();
                }else{
                validate(user_name.getText().toString(),password.getText().toString());}
            }
        });

    }
    private void checkemailverification(){
        FirebaseUser firebaseUser=firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag=((FirebaseUser) firebaseUser).isEmailVerified();
        if(emailflag){
            finish();
            startActivity(new Intent(MainActivity.this, mainlayout.class));
        }else{
            Toast.makeText(MainActivity.this,"Verify Your Email",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }

    }

    private void validate(String userName,String userPassword){

        progressDialog1.setMessage("Verifying your account");
        progressDialog1.show();

        firebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog1.dismiss();

                    checkemailverification();

                }else{
                    counter--;
                    Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    progressDialog1.dismiss();
                    attempts.setText("No of attempts remaining = "+ counter );
                    if(counter==0){
                        user_login.setEnabled(false);

                    }
                }

            }
        });

    }


    public void views(){
        user_login = (Button)findViewById(R.id.login);
        user_name = (EditText)findViewById(R.id.name);
        attempts =(TextView)findViewById(R.id.info);
        password = (EditText)findViewById(R.id.pass);
    }

    public void createAc(){
        button_na = findViewById(R.id.newaccount);
        button_na.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(".RegistrationActivity");
                startActivity(intent);

            }
        });
    };

    public void exitButton() {
        button_sb = findViewById(R.id.button);
        button_sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                a_builder.setMessage("DO YOU WANT TO CLOSE THE APP???")
                        .setCancelable(false)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alert = a_builder.create();
                alert.setTitle("ALERT!!!");
                alert.show();

            }
        });

    }
}