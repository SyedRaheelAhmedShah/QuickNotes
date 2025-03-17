package com.example.quicknotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class add_notes extends AppCompatActivity {

    EditText etitle,edescription;
    TextView toastline;
    Button saveNote;
    DBconnection DBconnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        etitle=findViewById(R.id.etitle);
        edescription=findViewById(R.id.eDescribe);
        saveNote=findViewById(R.id.esavenote);

        DBconnection =new DBconnection(add_notes.this);


        saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etitle.length()>0 && edescription.length()>0){
                    DBconnection.insertData(etitle.getText().toString(), edescription.getText().toString());
                    Toast.makeText(add_notes.this, "Data successfully inserted ", Toast.LENGTH_SHORT).show();
                    etitle.setText("");
                    edescription.setText("");
                    Intent intent1=new Intent(add_notes.this, MainActivity.class);
                    startActivity(intent1);

                }
                else {
                    Toast.makeText(add_notes.this, "Write something....", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}