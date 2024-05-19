package com.prithviraj.n0ted.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.prithviraj.n0ted.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    public abstract NoteDao noteDao();

    private static RoomDB INSTANCE;

    public static RoomDB getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (RoomDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    RoomDB.class, "note_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

