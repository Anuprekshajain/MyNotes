package com.anupreksha.mynotes.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.anupreksha.mynotes.models.Note;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM notes")
    Maybe<List<Note>> getAll();

    @Insert
    void insertAll(Note... notes);

    @Insert
    void insert(Note note);

    /*
     * update the object in database
     * @param note, object to be updated
     */
    @Update
    void update(Note repos);

    /*
     * delete the object from database
     * @param note, object to be deleted
     */
    @Delete
    void delete(Note note);

    @Query("Update notes set title= :title, note=:description where id=:id")
    void update(String title,String description,int id);

    /*
     * delete list of objects from database
     * @param note, array of objects to be deleted
     */
    @Delete
    void delete(Note... note);      // Note... is varargs, here note is an array

}