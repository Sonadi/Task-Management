package com.example.taskmanagement

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanagement.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db : NoteDatabaseHelper
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)
        noteAdapter = NoteAdapter(db.getAllNotes(),this)
        binding.taskRecycleView.layoutManager = LinearLayoutManager(this)
        binding.taskRecycleView.adapter = noteAdapter

        binding.addbutton.setOnClickListener {
            val intent = Intent(this,AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        noteAdapter.refreshData(db.getAllNotes())
    }
}