package com.example.notesapp.screens


import androidx.compose.foundation.border
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.stringResource

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notesapp.R
import com.example.notesapp.Route


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
                Card(modifier = Modifier
                    .fillMaxSize()
                    .padding(1.dp)
                    .border(
                        width = 2.dp, color = androidx.compose.ui.graphics.Color.Black,
                        shape = RoundedCornerShape(3.dp)
                    )

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
            Text(text = stringResource(R.string.add))
        }
    }
}





