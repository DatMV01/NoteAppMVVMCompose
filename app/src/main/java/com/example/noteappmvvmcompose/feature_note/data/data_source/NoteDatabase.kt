package com.example.noteappmvvmcompose.feature_note.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteappmvvmcompose.feature_note.domain.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"

        private var INSTANCE: NoteDatabase? = null
        fun getDatabase(context: Context): NoteDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}