package com.example.course;

import androidx.cardview.widget.CardView;

import com.example.course.Models.Notes;

public interface NotesClickListener {
    void onClick(Notes notes);

    void onLongClick(Notes notes, CardView cardView);
}
