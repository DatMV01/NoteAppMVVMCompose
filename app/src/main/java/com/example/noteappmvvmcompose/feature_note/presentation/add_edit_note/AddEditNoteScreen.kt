package com.example.noteappmvvmcompose.feature_note.presentation.add_edit_note

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.noteappmvvmcompose.feature_note.domain.model.NoteEntity
import com.example.noteappmvvmcompose.feature_note.presentation.add_edit_note.components.TransparentHintTextField
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddEditNoteScreen(
    navController: NavController,
    noteColor: Int,
    vm: AddEditNoteViewModel = hiltViewModel()
) {
    val titleState = vm.noteTitle.value
    val contentState = vm.noteContent.value

    val noteBackgroundAnimatable = remember {
        Animatable(
            Color(
                if (noteColor != -1) noteColor
                else vm.noteColor.value
            )
        )
    }
    val scope = rememberCoroutineScope()
    val snackbarHost = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        vm.eventFlow.collectLatest { event ->
            when (event) {
                AddEditNoteViewModel.UiEvent.SaveNote -> navController.navigateUp()
                is AddEditNoteViewModel.UiEvent.SnowSnackbar -> {
                    snackbarHost.showSnackbar(
                        message = event.message
                    )
                }

                is AddEditNoteViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { vm.onEvent(AddEditNoteEvent.SaveNote) },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save note")
            }
        }

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .padding(it)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                NoteEntity.noteColors.forEach { color ->
                    val colorInt = color.toArgb();
                    val fraction = 0.5
                    val c = Color(
                        (color.red * fraction).toFloat(),
                        (color.green * fraction).toFloat(),
                        (color.blue * fraction).toFloat()
                    )
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = 2.dp,
                                color = if (vm.noteColor.value == colorInt) {
                                    c
                                } else Color.Transparent,

                                shape = CircleShape
                            )
                            .clickable {
                                scope.launch {
                                    noteBackgroundAnimatable.animateTo(
                                        targetValue = Color(colorInt),
                                        animationSpec = tween(
                                            durationMillis = 500
                                        )
                                    )
                                }
                                vm.onEvent(AddEditNoteEvent.ChangeColor(colorInt))
                            }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            TransparentHintTextField(
                text = titleState.text,
                hint = titleState.hint,
                onValueChange = {
                    vm.onEvent(AddEditNoteEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    vm.onEvent(AddEditNoteEvent.ChangeTitleFocus(it))
                },
                isHintVisible = titleState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.displayLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            TransparentHintTextField(
                text = contentState.text,
                hint = contentState.hint,
                onValueChange = {
                    vm.onEvent(AddEditNoteEvent.EnteredContent(it))
                },
                onFocusChange = {
                    vm.onEvent(AddEditNoteEvent.ChangeContentFocus(it))
                },
                isHintVisible = contentState.isHintVisible,
                textStyle = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxHeight()
            )
        }
    }

}