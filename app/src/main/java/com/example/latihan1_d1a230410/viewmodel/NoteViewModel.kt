package com.example.latihan1_d1a230410.viewmodel

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.latihan1_d1a230410.Data.DatabaseHelper
import com.example.latihan1_d1a230410.Data.Note
import com.example.latihan1_d1a230410.Data.NoteRepository

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = NoteRepository(DatabaseHelper(application))

    private val _notes = mutableStateOf(listOf<Note>())
    val notes: State<List<Note>> = _notes

    fun loadNotes() {
        _notes.value = repository.getNotes()
    }

    fun addNote(title: String, content: String) {
        repository.addNote(title, content)
        loadNotes()
    }

    fun updateNote(id: Int, title: String, content: String) {
        repository.updateNote(id, title, content)
        loadNotes()
    }

    fun deleteNote(id: Int) {
        repository.deleteNote(id)
        loadNotes()
    }
}