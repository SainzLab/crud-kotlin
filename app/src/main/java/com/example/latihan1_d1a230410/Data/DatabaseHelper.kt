package com.example.latihan1_d1a230410.Data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "notes.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE Notes (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Notes")
        onCreate(db)
    }

    fun insertNote(title: String, content: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("title", title)
            put("content", content)
        }
        return db.insert("Notes", null, values) != -1L
    }

    fun getAllNotes(): Cursor {
        val db = readableDatabase
        return db.rawQuery("SELECT * FROM Notes", null)
    }

    fun updateNote(id: Int, title: String, content: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("title", title)
            put("content", content)
        }
        return db.update("Notes", values, "id = ?", arrayOf(id.toString())) > 0
    }

    fun deleteNote(id: Int): Boolean {
        val db = writableDatabase
        return db.delete("Notes", "id = ?", arrayOf(id.toString())) > 0
    }
}