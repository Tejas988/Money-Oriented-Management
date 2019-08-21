package com.example.scam;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {

    private static EditText email_pass;
    private static Button reset_pass;
    private static FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        email_pass=(EditText)findViewById(R.id.etemailpassreset);
        reset_pass=(Button)findViewById(R.id.passresetbtn);
        firebaseAuth=FirebaseAuth.getInstance();


        reset_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail = email_pass.getText().toString().trim();
                if(useremail.equals(null)){
                    Toast.makeText(ResetPassword.this,"Enter the registered emailId",Toast.LENGTH_SHORT).show();
                }else {
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                            Toast.makeText(ResetPassword.this,"Reset password email sent!!",Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(ResetPassword.this,MainActivity.class));}
                        else {
                                Toast.makeText(ResetPassword.this, "Error in sending reset password email !!", Toast.LENGTH_LONG).show();
                                Toast.makeText(ResetPassword.this, "Make sure if entered email is registered!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.Back){

            finish();
            startActivity(new Intent(ResetPassword.this,MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
