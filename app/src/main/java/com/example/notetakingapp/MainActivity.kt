package com.example.notetakingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var noteAdapter: NoteAdapter
    private lateinit var noteRepository: NoteRepository
    private var notes: List<Note> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteRepository = NoteRepository(this)

        // RecyclerView'i ayarla
        noteAdapter = NoteAdapter(notes) { note ->
            deleteNote(note)
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = noteAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadNotes()
    }

    // Notları yüklemek için
    private fun loadNotes() {
        GlobalScope.launch(Dispatchers.Main) {
            notes = noteRepository.getAllNotes()
            noteAdapter.updateNotes(notes) // RecyclerView'i güncelle
        }
    }

    // Notu silmek için
    private fun deleteNote(note: Note) {
        GlobalScope.launch(Dispatchers.Main) {
            noteRepository.delete(note)
            loadNotes() // Notlar güncellenir
        }
    }
}
