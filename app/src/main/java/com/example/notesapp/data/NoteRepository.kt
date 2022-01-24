package com.example.notesapp.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow


class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: Flow<List<Note>> = noteDao.getAll()

    suspend fun addNote(note: Note) {
        noteDao.insert(note)
    }
    suspend fun removeNote(note: Note) {
        noteDao.delete(note)
    }
     suspend fun getNote(id : Long):Note{
       return noteDao.getNote(id)
    }


}