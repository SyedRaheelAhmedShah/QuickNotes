package com.example.quicknotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton flotbtn;
    RecyclerView recyclerView;
    ArrayList<DataModel> arrayList=new ArrayList<>();
    DBconnection dBConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flotbtn=findViewById(R.id.addbtn);
        recyclerView=findViewById(R.id.recycleview);
        dBConnection=new DBconnection(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Cursor cursor=dBConnection.showData();
        while (cursor.moveToNext()){
            arrayList.add(new DataModel(cursor.getString(1), cursor.getString(2), cursor.getInt(0)));
        }

        DataAdapter adapter=new DataAdapter(this,arrayList);
        recyclerView.setAdapter(adapter);

        flotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent addUp=new Intent(MainActivity.this,add_notes.class);
            startActivity(addUp);
            }
        });

    }
}