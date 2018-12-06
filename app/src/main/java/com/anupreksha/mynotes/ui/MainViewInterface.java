package com.anupreksha.mynotes.ui;

import com.anupreksha.mynotes.models.Note;

import java.util.List;

public interface MainViewInterface {

    void onNotesLoaded(List<Note> notes);


    void onDataNotAvailable();

}