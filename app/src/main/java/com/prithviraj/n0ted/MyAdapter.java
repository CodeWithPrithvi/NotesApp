package com.prithviraj.n0ted;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Note> Notes;

    public MyAdapter(ArrayList<Note> notes) {
        this.Notes = notes;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.itemTextView1.setText(Notes.get(position).getTitle());
        holder.itemTextView2.setText(Notes.get(position).getContent());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(v.getContext(),edit_and_del_note_activity.class);
                intent.putExtra("Note",Notes.get(position));
                v.getContext().startActivity(intent);

            }
        });
    }
    @Override
    public int getItemCount() {
        return Notes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemTextView1, itemTextView2;
        LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.linear_layout);
            itemTextView1 = itemView.findViewById(R.id.NoteTitle);
            itemTextView2 = itemView.findViewById(R.id.NoteContent);
        }
    }
}
