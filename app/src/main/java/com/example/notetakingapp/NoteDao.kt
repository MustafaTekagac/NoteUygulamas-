package com.example.notetakingapp

//import BaseDao
import androidx.room.Dao
import androidx.room.Query

@Dao
abstract class NoteDao : BaseDao<Note>() {

    @Query("SELECT * FROM notes")
    abstract suspend fun getAllNotes(): List<Note>  // Tüm notları almak için
}
