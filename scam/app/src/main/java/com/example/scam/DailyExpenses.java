package com.example.scam;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DailyExpenses extends AppCompatActivity {

    private static EditText food,transport,others;
    private static Button calculate;
    private static TextView display,display2;
    private static Double fd=0.0,tr=0.0,oth=0.0,newsave=0.0,prevmon,update;
    private static FirebaseAuth firebaseAuth;
    private static FirebaseDatabase firebaseDatabase;
    private static FirebaseUser firebaseUser;
    private static String name,age,clg,gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_expenses);
        food=findViewById(R.id.food);
        transport=findViewById(R.id.transport);
        others=findViewById(R.id.dailyotherexp);
        calculate=findViewById(R.id.calculate);
        display=findViewById(R.id.displaycalculated);
        display2=findViewById(R.id.updation);

        getData();

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(food.getText().toString().isEmpty() || transport.getText().toString().isEmpty() || others.getText().toString().isEmpty())
                {  Toast.makeText(DailyExpenses.this,"Enter 0 in case of no data",Toast.LENGTH_SHORT).show();}
                else {
                    fd = Double.parseDouble(food.getText().toString());
                    tr = Double.parseDouble(transport.getText().toString());
                    oth = Double.parseDouble(others.getText().toString());


                    if (prevmon > (fd + tr + oth)) {

                        newsave = prevmon - (fd + tr + oth);
                        if (update == 0) {

                            update = newsave + prevmon;
                        } else {
                            update = update + newsave;
                        }
                        display.setText("Congo you saved rupees : " + newsave);
                        display2.setText("Tommorow you can spend : " + update);

                    } else {
                        newsave = (fd + tr + oth) - prevmon;
                        if (update == 0) {
                            update = prevmon - newsave;
                        } else {
                            update = update - newsave;
                        }


                        display.setText("You lost rupees :" + newsave);
                        display2.setText("Tommorow you can spend :" + update);

                    }

                    sendData();
                }

            }

                });






    }
    public void getData(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = firebaseDatabase.getReference(firebaseUser.getUid());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                name = userProfile.getStudentname();
                age = userProfile.getStudentage();
                clg = userProfile.getStudentclg();
                gender = userProfile.getStudentgender();
                prevmon = userProfile.getPerdaymoneyleft();
                update = userProfile.getEverdaymoney();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
            public void sendData(){
                firebaseAuth = FirebaseAuth.getInstance();
                firebaseUser=firebaseAuth.getCurrentUser();
                FirebaseDatabase firebaseDatabase2 = FirebaseDatabase.getInstance();
                final DatabaseReference myRef1 = firebaseDatabase2.getReference(firebaseUser.getUid());

             myRef1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        UserProfile userProfile1=new UserProfile(name,age,clg,gender,prevmon,update);
                        myRef1.setValue(userProfile1);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

    }

