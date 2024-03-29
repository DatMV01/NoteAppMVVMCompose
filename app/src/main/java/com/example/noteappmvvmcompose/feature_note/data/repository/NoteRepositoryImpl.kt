package com.example.noteappmvvmcompose.feature_note.data.repository

import com.example.noteappmvvmcompose.feature_note.data.data_source.NoteDao
import com.example.noteappmvvmcompose.feature_note.domain.model.NoteEntity
import com.example.noteappmvvmcompose.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val dao: NoteDao) : NoteRepository {
    override fun getNotes(): Flow<List<NoteEntity>> = dao.getNotes();

    override suspend fun getNoteById(id: Int) = dao.getNoteById(id);

    override suspend fun insertNote(note: NoteEntity) = dao.insertNote(note);

    override suspend fun deleteNote(note: NoteEntity) = dao.deleteNote(note);
}