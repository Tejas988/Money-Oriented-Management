package com.example.scam;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {
    private static Button create_button;
    private static EditText name;
    private static EditText email;
    private static EditText password;
    private  TextView info;
    private FirebaseAuth login_data;
    private static ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        diffviews();

        login_data = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(RegistrationActivity.this);

        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if (validate()){


                String useremail = email.getText().toString().trim();
                String passme = password.getText().toString().trim();
                progressDialog.setMessage("Creating Your Account");
                progressDialog.show();
                login_data.createUserWithEmailAndPassword(useremail,passme).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            sendemailverification();

                        }
                        else{
                            progressDialog.dismiss();
                            Toast.makeText(RegistrationActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }


    public void sendemailverification(){

        final FirebaseUser firebaseUser = login_data.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegistrationActivity.this,"Registration Successful: Email verification sent",Toast.LENGTH_LONG).show();
                        finish();
                        login_data.signOut();
                        startActivity(new Intent(RegistrationActivity.this,MainActivity.class));

                    }else{
                        Toast.makeText(RegistrationActivity.this," Email verification not sent",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public void diffviews(){
        create_button = (Button)findViewById(R.id.createaccount);
        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.etEmail);
        password = (EditText)findViewById(R.id.pass);
        info = (TextView)findViewById(R.id.logingo);

    };
    public Boolean validate(){
        Boolean value = false;
        String username = name.getText().toString();
        String passme = password.getText().toString();

        String useremail = email.getText().toString();

         if(username.isEmpty() || passme.isEmpty() || useremail.isEmpty())
        {
            Toast.makeText(this,"Please Enter All The Details",Toast.LENGTH_SHORT).show();


        }else if(passme.length()<=5){
            Toast.makeText(RegistrationActivity.this,"Password should contain atleast 6 characters",Toast.LENGTH_LONG).show();
        }else if(useremail.indexOf("@")<=0 || useremail.charAt(useremail.length()-1)!='m' || useremail.charAt(useremail.length()-2)!='o'|| useremail.charAt(useremail.length()-3)!='c' || useremail.charAt(useremail.length()-4)!='.') {
             Toast.makeText(RegistrationActivity.this,"Invalid Email Id",Toast.LENGTH_LONG).show();

         }
         else{
            value = true;

        }
        return value;
    };

}


