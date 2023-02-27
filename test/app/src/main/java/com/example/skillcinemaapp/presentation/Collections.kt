package com.example.skillcinemaapp.presentation

sealed class Collections {
    object Favorites : Collections()
    object ToWatch : Collections()
    object Custom : Collections()
}
