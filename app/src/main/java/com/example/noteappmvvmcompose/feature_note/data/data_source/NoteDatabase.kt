package com.example.noteappmvvmcompose.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteappmvvmcompose.feature_note.domain.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao : NoteDao

    companion object{
        const val DATABASE_NAME = "notes_db"
    }
}