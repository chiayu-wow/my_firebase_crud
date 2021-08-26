package com.example.test2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class retreive_data extends AppCompatActivity {

    ListView mylistview;
    List<students> studentslist;

    DatabaseReference studentsReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retreive_data);

        mylistview=findViewById(R.id.myLv);
        studentslist=new ArrayList<>();

        studentsReference = FirebaseDatabase.getInstance().getReference().child("student");

        studentsReference.addValueEventListener(new ValueEventListener() {



            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studentslist.clear();

                for(DataSnapshot dataSnapsnap : snapshot.getChildren() )
                {
                    students student= dataSnapsnap.getValue(students.class);

                    String personId = String.valueOf(dataSnapsnap.getKey());

                    student.setId(personId);

                    studentslist.add(student);

                }

                ListAdapter adapter = new ListAdapter(retreive_data.this,studentslist);
                mylistview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mylistview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
               students student=studentslist.get(position);

               ShowUpdateDialog(student.getId(),student.getName());

               showToast(student.getId());

               return  false;
            }
        });

    }

    private void ShowUpdateDialog(String id, String name)
    {
        AlertDialog.Builder mdialog=new AlertDialog.Builder(this);
        LayoutInflater inflater=getLayoutInflater();
        View mDialogview= inflater.inflate(R.layout.update_dialog,null);

        mdialog.setView(mDialogview);

        EditText e_name=mDialogview.findViewById(R.id.edit_name);
        EditText e_age=mDialogview.findViewById(R.id.edit_age);
        Button change_bt=mDialogview.findViewById(R.id.change_btn);
        Button dele_bt=mDialogview.findViewById(R.id.de_btn);

        mdialog.setTitle("Updating"+name+"record");
        mdialog.show();

        change_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                String newname=e_name.getText().toString();
                String newage=e_age.getText().toString();

                updateData(id,newname,newage,name);

                Toast.makeText(retreive_data.this,"Record Updated",Toast.LENGTH_SHORT).show();
            }
        });

        dele_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleRecord(id);
            }
        });


    }


    private  void showToast(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    private  void  deleRecord(String id)
    {


        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();



        Task<Void> mTask=rootRef.child("student").child(id).removeValue();

        mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                showToast("Deleted");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                showToast("error to delete");
            }
        });


    }

    private  void updateData(String id,String name,String age,String oldname)
    {


        DatabaseReference Dbref_name;

        Dbref_name=FirebaseDatabase.getInstance().getReference("student").child(id);

        students student=new students (id,name,age);

        Dbref_name.setValue(student);
    }
}