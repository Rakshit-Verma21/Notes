package com.example.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder>
{
    private List<Note> notes =new ArrayList<>();
    private OnItemClickListener listener;
    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,
                parent,false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position)
    {
        Note curentnote=notes.get(position);
        holder.textViewtitle.setText(curentnote.getTitle());
        holder.textViewdescription.setText(curentnote.getDescription());

    }

    @Override
    public int getItemCount()
    {
        return notes.size();
    }
    public void setNotes(List<Note>notes)
    {
        this.notes=notes;
        notifyDataSetChanged();
    }
    public  Note getNotes(int position)
    {
        return notes.get(position);
    }


    public class NoteHolder extends RecyclerView.ViewHolder{
        TextView textViewtitle;
        TextView textViewdescription;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewtitle=itemView.findViewById(R.id.textViewtitle);
            textViewdescription=itemView.findViewById(R.id.textViewdescription);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int positon=getAdapterPosition();
                    if(listener!=null&&positon!=RecyclerView.NO_POSITION)
                    {
                        listener.onItemClick(notes.get(positon));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Note note);
    }
    public void setOnItemClickListner(OnItemClickListener listner)
    {
        this.listener=listner;

    }

}
