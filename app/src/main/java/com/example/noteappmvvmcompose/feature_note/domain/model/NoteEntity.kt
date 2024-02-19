package com.example.noteappmvvmcompose.feature_note.domain.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteappmvvmcompose.ui.theme.RedOrange
import com.example.noteappmvvmcompose.ui.theme.LightGreen
import com.example.noteappmvvmcompose.ui.theme.Violet
import com.example.noteappmvvmcompose.ui.theme.BabyBlue
import com.example.noteappmvvmcompose.ui.theme.RedPink

@Entity
data class NoteEntity(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }

    class InvalidNoteException(message: String) : Exception(message)
}
