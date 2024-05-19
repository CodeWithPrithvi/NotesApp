package com.prithviraj.n0ted.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.prithviraj.n0ted.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);
    @Delete
    void delete(Note note);
    @Query("select * from Note_Table")
    List<Note> getAllNotes();
    @Query("select * from Note_Table where id = :noteID")
    Note getNoteById(int noteID);
    @Update
    void update(Note note);
    
}
