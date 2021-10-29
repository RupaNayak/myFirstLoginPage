package com.example.myfirstloginpage;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("DATA/");

    public void onFetch(View view){
        Toast.makeText(this, "Okk", Toast.LENGTH_SHORT).show();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,Map<String,String>> ob = (Map) dataSnapshot.getValue();
                Log.i("Info","The Wholw Object "+ob);
                String data = "";
                for(String x:ob.keySet()){
                    //Log.i("Info","Indivisual "+ (ob.get(x)));
                    for(String f: ob.get(x).keySet()){
                       // Log.i("Info"," Inner Indivisual "+ (ob.get(x).get(f)));
                        data = data +ob.get(x).get(f)+"  ";
                    }
                    data = data + "\n";
                }
                
                EditText editText = (EditText) findViewById(R.id.displayEditText);
                editText.setText(data);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }


    public void myDisplay(View view){
        Intent i= new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
}