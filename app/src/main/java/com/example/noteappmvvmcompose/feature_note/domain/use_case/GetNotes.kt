package com.example.noteappmvvmcompose.feature_note.domain.use_case

import com.example.noteappmvvmcompose.feature_note.domain.model.NoteEntity
import com.example.noteappmvvmcompose.feature_note.domain.repository.NoteRepository
import com.example.noteappmvvmcompose.feature_note.domain.util.NoteOrder
import com.example.noteappmvvmcompose.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes(private val repository: NoteRepository) {

    operator fun invoke(noteOrder: NoteOrder = NoteOrder.Date(OrderType.Ascending)): Flow<List<NoteEntity>> {
        return repository.getNotes().map {
            when (noteOrder.orderType) {
                is OrderType.Ascending -> {
                    when (noteOrder) {
                        is NoteOrder.Title -> it.sortedBy { it.title.lowercase() }
                        is NoteOrder.Date -> it.sortedBy { it.timestamp }
                        is NoteOrder.Color -> it.sortedBy { it.color }
                    }
                }

                is OrderType.Descending -> {
                    when (noteOrder) {
                        is NoteOrder.Title -> it.sortedByDescending {
                            it.title.lowercase()
                        }

                        is NoteOrder.Date -> it.sortedByDescending { it.timestamp }
                        is NoteOrder.Color -> it.sortedByDescending { it.color }
                    }
                }
            }
        }
    }

}
