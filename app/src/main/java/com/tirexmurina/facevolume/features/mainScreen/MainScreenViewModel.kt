package com.tirexmurina.facevolume.features.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tirexmurina.facevolume.shared.domain.usecase.GetContactsUseCase
import com.tirexmurina.facevolume.shared.util.ContactListCorruptedException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getContactsUseCase: GetContactsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainScreenState>(MainScreenState.Initial)
    val uiState: StateFlow<MainScreenState> = _uiState.asStateFlow()

    fun getContacts() {
        _uiState.value = MainScreenState.Loading
        viewModelScope.launch {
            try {
                val result = getContactsUseCase()
                _uiState.value = MainScreenState.Success(result)
            } catch (e : Exception) {
                errorHandler(e)
            }
        }
    }

    private fun errorHandler(exception: Exception) {
        when(exception) {
            is ContactListCorruptedException -> {
                _uiState.value = MainScreenState.Error("Возникла проблема с получением списка контактов")
            }

            else -> {
                _uiState.value = MainScreenState.Error("Неизвестная ошибка")
            }
        }
    }

}