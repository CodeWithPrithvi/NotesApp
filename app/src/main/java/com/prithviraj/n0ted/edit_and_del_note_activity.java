package com.prithviraj.n0ted;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.prithviraj.n0ted.Database.RoomDB;

public class edit_and_del_note_activity extends AppCompatActivity {
    Button btndel,buttonupdt;
    EditText title,content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_and_del_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RoomDB roomDB=RoomDB.getInstance(this);
        title=findViewById(R.id.setTitle);
        content=findViewById(R.id.setContent);
        btndel=findViewById(R.id.btn_delete);
        buttonupdt=findViewById(R.id.btn_update);
        Note note = (Note) getIntent().getSerializableExtra("Note");
        title.setText(note.getTitle().toString());
        content.setText(note.getContent().toString());
        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               roomDB.noteDao().delete(note);
                Intent i = new Intent(edit_and_del_note_activity.this,MainActivity.class);
                startActivity(i);
                Toast.makeText(edit_and_del_note_activity.this, "Note has been deleted successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        buttonupdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (note.getContent().toString().equalsIgnoreCase(content.getText().toString()) && note.getTitle().toString().equalsIgnoreCase(title.getText().toString())){
                    Toast.makeText(edit_and_del_note_activity.this, "No updates were made", Toast.LENGTH_SHORT).show();
                }
                else {
                    note.setTitle(title.getText().toString());
                    note.setContent(content.getText().toString());
                    roomDB.noteDao().update(note);
                    Intent i = new Intent(edit_and_del_note_activity.this,MainActivity.class);
                    startActivity(i);
                    Toast.makeText(edit_and_del_note_activity.this, "Note has been updated successfully", Toast.LENGTH_SHORT).show();
                    finish();

                }

            }
        });

    }
}