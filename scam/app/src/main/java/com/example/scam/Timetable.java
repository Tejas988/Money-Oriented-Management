package com.example.scam;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Timetable extends AppCompatActivity {
    private static EditText l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
    private static String ul1,ul2,ul3,ul4,ul5,ul6,ul7,ul8,ul9,ul10;
    private static Double uf1,uf2,uf3,uf4,uf5,uf6,uf7,uf8,uf9,uf10,ut1,ut2,ut3,ut4,ut5,ut6,ut7,ut8,ut9,ut10;
    private static FirebaseAuth firebaseAuth;
    private static FirebaseDatabase firebaseDatabase;
    private static FirebaseUser firebaseUser;
    private static Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        GetIds();
        button=findViewById(R.id.senddata);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ul1=l1.getText().toString(); uf1=Double.parseDouble(f1.getText().toString());ut1=Double.parseDouble(t1.getText().toString());
                ul2=l2.getText().toString();uf2=Double.parseDouble(f2.getText().toString());ut2=Double.parseDouble(t2.getText().toString());
                ul3=l3.getText().toString();uf3=Double.parseDouble(f3.getText().toString());ut3=Double.parseDouble(t3.getText().toString());
                ul4=l4.getText().toString();uf4=Double.parseDouble(f4.getText().toString());ut4=Double.parseDouble(t4.getText().toString());
                ul5=l5.getText().toString();uf5=Double.parseDouble(f5.getText().toString());ut5=Double.parseDouble(t5.getText().toString());
                ul6=l6.getText().toString();uf6=Double.parseDouble(f6.getText().toString());ut6=Double.parseDouble(t6.getText().toString());
                ul7=l7.getText().toString();uf7=Double.parseDouble(f7.getText().toString());ut7=Double.parseDouble(t7.getText().toString());
                ul8=l8.getText().toString();uf8=Double.parseDouble(f8.getText().toString());ut8=Double.parseDouble(t8.getText().toString());
                ul9=l9.getText().toString();uf9=Double.parseDouble(f9.getText().toString());ut9=Double.parseDouble(t9.getText().toString());
                ul10=l10.getText().toString();uf10=Double.parseDouble(f10.getText().toString());ut10=Double.parseDouble(t10.getText().toString());


            sendData();






            }
        });




}



private void GetIds(){
        l1=findViewById(R.id.Ml1);
    l2=findViewById(R.id.Ml2);
    l3=findViewById(R.id.Ml3);
    l4=findViewById(R.id.Ml4);
    l5=findViewById(R.id.Ml5);
    l6=findViewById(R.id.Ml6);
    l7=findViewById(R.id.Ml7);
    l8=findViewById(R.id.Ml8);  f1=findViewById(R.id.Mf1);
    l9=findViewById(R.id.Ml9);
    l10=findViewById(R.id.Ml10);
    f2=findViewById(R.id.Mf2);
    f3=findViewById(R.id.Mt2);
    f4=findViewById(R.id.Mt3);
    f5=findViewById(R.id.Mf5);
    f6=findViewById(R.id.Mf6);
    f7=findViewById(R.id.Mf7);
    f8=findViewById(R.id.Mf8);
    f9=findViewById(R.id.Mf9);
    f10=findViewById(R.id.Mf10);
    t1=findViewById(R.id.Mt1);  t3=findViewById(R.id.Mt3);
    t2=findViewById(R.id.Mt2);
    t4=findViewById(R.id.Mt4);t6=findViewById(R.id.Mt6);t7=findViewById(R.id.Mt7);t8=findViewById(R.id.Mt8);t9=findViewById(R.id.Mt9);
    t5=findViewById(R.id.Mt5);t10=findViewById(R.id.Mt10);


}
    public void sendData(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference myRef1 = firebaseDatabase.getReference(firebaseUser.getUid());

        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Monday.Lec1 lec1 = new Monday.Lec1(ul1, uf1, ut1);
                myRef1.setValue(lec1);
                Monday.Lec2 lec2 = new Monday.Lec2(ul2, uf2, ut2);
                myRef1.setValue(lec2);
                Monday.Lec3 lec3 = new Monday.Lec3(ul3, uf3, ut3);
                myRef1.setValue(lec3);
                Monday.Lec4 lec4 = new Monday.Lec4(ul4, uf4, ut4);
                myRef1.setValue(lec4);
                Monday.Lec5 lec5 = new Monday.Lec5(ul5, uf5, ut5);
                myRef1.setValue(lec5);
                Monday.Lec6 lec6 = new Monday.Lec6(ul6, uf6, ut6);
                myRef1.setValue(lec6);
                Monday.Lec7 lec7 = new Monday.Lec7(ul7, uf7, ut7);
                myRef1.setValue(lec7);
                Monday.Lec8 lec8= new Monday.Lec8(ul8,uf8,ut8);
                myRef1.setValue(lec8);
                Monday.Lec9 lec9 = new Monday.Lec9(ul9,uf9,ut9);
                myRef1.setValue(lec9);
                Monday.Lec10 lec10 = new Monday.Lec10(ul10,uf10,ut10);
                myRef1.setValue(lec10);


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }




}