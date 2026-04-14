package com.example.latihan1_d1a230410.Data

class NoteRepository(private val dbHelper: DatabaseHelper) {
    fun getNotes(): List<Note> {
        val cursor = dbHelper.getAllNotes()
        val notes = mutableListOf<Note>()
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val title = cursor.getString(cursor.getColumnIndexOrThrow("title"))
                val content = cursor.getString(cursor.getColumnIndexOrThrow("content"))
                notes.add(Note(id, title, content))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return notes
    }

    fun addNote(title: String, content: String) {
        dbHelper.insertNote(title, content)
    }

    fun updateNote(id: Int, title: String, content: String) {
        dbHelper.updateNote(id, title, content)
    }

    fun deleteNote(id: Int) {
        dbHelper.deleteNote(id)
    }
}