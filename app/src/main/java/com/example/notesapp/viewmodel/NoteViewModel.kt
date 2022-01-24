package com.example.notesapp.viewmodel

import androidx.lifecycle.*
import com.example.notesapp.data.Note
import com.example.notesapp.data.NoteRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch


class NoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    val notes: LiveData<List<Note>> = noteRepository.allNotes.asLiveData()


    fun addNote(title: String, text: String,createdAt: Long) {
        viewModelScope.launch {

            noteRepository.addNote(Note(0, title, text,createdAt))
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {

            noteRepository.removeNote(note)
        }
    }

     fun getNote(id: Long): Note? {

         return notes.value?.firstOrNull{it.id == id}
    }

}
