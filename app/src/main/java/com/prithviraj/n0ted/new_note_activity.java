package com.prithviraj.n0ted;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.prithviraj.n0ted.Database.RoomDB;

import java.util.ArrayList;

public class new_note_activity extends AppCompatActivity {
    TextInputEditText title,content;
    Button newnoteBTN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RoomDB roomDB=RoomDB.getInstance(this);
        title=findViewById(R.id.titleEditText);
        content=findViewById(R.id.contentEditText);
        newnoteBTN=findViewById(R.id.addNoteButton);
        newnoteBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title.getText().toString().isEmpty())
                {
                    Toast.makeText(new_note_activity.this, "Please Enter a title", Toast.LENGTH_SHORT).show();
                }
                else if (content.getText().toString().isEmpty())
                {
                    Toast.makeText(new_note_activity.this, "Your Note Body Cant be empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    Note new_note= new Note();
                    new_note.setTitle(title.getText().toString());
                    new_note.setContent(content.getText().toString());
                    roomDB.noteDao().insert(new_note);
                    Toast.makeText(new_note_activity.this, "Note successfully inserted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        });
    }
}