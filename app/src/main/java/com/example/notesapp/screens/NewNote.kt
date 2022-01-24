package com.example.notesapp.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import com.example.notesapp.viewmodel.NoteViewModel
import java.sql.Date
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Composable
fun NewNote(
    navController: NavHostController,
    noteViewModel: NoteViewModel

){
    val scroll = rememberScrollState(0)
    Column(Modifier.fillMaxWidth()) {
        val textStateTitle = remember { mutableStateOf(TextFieldValue()) }
        val textStateNote = remember { mutableStateOf(TextFieldValue()) }
        Button(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .weight(10f),
            onClick = { onClick(
                navController,
                noteViewModel,
                textStateTitle.value.text,
                textStateNote.value.text,
            )
            },
            enabled = !textStateTitle.value.text.equals("")

        ){
            Text(text = "add")
        }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(10f),
            value = textStateTitle.value,
            onValueChange = { textStateTitle.value = it },
            placeholder = {Text(text="Add Title to save Note" )},
            label = { Text(text = "Title")}
        )
        OutlinedTextField(
            modifier = Modifier
                .weight(80f)
                .fillMaxWidth()
                .verticalScroll(scroll),
            maxLines = 20,
            value = textStateNote.value,
            onValueChange = { textStateNote.value = it },
            placeholder = {Text(text="Add your Notes content" )},
            label = { Text(text = "Content")}
        )

    }
}
fun onClick(navController: NavHostController,noteViewModel: NoteViewModel,title:String,text :String){


    val now = LocalDateTime.now()
    val nowInSeconds = now.toEpochSecond(ZoneOffset.UTC)


    noteViewModel.addNote(title,text,nowInSeconds)
    navController.popBackStack()
}
