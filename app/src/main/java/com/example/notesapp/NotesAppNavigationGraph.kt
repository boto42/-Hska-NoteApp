package com.example.notesapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notesapp.screens.AllNotes
import com.example.notesapp.screens.NewNote
import com.example.notesapp.screens.SingleNote
import com.example.notesapp.viewmodel.NoteViewModel


@Composable
fun NotesAppNavigationGraph(
    noteViewModel: NoteViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Route.Home.routeTemplate
) {
    NavHost(
        navController = navController,
        startDestination = startDestination ,
        modifier = modifier
    )
    {
        composable(Route.Home.routeTemplate){ AllNotes(navController,noteViewModel) }
        composable(Route.NewNote.routeTemplate){ NewNote(navController,noteViewModel) }
        composable(Route.SingleNote.routeTemplate,arguments = listOf (navArgument("id")
        { type =
            NavType.LongType})
        ){ backStackEntry ->
            SingleNote( navController ,noteViewModel,
                backStackEntry.arguments?.getLong("id")?: 0)
        }
    }
}
