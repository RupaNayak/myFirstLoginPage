package com.example.myfirstloginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    static  int i = 0;
    FirebaseDatabase database;
    DatabaseReference myRef;
    static String name;
    static String password;
    EditText nameEditText;
    EditText passwordEditText;
    public void mySubmit(View view){

        nameEditText = (EditText)findViewById(R.id.nameEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);

        name=nameEditText.getText().toString();
        password=passwordEditText.getText().toString();
        Log.i("Info","Username :  "+ name);
        Log.i("Info","Password :  "+ password);
        Toast.makeText(this, "Hi! "+ nameEditText.getText().toString(), Toast.LENGTH_SHORT).show();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("DATA/user"+i);
        User ob = new User(name,password);
        myRef.setValue(ob);
        i++;

    }
    public void myDisplay(View view){
        Intent i= new Intent(getApplicationContext(),MainActivity2.class);
        startActivity(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
class User{
    public String userName;
    public String passWord;

    public User(){
    }
    public User(String userName,String passWord){
        this.userName=userName;
        this.passWord=passWord;
    }
}