package com.example.scam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class StudentProfile extends AppCompatActivity {
   private Spinner gender;
    private EditText name,clgname,age;
    private  static ImageView profile_pic;
    Button create_prof;

    private static FirebaseAuth firebaseAuth;

    String username,userclg,usergender,userage;
    Double usermoney=0.0,initialmon=0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        gender=(Spinner)findViewById(R.id.gender);

        List<String>list = new ArrayList<>();
        list.add("male");
        list.add("female");
        list.add("others");

       ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(StudentProfile.this,android.R.layout.simple_spinner_item,list);
      arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        gender.setAdapter(arrayAdapter);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String itemvale = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(StudentProfile.this,"You selected"+itemvale,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });






        setCreate_prof();







    }

    public void views(){
        name=(EditText)findViewById(R.id.profilename);
        clgname=(EditText)findViewById(R.id.college);
        age=(EditText)findViewById(R.id.age);
        profile_pic=(ImageView)findViewById(R.id.profilepic);

        username=name.getText().toString();
        userage=age.getText().toString();
        userclg=clgname.getText().toString();
        usergender=gender.getSelectedItem().toString();



    }
    private  void sendUserData(){

        firebaseAuth =FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference myRef=firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile userProfile = new UserProfile(username,userage,userclg,usergender,usermoney,initialmon);
        myRef.setValue(userProfile);
    }
    public void setCreate_prof(){
        create_prof=(Button)findViewById(R.id.createprofile);
        create_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                views();
                if(username.isEmpty() || userage.isEmpty() || userclg.isEmpty() || usergender.isEmpty()){
                    Toast.makeText(StudentProfile.this,"Please Enter All The Details",Toast.LENGTH_SHORT).show();
                }else{
                sendUserData();
                Toast.makeText(StudentProfile.this,"Profile Created Successfully",Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(StudentProfile.this,mainlayout.class));}

            }
        });
    }

}
