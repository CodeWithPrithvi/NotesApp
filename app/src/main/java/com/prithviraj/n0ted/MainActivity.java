package com.prithviraj.n0ted;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.prithviraj.n0ted.Database.RoomDB;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton addNote;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<Note> note_list=new ArrayList<Note>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RoomDB roomDB=RoomDB.getInstance(this);

        note_list = (ArrayList<Note>) roomDB.noteDao().getAllNotes();
        updateRecycler(note_list);
        addNote=findViewById(R.id.addBTN);
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), new_note_activity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    private void updateRecycler(ArrayList<Note> notes)
    {
        recyclerView=findViewById(R.id.main_recyler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(notes);
        recyclerView.setAdapter(myAdapter);
    }
}