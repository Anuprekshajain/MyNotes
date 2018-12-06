package com.anupreksha.mynotes.ui;

import com.anupreksha.mynotes.models.Note;

import java.util.List;

public interface DeleteNoteDataInterface {
    void onDelete();
    void afterdelete(List<Note> notes);
}
