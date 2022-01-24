package com.example.notesapp


sealed class Route(val routeTemplate: String) {
    object Home : Route("Home")
    object NewNote : Route("NewNote")
    object SingleNote : Route("SingleNote/{id}") {
        fun createRoute(id: Long=0) = "SingleNote/$id"
    }
}