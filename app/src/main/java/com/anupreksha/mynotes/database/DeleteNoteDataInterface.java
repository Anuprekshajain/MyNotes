package com.anupreksha.mynotes.database;

import com.anupreksha.mynotes.models.Note;

import java.util.List;

public interface DeleteNoteDataInterface {
    void onDelete();
    void afterdelete(List<Note> notes);
}
