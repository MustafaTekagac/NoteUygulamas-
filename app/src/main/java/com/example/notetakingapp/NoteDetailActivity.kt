package com.example.notetakingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView

class NoteDetailActivity : AppCompatActivity() {

    private lateinit var noteImageView: PhotoView
    private lateinit var noteTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        // UI bileşenlerini buluyoruz
        noteImageView = findViewById(R.id.note_image_detail)
        noteTextView = findViewById(R.id.note_text_detail)

        // Intent'ten not metni ve resim yolunu alıyoruz
        val noteText = intent.getStringExtra("note_text")
        val imagePath = intent.getStringExtra("image_path")

        // Not metnini göster
        noteTextView.text = noteText

        // Resmi yükle ve göster
        loadNoteImage(imagePath)
    }

    // Resmi Glide kullanarak yükleyen fonksiyon
    private fun loadNoteImage(imagePath: String?) {
        if (imagePath != null && imagePath.isNotEmpty()) {
            // Glide ile resmi PhotoView'a yüklüyoruz
            Glide.with(this)
                .load(imagePath)
                .into(noteImageView)  // Resmi PhotoView'a yerleştir
        } else {
            noteTextView.text = "${noteTextView.text}\n\nNo image available."
        }
    }
}
