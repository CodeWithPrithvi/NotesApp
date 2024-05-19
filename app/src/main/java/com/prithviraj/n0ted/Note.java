package com.prithviraj.n0ted;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Note_Table")
public class Note implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id=0;
    @ColumnInfo(name = "Title")
    private String title="";
    @ColumnInfo(name = "Content")
    private String content="";

    public Note(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
    @Ignore
    public Note() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
