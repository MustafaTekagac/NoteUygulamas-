package com.example.notetakingapp

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream

class AddNoteActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var noteText: EditText
    private lateinit var saveButton: Button
    private var noteImage: Bitmap? = null  // Resim için Bitmap

    private val CAMERA_REQUEST = 1888
    private val GALLERY_REQUEST = 1 // Galeri isteği için sabit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        // View'leri tanımla
        imageView = findViewById(R.id.note_image)
        noteText = findViewById(R.id.note_text)
        saveButton = findViewById(R.id.save_note_button)

        // Resim seçimi için diyalog aç
        imageView.setOnClickListener {
            val options = arrayOf("Kamera", "Galeriden Seç")
            AlertDialog.Builder(this)
                .setTitle("Resim Seç")
                .setItems(options) { dialog, which ->
                    when (which) {
                        0 -> {
                            // Kamera seçildi
                            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            startActivityForResult(cameraIntent, CAMERA_REQUEST)
                        }
                        1 -> {
                            // Galeri seçildi
                            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                            startActivityForResult(galleryIntent, GALLERY_REQUEST)
                        }
                    }
                }
                .show()
        }

        // Notu kaydetme işlemi
        saveButton.setOnClickListener {
            val noteContent = noteText.text.toString()
            // Not metni boş olmamalı
            if (noteContent.isNotEmpty() && noteImage != null) {
                // Resmi kaydet ve dosya yolunu al
                val imagePath = saveImageToStorage(noteImage!!)
                val newNote = Note(noteContent, imagePath)

                // Intent ile Note'u geri gönder
                val resultIntent = Intent()
                resultIntent.putExtra("note", newNote)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            } else {
                // Hata durumu
                if (noteContent.isEmpty()) {
                    noteText.error = "Not metni boş olamaz!"
                }
                if (noteImage == null) {
                    // Resim seçilmedi
                    AlertDialog.Builder(this)
                        .setTitle("Hata")
                        .setMessage("Lütfen bir resim seçin!")
                        .setPositiveButton("Tamam", null)
                        .show()
                }
            }
        }
    }

    private fun Note(id: String, text: String): Note {
        TODO("Not yet implemented")
    }

    // Kamera veya Galeri'den gelen sonucu işlemek
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                CAMERA_REQUEST -> {
                    // Kamera ile çekilen resim
                    noteImage = data?.extras?.get("data") as Bitmap
                    imageView.setImageBitmap(noteImage)  // Resmi ImageView'e ayarla
                }
                GALLERY_REQUEST -> {
                    // Galeriden seçilen resim
                    val selectedImageUri = data?.data
                    if (selectedImageUri != null) {
                        val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImageUri)
                        noteImage = bitmap
                        imageView.setImageBitmap(noteImage)
                    }
                }
            }
        }
    }

    // Resmi depolamaya kaydet ve dosya yolunu döndür
    private fun saveImageToStorage(bitmap: Bitmap): String {
        val filename = "${System.currentTimeMillis()}.png"
        val file = File(getExternalFilesDir(null), filename)
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out) // Resmi PNG formatında kaydet
        }
        return file.absolutePath // Dosya yolunu döndür
    }
}

private fun Intent.putExtra(s: String, newNote: Note) {

}
