package com.example.cristian.listconsume.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cristian.listconsume.domain.useCases.GetCategoriesUseCase
import com.example.cristian.listconsume.presentation.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(private val getCategoriesUseCase: GetCategoriesUseCase) :
    ViewModel() {

    private val _status = MutableLiveData<ViewState>()
    val status: LiveData<ViewState> get() = _status

    fun getCategories() {
        viewModelScope.launch {
            _status.postValue(ViewState.Loading)
            getCategoriesUseCase.invoke()
                .onSuccess { categories ->
                    _status.postValue(ViewState.Success(categories))
                }.onFailure { error ->
                    _status.postValue(ViewState.Error(error))
                }
        }
    }
}