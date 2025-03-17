package com.example.quicknotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update extends AppCompatActivity {


      EditText updatetitle, updateDescribe;
    Button updatenote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        updatetitle = findViewById(R.id.updatetitle);
        updateDescribe = findViewById(R.id.updateDescribe);
        updatenote = findViewById(R.id.updatenote);

        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("Description");
        int id = getIntent().getIntExtra("id", 0);
        String sid = String.valueOf(id);

        updatetitle.setText(title);
        updateDescribe.setText(description);

        updatenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBconnection dBconnection = new DBconnection(update.this);
                dBconnection.updatedata(updatetitle.getText().toString(), updateDescribe.getText().toString(), sid);
                Toast.makeText(update.this, "Data is successfully updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(update.this, MainActivity.class));
            }
        });
    }
}
