package com.example.skillcinemaapp.presentation

sealed class Selections {

    object Top : Selections()
    object Popular : Selections()
    object FirstCustom : Selections()
    object SecondCustom : Selections()
    object Series : Selections()
    object Premiers : Selections()

}
