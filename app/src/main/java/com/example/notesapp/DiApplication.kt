package com.example.notesapp

import android.app.Application
import com.example.notesapp.data.NoteDatabase
import com.example.notesapp.data.NoteRepository

class DiApplication : Application() {

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    private val dao by lazy { NoteDatabase.getDatabase(this).noteDao() }
    val noteRepo by lazy { NoteRepository(dao) }
}