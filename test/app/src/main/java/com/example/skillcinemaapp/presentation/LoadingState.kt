package com.example.skillcinemaapp.presentation

sealed class LoadingState{

    object IsLoading: LoadingState()

    object LoadingIsFinished: LoadingState()

}
