package com.example.scam;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MonthlyGoals extends AppCompatActivity {
    private static EditText pocket_money,money_save,train_pass,mobile_data,other_expenses;
    private static Button evalute;
     Double pm,ms,tp,md,oe,total,saved;
    private static FirebaseAuth firebaseAuth;
    private static FirebaseDatabase firebaseDatabase;
    private static FirebaseUser firebaseUser;
   private static String name,age,clg,gender;
   private static Double mon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_goals);
        pocket_money=findViewById(R.id.pocketmoney);
        money_save=findViewById(R.id.savemoney);
        train_pass=findViewById(R.id.trainpass);
        mobile_data=findViewById(R.id.datapack);
        other_expenses=findViewById(R.id.otherexpenses);
        evalute=findViewById(R.id.spendingmoney);





        evalute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pocket_money.getText().toString().isEmpty() || money_save.getText().toString().isEmpty() || train_pass.getText().toString().isEmpty() || mobile_data.getText().toString().isEmpty() || other_expenses.getText().toString().isEmpty())
                { Toast.makeText(MonthlyGoals.this,"Enter 0 in case of no data",Toast.LENGTH_SHORT).show();}
                else{
                pm=Double.parseDouble(pocket_money.getText().toString());
                ms=Double.parseDouble(money_save.getText().toString());
                tp=Double.parseDouble(train_pass.getText().toString());
                md=Double.parseDouble(mobile_data.getText().toString());
                oe=Double.parseDouble(other_expenses.getText().toString());
                total=pm-(ms+tp+md+oe);
                saved=total/30;;

                    sendUser1Data();
                    Toast.makeText(MonthlyGoals.this,"Your per day amount is "+ saved,Toast.LENGTH_LONG).show();;}
                }

        });




    }

    private  void sendUser1Data() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = firebaseDatabase.getReference(firebaseUser.getUid());

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile=dataSnapshot.getValue(UserProfile.class);
                name=userProfile.getStudentname();
                age=userProfile.getStudentage();
                clg=userProfile.getStudentclg();
                gender=userProfile.getStudentgender();
                mon=userProfile.getEverdaymoney();
                UserProfile userProfile1=new UserProfile(name,age,clg,gender,saved,mon);
                myRef.setValue(userProfile1);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
