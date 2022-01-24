package com.example.notesapp.screens

import android.graphics.Color
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight.Companion.Black
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notesapp.Route

import com.example.notesapp.data.Note
import com.example.notesapp.viewmodel.NoteViewModel

@Composable
fun AllNotes(
    navController: NavController,
    noteViewModel: NoteViewModel
    ) {

    val notes by noteViewModel.notes.observeAsState(emptyList())

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            items(items = notes) { note ->
                Surface(modifier = Modifier
                    .fillMaxSize()
                    .border(width = 2 .dp  ,color = androidx.compose.ui.graphics.Color.Black,
                   shape = RectangleShape )

                    .clickable { navController.navigate(Route.SingleNote.createRoute(note.id)) }) {
                    Text(textAlign = TextAlign.Center,
                        text =note.title,
                        style = MaterialTheme.typography.h4)
                }
            }
        }
        Button(modifier = Modifier
            .align(Alignment.CenterHorizontally),
            onClick = { navController.navigate(Route.NewNote.routeTemplate)
        },
        ) {
            Text(text = "Add note")
        }
    }
}





