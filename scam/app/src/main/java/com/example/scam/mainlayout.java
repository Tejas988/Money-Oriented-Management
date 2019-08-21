package com.example.scam;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class mainlayout extends AppCompatActivity {

    private static Button profile,monthly_goals,createprof,dailyexpenses,ti;

    private static FirebaseAuth firebaseAuth;
    private static FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainlayout);


        monthly_goals=(Button)findViewById(R.id.monthlygoals);
        monthly_goals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mainlayout.this, MonthlyGoals.class));
            }
        });

        dailyexpenses=findViewById(R.id.dailyexpenses);
        dailyexpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mainlayout.this,DailyExpenses.class));
            }
        });

        createprof=findViewById(R.id.createmyprofile);
        createprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mainlayout.this,StudentProfile.class));
            }
        });

        profile=(Button)findViewById(R.id.createyourprofile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(mainlayout.this, profiledisplay.class));
            }
        });
        ti=findViewById(R.id.tt);
        ti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mainlayout.this,Timetable.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        firebaseAuth=FirebaseAuth.getInstance();
        if(item.getItemId()== R.id.Logout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(mainlayout.this,MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


}
