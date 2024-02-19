package com.example.noteappmvvmcompose.feature_note.domain.use_case

import com.example.noteappmvvmcompose.feature_note.domain.model.NoteEntity
import com.example.noteappmvvmcompose.feature_note.domain.repository.NoteRepository

class AddNote(private val repository: NoteRepository) {

    suspend operator fun invoke(note: NoteEntity) {
        if (note.title.isBlank()) {
            throw NoteEntity.InvalidNoteException("The title of the note can't be empty.")
        }
        if (note.content.isBlank()) {
            throw NoteEntity.InvalidNoteException("The content of the note can't be empty.")
        }
        repository.insertNote(note)
    }
}