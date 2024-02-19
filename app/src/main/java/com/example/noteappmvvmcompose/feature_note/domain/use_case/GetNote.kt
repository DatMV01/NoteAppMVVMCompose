package com.example.noteappmvvmcompose.feature_note.domain.use_case

import com.example.noteappmvvmcompose.feature_note.domain.model.NoteEntity
import com.example.noteappmvvmcompose.feature_note.domain.repository.NoteRepository

class GetNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Int): NoteEntity? {
        return repository.getNoteById(id)
    }
}