package com.example.cristian.listconsume.presentation

sealed class ViewState {
    object Loading : ViewState()
    data class Success(val data: List<String>) : ViewState()
    data class Error(val exception: Throwable) : ViewState()
}