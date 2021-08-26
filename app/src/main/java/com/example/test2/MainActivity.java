package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    EditText editname;
    EditText editage;
    Button submit_btn;

    DatabaseReference studentref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editage=findViewById(R.id.ed2);
        editname=findViewById(R.id.ed1);
        submit_btn=(Button) findViewById(R.id.btn_submit);

        studentref= FirebaseDatabase.getInstance().getReference().child("student");
    }




    private  void  insertStudentData()
    {
        String name= editname.getText().toString();
        String age=editage.getText().toString();
        students student=new students(name,age);
        studentref.push().setValue(student);

        Toast.makeText(MainActivity.this,"Data inserted",Toast.LENGTH_SHORT).show();
    }

    public void  to(View view)
    {
        insertStudentData();
    }

    public  void go(View view)
    {
        Intent i1=new Intent(MainActivity.this,retreive_data.class);
        startActivity(i1);
    }
}