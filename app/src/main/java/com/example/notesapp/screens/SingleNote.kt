package com.example.notesapp.screens




import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.example.notesapp.R
import com.example.notesapp.data.Note
import com.example.notesapp.viewmodel.NoteViewModel

@Composable
 fun SingleNote(navController: NavHostController, noteViewModel: NoteViewModel, id: Long
){

  val  _note = noteViewModel.getNote(id)

    val note = if (_note != null){
        _note
    }
    else{
        Note(0,"","",0L)
    }

    Content(note = note, navController = navController, noteViewModel = noteViewModel )
    }


fun onClick(note: Note,navController: NavHostController,noteViewModel: NoteViewModel){

    navController.popBackStack()
    noteViewModel.deleteNote(note)
}
@Composable
fun Content(note:Note,navController: NavHostController,noteViewModel: NoteViewModel){
    val scaffoldState = rememberScaffoldState()
    val scroll = rememberScrollState(0)
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopAppBar(title = {

            Text(text = note.title,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold)

        })  },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionButton(onClick = { onClick(note,navController,noteViewModel)}){
            Text(text = stringResource(R.string.delete))
        } },
        content = { Text(modifier= Modifier.verticalScroll(scroll),
                text= note.text) }

    )

}