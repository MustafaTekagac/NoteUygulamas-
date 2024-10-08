package com.example.notetakingapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,  // ID'nin türü Long olmalı
    val text: String,                                   // Notun metni
    val imagePath: String?                              // Resim dosyasının yolu (nullable olabilir)
)
