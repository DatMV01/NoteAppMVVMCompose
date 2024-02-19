package com.example.noteappmvvmcompose.feature_note.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.noteappmvvmcompose.feature_note.domain.model.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM ${NoteDatabase.DATABASE_NAME}")
    fun getNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM ${NoteDatabase.DATABASE_NAME} WHERE id = :id")
    suspend fun getNoteById(id:Int): NoteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note : NoteEntity)
}