package com.example.notesapp.data


import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)
    @Delete
    suspend fun delete(note: Note)

    @Query("select * from note order by createdAt asc")
    fun getAll(): Flow<List<Note>>

    @Query("select * from note where id= :id")
     suspend fun getNote(id: Long):Note



}