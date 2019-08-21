package com.example.scam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profiledisplay extends AppCompatActivity {

    private static TextView name,age,college,gender;
    private static ImageView pic_display;
    private static Button update;
    private static FirebaseDatabase firebaseDatabase;
    private static FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiledisplay);

        name=findViewById(R.id.namedisplay);
        age=findViewById(R.id.agedisplay);
        college=findViewById(R.id.clgdisplay);
        gender=findViewById(R.id.genderdisplay);
        update=findViewById(R.id.updation);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();

        DatabaseReference databaseReference=firebaseDatabase.getReference(firebaseAuth.getUid());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(profiledisplay.this,StudentProfile.class));
                finish();
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile=dataSnapshot.getValue(UserProfile.class);
                name.setText(userProfile.getStudentname());
                age.setText(userProfile.getStudentage());
                college.setText(userProfile.getStudentclg());
                gender.setText(userProfile.getStudentgender());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(profiledisplay.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();

            }
        });

    }
}
