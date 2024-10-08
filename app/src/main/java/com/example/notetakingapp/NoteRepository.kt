package com.example.notetakingapp

import android.content.Context

class NoteRepository(context: Context) {
    private val db = NoteDatabase.getDatabase(context)
    private val noteDao = db.noteDao()

    suspend fun insert(note: Note) = noteDao.insert(note)
    suspend fun update(note: Note) = noteDao.update(note)
    suspend fun delete(note: Note) = noteDao.delete(note)

    // Tüm notları almak için
    suspend fun getAllNotes(): List<Note> = noteDao.getAllNotes()
}
