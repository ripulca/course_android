package com.example.course.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.course.Models.Notes;
import com.example.course.NotesClickListener;
import com.example.course.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesListAdapter extends RecyclerView.Adapter<NotesViewHolder>{

    Context context;
    List<Notes> list;
    NotesClickListener listener;

    public NotesListAdapter(Context context, List<Notes> list, NotesClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.note_title.setText(list.get(position).getTitle());
        holder.note_title.setSelected(true);

        holder.note_text.setText(list.get(position).getText());

        holder.note_date.setText(list.get(position).getDatetime());
        holder.note_date.setSelected(true);

        if(list.get(position).isPinned()){
            holder.pin.setImageResource(R.drawable.ic_pin);
        }
        else {
            holder.pin.setImageResource(0);
        }

        int colorCode=getRandomColor();
        holder.notes_list.setCardBackgroundColor(colorCode);

        holder.notes_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(list.get(holder.getAdapterPosition()));
            }
        });

        holder.notes_list.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onLongClick(list.get(holder.getAdapterPosition()), holder.notes_list);
                return true;
            }
        });
    }

    private int getRandomColor(){
        Random random = new Random();
        return Color.rgb(130+random.nextInt(100), 130+random.nextInt(110), 130+random.nextInt(110));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(List<Notes> filteredList){
        list=filteredList;
        notifyDataSetChanged();
    }
}

class NotesViewHolder extends RecyclerView.ViewHolder{

    CardView notes_list;
    TextView note_title, note_text, note_date;
    ImageView pin;

    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);
        notes_list=itemView.findViewById(R.id.notes_list);
        note_title=itemView.findViewById(R.id.note_title);
        note_text=itemView.findViewById(R.id.note_text);
        note_date=itemView.findViewById(R.id.note_date);
        pin=itemView.findViewById(R.id.pin);
    }
}