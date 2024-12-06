package com.example.taskmanagement

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.taskmanagement.databinding.ActivityAddNoteBinding

import com.example.taskmanagement.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db:NoteDatabaseHelper
    private var noteId:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id",-1)
        if(noteId == -1){
            finish()
            return
        }

        val note = db.getTaskById(noteId)
        binding.updatetitleedittext.setText(note.title)
        binding.updatecontentedittext.setText(note.content)

        binding.updatesavebutton.setOnClickListener{
            val newTitle = binding.updatetitleedittext.text.toString()
            val newContent = binding.updatecontentedittext.text.toString()
            val updatedNote = Note(noteId,newTitle,newContent)
            db.updateTask(updatedNote)
            finish()
            Toast.makeText(this,"Changes Saveed",Toast.LENGTH_SHORT).show()

        }


    }
}