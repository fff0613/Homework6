package com.byted.camp.todolist.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byted.camp.todolist.NoteOperator;
import com.byted.camp.todolist.R;
import com.byted.camp.todolist.beans.Note;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created on 2019/1/23.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */
public class NoteListAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private final NoteOperator operator;
    private final List<Note> notes = new ArrayList<>();
    private final List<Note> highNotes = new ArrayList<>();
    private final List<Note> midNotes = new ArrayList<>();

    public NoteListAdapter(NoteOperator operator) {
        this.operator = operator;
    }

    public void refresh(List<Note> newNotes) {
        notes.clear();
        highNotes.clear();
        midNotes.clear();

        if (newNotes != null) {
            for(Note note: newNotes){
                if(note.getPriority() == 1)
                    highNotes.add(note);
                else if(note.getPriority() == 2){
                    midNotes.add(note);
                }else
                    notes.add(note);
            }
//            notes.addAll(newNotes);
        }

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int pos) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(itemView, operator);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int pos) {
        System.out.println("pos"+pos);
        if(pos < highNotes.size())
            holder.bind(highNotes.get(pos));
        else if((pos - highNotes.size()) < midNotes.size())
            holder.bind(midNotes.get((pos - highNotes.size())));
        else
            holder.bind(notes.get((pos - highNotes.size() - midNotes.size())));
//        holder.bind(notes.get(pos));
    }

    @Override
    public int getItemCount() {
        return notes.size() + highNotes.size() + midNotes.size();
//        return notes.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
