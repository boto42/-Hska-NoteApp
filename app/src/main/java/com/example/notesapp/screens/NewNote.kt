package com.example.notesapp.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.notesapp.R
import com.example.notesapp.viewmodel.NoteViewModel
import java.time.LocalDateTime
import java.time.ZoneOffset

@Composable
fun NewNote(
    navController: NavHostController,
    noteViewModel: NoteViewModel

){
    val scaffoldState = rememberScaffoldState()
    val scroll = rememberScrollState(0)
    val textStateTitle = remember { mutableStateOf(TextFieldValue()) }
    val textStateNote = remember { mutableStateOf(TextFieldValue()) }
    Scaffold(scaffoldState=scaffoldState,
        content ={
    Column(Modifier.fillMaxWidth()) {


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                ,
            value = textStateTitle.value,
            onValueChange = { textStateTitle.value = it },
            placeholder = {Text(text= stringResource(R.string.placeholder_title ))},
            label = { Text(text = stringResource(R.string.title))}
        )
        OutlinedTextField(
            modifier = Modifier

                .fillMaxWidth()
                .verticalScroll(scroll)
                .weight(1f),

            value = textStateNote.value,
            onValueChange = { textStateNote.value = it },
            placeholder = {Text(text= stringResource(id = R.string.placeholder_content) )},
            label = { Text(text = stringResource(id = R.string.content))}
        )

    }}, topBar = {Button(modifier = Modifier.padding(40.dp,0.dp),
            onClick = { onClick(
                navController,
                noteViewModel,
                textStateTitle.value.text,
                textStateNote.value.text,
            )
            },
            enabled = !textStateTitle.value.text.equals("")

        ){
            Text(text = stringResource(R.string.add))
        }})
}
fun onClick(navController: NavHostController,noteViewModel: NoteViewModel,title:String,text :String){


    val now = LocalDateTime.now()
    val nowInSeconds = now.toEpochSecond(ZoneOffset.UTC)


    noteViewModel.addNote(title,text,nowInSeconds)
    navController.popBackStack()
}
