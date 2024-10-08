package com.example.notetakingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NoteAdapter(
    private var notes: List<Note>,              // Notların listesi
    private val onDeleteClick: (Note) -> Unit   // Silme işlemi için fonksiyon
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    // ViewHolder sınıfı, her bir öğeyi tanımlar
    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val noteImage: ImageView = view.findViewById(R.id.note_image)    // Resim için ImageView
        val noteText: TextView = view.findViewById(R.id.note_text)       // Not metni için TextView
        val deleteIcon: ImageView = view.findViewById(R.id.delete_icon)  // Silme ikonu için ImageView
    }

    // ViewHolder'ı oluştur
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    // Veriyi ilgili ViewHolder'a bağla
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]

        // Not metnini TextView'e yaz
        holder.noteText.text = note.noteText

        // Resmi Glide ile yükle
        note.noteImagePath?.let { imagePath ->
            Glide.with(holder.itemView.context)
                .load(imagePath) // Resmin dosya yolunu kullanarak yükle
                .into(holder.noteImage)
        }

        // Çöp kutusu ikonuna tıklandığında notu silmek için tıklama işlemi
        holder.deleteIcon.setOnClickListener {
            onDeleteClick(note)
        }
    }

    // Liste boyutunu döndür
    override fun getItemCount(): Int = notes.size

    // Notlar güncellendiğinde listeyi yeniden ayarla
    fun updateNotes(updatedNotes: List<Note>) {
        this.notes = updatedNotes
        notifyDataSetChanged()  // RecyclerView'i güncelle
    }
}
