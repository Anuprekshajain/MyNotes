package com.anupreksha.mynotes.database;

import com.anupreksha.mynotes.models.Note;

import java.util.List;

public interface MainViewInterface {

    void onNotesLoaded(List<Note> notes);

    void onNoteAdded();

    void onDataNotAvailable();

}